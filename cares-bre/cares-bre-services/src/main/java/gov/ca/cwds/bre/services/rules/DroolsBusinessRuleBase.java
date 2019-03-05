package gov.ca.cwds.bre.services.rules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreRequestData;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BreResponseData;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDocumentation;
import gov.ca.cwds.bre.interfaces.model.RuleDocumentation;
import gov.ca.cwds.bre.services.api.BusinessRule;
import gov.ca.cwds.drools.DroolsConfiguration;
import gov.ca.cwds.drools.DroolsException;
import gov.ca.cwds.rest.exception.IssueDetails;

/**
 * @author CWDS J Team
 */
public abstract class DroolsBusinessRuleBase implements BusinessRule {
  
  @Autowired
  @Qualifier(value="BreDroolsService")
  private BreDroolsService breDroolsService;
  
  @Autowired
  protected ObjectMapper jacksonObjectMapper;
  
  @Override
  public BreResponse execute(BreRequest breRequest) {
    Object[] facts = getFacts(breRequest);
    Set<IssueDetails> issues = new HashSet<>();
    
    // First execute validation rules
    Set<IssueDetails> validationIssues = breDroolsService.performBusinessRules(getDroolsConfiguration(getDroolsValidationAgenda()), facts);
    issues.addAll(validationIssues);
    
    // If no validation issues then execute data processing business rules
    if (validationIssues.isEmpty()) {      
      Set<IssueDetails> dataProcessingIssues = breDroolsService.performBusinessRules(getDroolsConfiguration(getDroolsDataProcessingAgenda()), facts);
      issues.addAll(dataProcessingIssues);      
    }
    
    BreResponse breResponse = new BreResponse();
    breResponse.setBusinessRuleSetName(getName());
    breResponse.setIssues(issues);
    
    // Put the updated fact in response
    for (Object fact : facts) {
      JsonNode updatedFact = jacksonObjectMapper.convertValue(fact, JsonNode.class);   
      String dataClassName = fact.getClass().getName();
      BreResponseData breResponseData = new BreResponseData();
      breResponseData.setDataObjectClassName(dataClassName);
      breResponseData.setDataObject(updatedFact);
      breResponse.addDataObject(breResponseData);
    }
    
    return breResponse;
  }
  
  @Override
  public BusinessRuleSetDocumentation getDocumentation() {
    BusinessRuleSetDocumentation doc = new BusinessRuleSetDocumentation();
    doc.setBusinessRuleSetName(getName());    
    doc.setRules(getRuleDocumentation());    
    return doc;    
  }
  
  private Object[] getFacts(BreRequest breRequest) {
    List<Object> facts = new ArrayList<>();
    List<BreRequestData> requestDataObjects = breRequest.getDataObjects();
    
    for (BreRequestData requestData : requestDataObjects) {
      try {
        Class<?> dataClass = Class.forName(requestData.getDataObjectClassName());
        JsonNode dataNode = requestData.getDataObject();
        Object fact = jacksonObjectMapper.readValue(jacksonObjectMapper.writeValueAsString(dataNode), dataClass);
        facts.add(fact);  
      } catch (IOException t) {
        throw new BreException("Error reading business rule data for: " + getName(), 
          t, breRequest);      
      } catch (ClassNotFoundException e) {
        throw new BreException("Error finding class for business rule data for: " + getName(), 
            e, breRequest);
      }
    }
    return facts.toArray();
  }
  
  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }
  
  private String getDroolsSessionName() {
    return getName() + "-session";
  }
  
  private String getDroolsRulesPath() {
    return "rules/drools/" + getName();
  }
  
  private String getDroolsValidationAgenda() {
    return getName() + "-validation-agenda";
  }
  
  private String getDroolsDataProcessingAgenda() {
    return getName() + "-data-processing-agenda";
  }
  
  private List<RuleDocumentation> getRuleDocumentation() {
    try {
      return breDroolsService.getRuleDocuments(
          getDroolsConfiguration(null), 
          getName() + "-kbase");
    } catch (DroolsException e) {
      throw new BreException(e.getMessage(), e);
    }
  }
  
  @SuppressWarnings("rawtypes")
  private DroolsConfiguration getDroolsConfiguration(String agenda) {
    return new DroolsConfiguration(getDroolsSessionName(), agenda, getDroolsRulesPath());
  }

  public BreDroolsService getBreDroolsService() {
    return breDroolsService;
  }

  public void setBreDroolsService(BreDroolsService breDroolsService) {
    this.breDroolsService = breDroolsService;
  }

  public ObjectMapper getJacksonObjectMapper() {
    return jacksonObjectMapper;
  }

  public void setJacksonObjectMapper(ObjectMapper jacksonObjectMapper) {
    this.jacksonObjectMapper = jacksonObjectMapper;
  }
}
