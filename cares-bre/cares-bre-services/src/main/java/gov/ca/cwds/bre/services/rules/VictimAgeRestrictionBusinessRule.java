package gov.ca.cwds.bre.services.rules;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition.Rule;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition.Rule.Type;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDocumentation;
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
    
    breResponse.setBusinessRuleName(breRequest.getBusinessRuleName());
    breResponse.setData(breRequest.getData());
    return breResponse;
  }

  @Override
  public BusinessRuleDefinition getDefinition() {
    BusinessRuleDefinition desc = new BusinessRuleDefinition();
    desc.setBusinessRuleName("VictimAgeRestrictionBusinessRule");
    desc.addRule(new Rule("age-validation", Type.VALIDATION, "Age must be less that 18 years. DOB must be provided in this formt: yyyy-MM-dd"));
    desc.setDataClassName(LocalDate.class.getName());
    
    BreRequest breRequest = new BreRequest();
    breRequest.setBusinessRuleName("VictimAgeRestrictionBusinessRule");
    breRequest.setData(jacksonObjectMapper.convertValue(LocalDate.now(), JsonNode.class));
    desc.setRequestSample(breRequest);
    
    return desc;
  }
  
  private LocalDate getDob(BreRequest breRequest) {
    LocalDate clientDob;
    try {      
      JsonNode data = breRequest.getData();      
      clientDob = jacksonObjectMapper.readValue(jacksonObjectMapper.writeValueAsString(data), LocalDate.class);      
    } catch (Throwable t) {
      throw new BreException("Error reading business rule data for: VictimAgeRestrictionBusinessRule", 
          t, breRequest, getDefinition());      
    }
    return clientDob;
  }

  @Override
  public BusinessRuleDocumentation getDocumentation() {
    // TODO Auto-generated method stub
    return null;
  }
}
