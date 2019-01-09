package gov.ca.cwds.bre.services.rules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDocumentation;
import gov.ca.cwds.bre.interfaces.model.RuleDocumentation;
import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.bre.services.api.BusinessRule;
import gov.ca.cwds.drools.DroolsConfiguration;
import gov.ca.cwds.drools.DroolsException;
import gov.ca.cwds.rest.exception.IssueDetails;

/**
 * @author CWDS J Team
 */
public abstract class DroolsBusinessRuleBase<F> implements BusinessRule {
  
  @Autowired
  @Qualifier(value="BreDroolsService")
  private BreDroolsService breDroolsService;
  
  @Autowired
  protected ObjectMapper jacksonObjectMapper;
  
  private BusinessRuleDefinition businessRuleDefinition;
  private BusinessRuleSetDocumentation businessRuleDocumentation;
  
  @Override
  public BreResponse execute(BreRequest breRequest) {
    F fact = getFact(breRequest);
    Set<IssueDetails> issues = new HashSet<>();
    
    // First execute validation rules
    Set<IssueDetails> validationIssues = breDroolsService.performBusinessRules(getDroolsConfiguration(getDroolsValidationAgenda()), fact);
    issues.addAll(validationIssues);
    
    // If no validation issues then execute data processing business rules
    if (validationIssues.isEmpty()) {      
      Set<IssueDetails> dataProcessingIssues = breDroolsService.performBusinessRules(getDroolsConfiguration(getDroolsDataProcessingAgenda()), fact);
      issues.addAll(dataProcessingIssues);      
    }
    
    BreResponse breResponse = new BreResponse();
    breResponse.setBusinessRuleName(getBusinessRuleName());
    breResponse.setIssues(issues);
    
    // Put the updated fact in response
    breResponse.setData(jacksonObjectMapper.convertValue(fact, JsonNode.class));
    
    return breResponse;
  }
  
  @Override
  public BusinessRuleDefinition getDefinition() {  
    if (this.businessRuleDefinition == null) {
      BusinessRuleDefinition def = new BusinessRuleDefinition();
      def.setBusinessRuleName(getBusinessRuleName());
      def.setDataClassName(getFactType().getName());
      def.setRules(getRules());
      def.setRequestSample(getSampleBreRequest());
      this.businessRuleDefinition = def;
    }
    
    return this.businessRuleDefinition;
  }
  
  public BusinessRuleSetDocumentation getDocumentation() {
    if (this.businessRuleDocumentation == null) {
      BusinessRuleSetDocumentation doc = new BusinessRuleSetDocumentation();
      doc.setBusinessRuleSetName(getBusinessRuleName());
      doc.setRulesDocumentation(getRuleDocumentation());
      doc.setDataClassName(getFactType().getName());
      this.businessRuleDocumentation = doc;
    }
    return this.businessRuleDocumentation;
    
  }
  
  protected abstract Class<F> getFactType();
  
  protected abstract BreRequest getSampleBreRequest();
  
  private F getFact(BreRequest breRequest) {
    F fact;
    try {      
      JsonNode data = breRequest.getData();      
      fact = jacksonObjectMapper.readValue(jacksonObjectMapper.writeValueAsString(data), getFactType());      
    } catch (Throwable t) {
      throw new BreException("Error reading business rule data for: " + getBusinessRuleName(), 
          t, breRequest, getDefinition());      
    }
    return fact;
  }
  
  private String getBusinessRuleName() {
    return this.getClass().getSimpleName();
  }
  
  private String getDroolsSessionName() {
    return getBusinessRuleName() + "-session";
  }
  
  private String getDroolsRulesPath() {
    return "rules/" + getBusinessRuleName();
  }
  
  private String getDroolsValidationAgenda() {
    return getBusinessRuleName() + "-validation-agenda";
  }
  
  private String getDroolsDataProcessingAgenda() {
    return getBusinessRuleName() + "-data-processing-agenda";
  }
  
  private List<BusinessRuleDefinition.Rule> getRules() {
    try {
      return breDroolsService.getRules(getDroolsConfiguration(null), getBusinessRuleName() + "-kbase");
    } catch (DroolsException e) {
      throw new BreException(e.getMessage(), e);
    }
  }
  
  private List<RuleDocumentation> getRuleDocumentation() {
    try {
      return breDroolsService.getRuleDocuments(getDroolsConfiguration(null), getBusinessRuleName() + "-kbase");
    } catch (DroolsException e) {
      throw new BreException(e.getMessage(), e);
    }
  }
  
  @SuppressWarnings("rawtypes")
  private DroolsConfiguration getDroolsConfiguration(String agenda) {
    return new DroolsConfiguration(getDroolsSessionName(), agenda, getDroolsRulesPath());
  }
}
