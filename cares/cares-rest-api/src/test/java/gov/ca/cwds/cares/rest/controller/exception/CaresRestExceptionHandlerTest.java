package gov.ca.cwds.cares.rest.controller.exception;

import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cares.common.exception.CicsException;
import gov.ca.cwds.rest.exception.IssueDetails;
import gov.ca.cwds.rest.exception.IssueType;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CWDS J Team
 */
public class CaresRestExceptionHandlerTest {
  private CaresRestExceptionHandler exceptionHandler;

  @Before
  public void setUp() {
    exceptionHandler = new CaresRestExceptionHandler();
  }

  @Test
  public void shouldHaveUserMessageWhenCicsException() throws Exception {
    String message = "test message";
    CicsException ex = new CicsException(message);

    Set<IssueDetails> detailsSet = exceptionHandler.cicsExceptionHandler(ex);
    IssueDetails issueDetails = detailsSet.iterator().next();
    assertEquals(message, issueDetails.getUserMessage());
    assertEquals(IssueType.EXPECTED_EXCEPTION, issueDetails.getType());
  }

  @Test
  public void shouldHaveUserMessageWhenBreExceptionWithNoIssueDetails() throws Exception {
    String message = "test message";
    BreException ex = new BreException(message);

    Set<IssueDetails> detailsSet = exceptionHandler.breExceptionHandler(ex);
    IssueDetails issueDetails = detailsSet.iterator().next();
    assertEquals(message, issueDetails.getUserMessage());
    assertEquals(IssueType.EXPECTED_EXCEPTION, issueDetails.getType());
  }

  @Test
  public void shouldHaveAllDataWhenBreExceptionWithIssueDetails() throws Exception {
    String code = "test Code";
    String causeStackTrace = "test CauseStackTrace";
    String incidentId = "test IncidentId";
    String invalidValue = "test InvalidValue";
    String property = "test Property";
    String stackTrace = "test StackTrace";
    String technicalMessage = "test TechnicalMessage";
    String userMessage = "test UserMessage";
    IssueType type = IssueType.BUSINESS_VALIDATION;

    IssueDetails details = new IssueDetails();
    details.setCode(code);
    details.setCauseStackTrace(causeStackTrace);
    details.setIncidentId(incidentId);
    details.setInvalidValue(invalidValue);
    details.setProperty(property);
    details.setStackTrace(stackTrace);
    details.setTechnicalMessage(technicalMessage);
    details.setType(type);
    details.setUserMessage(userMessage);
    Set<IssueDetails> detailsSet = new HashSet<>();
    detailsSet.add(details);
    BreResponse breResponse = new BreResponse();
    breResponse.setIssues(detailsSet);

    BreException ex = new BreException();
    ex.setBreResponse(breResponse);

    Set<IssueDetails> issueDetailsSet = exceptionHandler.breExceptionHandler(ex);
    IssueDetails issueDetails = issueDetailsSet.iterator().next();
    assertEquals(code, issueDetails.getCode());
    assertEquals(causeStackTrace, issueDetails.getCauseStackTrace());
    assertEquals(incidentId, issueDetails.getIncidentId());
    assertEquals(invalidValue, issueDetails.getInvalidValue());
    assertEquals(property, issueDetails.getProperty());
    assertEquals(stackTrace, issueDetails.getStackTrace());
    assertEquals(technicalMessage, issueDetails.getTechnicalMessage());
    assertEquals(type, issueDetails.getType());
    assertEquals(userMessage, issueDetails.getUserMessage());
  }
}