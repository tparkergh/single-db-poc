package gov.ca.cwds.cares.services;

import gov.ca.cwds.cares.common.exception.CicsException;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.DfhCommArea;
import gov.ca.cwds.cics.restclient.CicsReporterRestApiClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * CWDS J Team
 */
@RunWith(MockitoJUnitRunner.class)
public class CicsReporterServiceCallExecutorTest {
  @Mock
  private CicsReporterRestApiClient cicsReporterRestApiClient;

  @InjectMocks
  private CicsReporterServiceCallExecutor cicsReporterServiceCallExecutor;

  @Test
  public void when_executeServiceCall_then_Success() {
    CicsReporterRequest cicsReporterRequest = new CicsReporterRequest();

    CicsResponse cicsResponse = getSuccessCicsResponse();

    when(cicsReporterRestApiClient.createReporter(argThat(new ArgumentMatcher<CicsReporterRequest>() {
      @Override
      public boolean matches(CicsReporterRequest argument) {
        assertSame(cicsReporterRequest, argument);
        return true;
      }
    }))).thenReturn(cicsResponse);

    assertSame(cicsResponse, cicsReporterServiceCallExecutor.executeServiceCall(cicsReporterRequest));
    verify(cicsReporterRestApiClient).createReporter(any());
  }

  @Test(expected = CicsException.class)
  public void when_executeServiceCall_then_Exception() {
    CicsResponse cicsResponse = getExceptionCicsResponse();

    when(cicsReporterRestApiClient.createReporter(any())).thenReturn(cicsResponse);

    cicsReporterServiceCallExecutor.executeServiceCall(new CicsReporterRequest());
    verify(cicsReporterRestApiClient).createReporter(any());
  }

  private CicsResponse getSuccessCicsResponse() {
    CicsResponse cicsResponse = new CicsResponse();
    DfhCommArea dfhCommArea = new DfhCommArea();
    dfhCommArea.setProgReturnCode(0);
    cicsResponse.setDfhCommArea(dfhCommArea);
    return cicsResponse;
  }

  private CicsResponse getExceptionCicsResponse() {
    CicsResponse cicsResponse = new CicsResponse();
    DfhCommArea dfhCommArea = new DfhCommArea();
    dfhCommArea.setProgReturnCode(-1);
    dfhCommArea.setErrorMsgCode("test ErrorMsgCode");
    dfhCommArea.setErrorMsgPart1("test ErrorMsgPart1");
    dfhCommArea.setErrorMsgPart2("test ErrorMsgPart2");

    cicsResponse.setDfhCommArea(dfhCommArea);
    return cicsResponse;
  }
}