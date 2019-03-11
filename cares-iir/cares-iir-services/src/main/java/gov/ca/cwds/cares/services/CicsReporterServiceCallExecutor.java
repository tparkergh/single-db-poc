package gov.ca.cwds.cares.services;

import gov.ca.cwds.cares.common.exception.CicsException;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.DfhCommArea;
import gov.ca.cwds.cics.restclient.CicsReporterRestApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CWDS J Team
 */
@Component("CicsReporterServiceCallExecutor")
public class CicsReporterServiceCallExecutor implements CicsServiceCallExecutor<CicsReporterRequest> {
  @Autowired
  private CicsReporterRestApiClient cicsReporterRestApiClient;

  @Override
  public CicsResponse executeServiceCall(CicsReporterRequest request) {
    CicsResponse cicsReporterResponse = cicsReporterRestApiClient.createReporter(request);
    DfhCommArea dfhCommArea = cicsReporterResponse.getDfhCommArea();
    if (0 != dfhCommArea.getProgReturnCode()) {
      String message = String.format("Cannot create Reporter. Error code %s. Message: %s%s ",
          dfhCommArea.getErrorMsgCode(), dfhCommArea.getErrorMsgPart1(), dfhCommArea.getErrorMsgPart2());
      throw new CicsException(message);
    }
    return cicsReporterResponse;
  }
}
