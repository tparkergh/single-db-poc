package gov.ca.cwds.bre.services.rules;

import java.io.IOException;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import gov.ca.cwds.rest.exception.IssueDetails;
import gov.ca.cwds.rest.exception.IssueType;

/**
 * @author CWDS J Team
 */
@Component("VictimAgeRestrictionBusinessRule")
public class VictimAgeRestrictionBusinessRule implements BusinessRule {
  
  @Autowired
  private ObjectMapper jacksonObjectMapper;

  @Override
  public BreResponse execute(BreRequest breRequest) {
    LocalDate victimDob = getDob(breRequest);
    LocalDate victimOverAgeDate = LocalDate.now().minusYears(18);
    
    BreResponse breResponse = new BreResponse();    
    
    if (victimDob == null || victimDob.isBefore(victimOverAgeDate)) {
      IssueDetails issue = new IssueDetails();
      issue.setType(IssueType.BUSINESS_VALIDATION);
      issue.setInvalidValue(victimDob);
      issue.setUserMessage("Victim age must be less than 18 years.");
      breResponse.addIssue(issue);
    } 
    
    breResponse.setBusinessRuleSetName(breRequest.getBusinessRuleSetName());
    BreResponseData breResponseData = new BreResponseData();
    breResponseData.setDataObjectClassName(LocalDate.class.getName());
    breResponseData.setDataObject(jacksonObjectMapper.convertValue(victimDob, JsonNode.class));
    breResponse.addDataObject(breResponseData);
    return breResponse;
  }

  private LocalDate getDob(BreRequest breRequest) {
    LocalDate clientDob;
    try {      
      BreRequestData breRequestData = breRequest.getDataObjects().get(0);      
      clientDob = jacksonObjectMapper.readValue(jacksonObjectMapper.writeValueAsString(breRequestData.getDataObject()), LocalDate.class);      
    } catch (IOException t) {
      throw new BreException("Error reading business rule data for: VictimAgeRestrictionBusinessRule", 
          t, breRequest);      
    }
    return clientDob;
  }

  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }

  @Override
  public BusinessRuleSetDocumentation getDocumentation() {
    BusinessRuleSetDocumentation doc = new BusinessRuleSetDocumentation();
    doc.setBusinessRuleSetName(this.getClass().getSimpleName());
    doc.setDataClassName(LocalDate.class.getName());
    
    RuleDocumentation rd = new RuleDocumentation();
    rd.setName(this.getClass().getSimpleName());
    rd.addDocumentation("doc_description", "Victim age must be less than 18 years");
    
    doc.addRule(rd);
    return doc;
  }
}
