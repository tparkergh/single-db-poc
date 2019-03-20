package gov.ca.cwds.cares.rest.controller.exception;

import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cares.common.exception.CicsException;
import gov.ca.cwds.cares.common.exception.DataIntegrityException;
import gov.ca.cwds.rest.exception.IssueDetails;
import gov.ca.cwds.rest.exception.IssueType;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import gov.ca.cwds.bre.interfaces.exception.BreException;

/**
 * CWDS J Team
 */
@ControllerAdvice
public class CaresRestExceptionHandler {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(CaresRestExceptionHandler.class);

  @ResponseBody
  @ExceptionHandler(CicsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  Set<IssueDetails> cicsExceptionHandler(CicsException ex) {
    LOGGER.error("CICS encountered the following Exception:", ex);
    return createSingleMessageException(ex);
  }

  @ResponseBody
  @ExceptionHandler(DataIntegrityException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  Set<IssueDetails> databaseIntegrityExceptionHandler(DataIntegrityException ex) {
    LOGGER.error("Data integrity issue is found", ex);
    return createSingleMessageException(ex);
  }

  @ResponseBody
  @ExceptionHandler(BreException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  Set<IssueDetails> breExceptionHandler(BreException ex) {
    LOGGER.error("The Business Rules encountered the following exception", ex);
    Set<IssueDetails> issueDetailsSet = new HashSet<>();
    BreResponse breResponse = ex.getBreResponse();
    if (breResponse != null) {
      issueDetailsSet.addAll(ex.getBreResponse().getIssues());
    } else {
      IssueDetails issueDetails = new IssueDetails();
      issueDetails.setType(IssueType.EXPECTED_EXCEPTION);
      issueDetails.setUserMessage(ex.getMessage());
      Throwable causeEx = ex.getCause();
      if (causeEx != null) {
        issueDetails.setTechnicalMessage(causeEx.getMessage());
      }
      issueDetailsSet.add(issueDetails);
    }
    return issueDetailsSet;
  }

  private Set<IssueDetails> createSingleMessageException(Exception ex) {
    Set<IssueDetails> issueDetailsSet = new HashSet<>();
    IssueDetails issueDetails = new IssueDetails();
    issueDetails.setUserMessage(ex.getMessage());
    issueDetails.setType(IssueType.EXPECTED_EXCEPTION);
    issueDetailsSet.add(issueDetails);
    return issueDetailsSet;
  }
}
