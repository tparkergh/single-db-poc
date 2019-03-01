package gov.ca.cwds.cares.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.api.BusinessRuleService;
import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreRequestData;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cics.model.ReporterData;
import gov.ca.cwds.rest.exception.IssueDetails;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * CWDS J Team
 */
@Component
public class BusinessRulesExecutorImpl implements BusinessRulesExecutor<BreResponse, ReporterData> {
  @Autowired
  private ObjectMapper jacksonObjectMapper;

  @Autowired
  @Qualifier("BreRestApiClient")
  private BusinessRuleService businessRuleService;

  @Override
  public BreResponse executeBusinessRules(String ruleName, ReporterData fact) {
    BreRequestData breRequestData = new BreRequestData();
    breRequestData.setDataObjectClassName(ReporterData.class.getName());
    breRequestData.setDataObject(jacksonObjectMapper.convertValue(fact, JsonNode.class));

    BreRequest breRequest = new BreRequest();
    breRequest.setBusinessRuleSetName(ruleName);
    breRequest.addDataObject(breRequestData);

    BreResponse breResponse;
    try {
      breResponse = businessRuleService.executeBusinessRules(breRequest);
    } catch (Exception e) {
      throw new BreException("BRE service exception: " + e.getMessage());
    }
    Set<IssueDetails> issues = breResponse.getIssues();

    if (issues != null && !issues.isEmpty()) {
      BreException breException = new BreException("BRE issues found executing business rule set: " + breResponse.getBusinessRuleSetName());
      breException.setBreResponse(breResponse);
      throw breException;
    }
    return breResponse;
  }

  public void setJacksonObjectMapper(ObjectMapper jacksonObjectMapper) {
    this.jacksonObjectMapper = jacksonObjectMapper;
  }
}
