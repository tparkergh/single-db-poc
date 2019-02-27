package gov.ca.cwds.cares.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.api.BusinessRuleService;
import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreRequestData;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cares.common.exception.CicsUpdateException;
import gov.ca.cwds.cares.interfaces.api.ReporterService;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cares.services.mapping.ReporterMapper;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.DfhCommArea;
import gov.ca.cwds.cics.model.ReporterData;
import gov.ca.cwds.cics.restclient.CicsReporterRestApiClient;
import gov.ca.cwds.rest.exception.IssueDetails;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * CWDS J Team
 */
@Service
public class ReporterServiceImpl implements ReporterService {
  @Autowired
  private ObjectMapper jacksonObjectMapper;

  @Autowired
  @Qualifier("BreRestApiClient")
  private BusinessRuleService businessRuleService;

  @Autowired
  private CicsReporterRestApiClient cicsReporterRestApiClient;

  @Override
  public Reporter createReporter(Reporter reporter) {
    ReporterData reporterData = ReporterMapper.INSTANCE.mapToReporterData(reporter);

    executeBusinessRules(reporterData);

    CicsReporterRequest cicsAddressRequest = new CicsReporterRequest();
    cicsAddressRequest.setReporterData(reporterData);
    doCicsUpdateClientAddress(cicsAddressRequest);
    return reporter;
  }

  private void executeBusinessRules(ReporterData reporterData) {
    BreRequestData breRequestData = new BreRequestData();
    breRequestData.setDataObjectClassName(ReporterData.class.getName());
    breRequestData.setDataObject(jacksonObjectMapper.convertValue(reporterData, JsonNode.class));

    BreRequest breRequest = new BreRequest();
    breRequest.setBusinessRuleSetName("ReporterCreateScreenBusinessRules");
    breRequest.addDataObject(breRequestData);

    BreResponse breResponse = businessRuleService.executeBusinessRules(breRequest);
    Set<IssueDetails> issues = breResponse.getIssues();

    if (issues != null && !issues.isEmpty()) {
      BreException breException = new BreException("BRE issues found executing business rule set: " + breResponse.getBusinessRuleSetName());
      breException.setBreResponse(breResponse);
      throw breException;
    }
  }

  private CicsResponse doCicsUpdateClientAddress(CicsReporterRequest cicsAddressRequest) {
    CicsResponse cicsAddressResponse = cicsReporterRestApiClient.createReporter(cicsAddressRequest);
    DfhCommArea dfhCommArea = cicsAddressResponse.getDfhCommArea();
    if (0 != dfhCommArea.getProgReturnCode()) {
      String message = String.format("Cannot create reporter. Error code %s. Message: %s%s ",
          dfhCommArea.getErrorMsgCode(), dfhCommArea.getErrorMsgPart1(), dfhCommArea.getErrorMsgPart2());
      throw new CicsUpdateException(message);
    }
    return cicsAddressResponse;
  }

  public void setJacksonObjectMapper(ObjectMapper jacksonObjectMapper) {
    this.jacksonObjectMapper = jacksonObjectMapper;
  }
}
