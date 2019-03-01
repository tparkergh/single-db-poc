package gov.ca.cwds.cares.rest.controller.exception;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.cares.common.exception.CicsException;
import gov.ca.cwds.rest.exception.IssueDetails;

/**
 * CWDS J Team
 */
@ControllerAdvice
public class CaresRestExceptionHandler {
  
  private final static Logger LOGGER = LoggerFactory.getLogger(CaresRestExceptionHandler.class);

  
  @ResponseBody
  @ExceptionHandler(CicsException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  String cicsUpdateHandler(CicsException ex) {
    LOGGER.error(ex.getMessage(), ex);  
    return ex.getMessage();
  }
  
  @ResponseBody
  @ExceptionHandler(BreException.class)
  Set<IssueDetails> breExceptionHandler(BreException ex) {
    LOGGER.error(ex.getMessage(), ex);  
    return ex.getBreResponse().getIssues();
  }
}
