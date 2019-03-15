package gov.ca.cwds.bre.rest.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;

import gov.ca.cwds.rest.exception.IssueDetails;
import gov.ca.cwds.rest.exception.IssueType;

@ControllerAdvice
@RestController
public class BreResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(BreResponseEntityExceptionHandler.class);
  public static final String BUSINESS_RULE_RESPONSE_ENCOUNTERED_AN_EXCEPTION = "Business Rule Response encountered an Exception";

  @ExceptionHandler(BreException.class)
  public final ResponseEntity<BreResponse> handleBreException(BreException ex, WebRequest request) {    
    LOGGER.error(BUSINESS_RULE_RESPONSE_ENCOUNTERED_AN_EXCEPTION, ex);
    IssueDetails issue = new IssueDetails();   
    issue.setType(IssueType.BUSINESS_VALIDATION);    
    issue.setUserMessage(ex.getMessage());
    
    issue.setStackTrace(ExceptionUtils.getStackTrace(ex));
    
    BreResponse breResponse = new BreResponse();
    BreRequest breRequest = ex.getBreRequest();
    breResponse.setBusinessRuleSetName(breRequest != null ? breRequest.getBusinessRuleSetName() : null);
    breResponse.addIssue(issue);
    
    return new ResponseEntity<>(breResponse, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<IssueDetails> handleCatchAllException(Exception ex, WebRequest request) {
    LOGGER.error(BUSINESS_RULE_RESPONSE_ENCOUNTERED_AN_EXCEPTION, ex);
    IssueDetails issue = new IssueDetails();
    issue.setType(IssueType.BUSINESS_VALIDATION);
    issue.setUserMessage(ex.getMessage());
    issue.setStackTrace(ExceptionUtils.getStackTrace(ex));
    return new ResponseEntity<>(issue, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
