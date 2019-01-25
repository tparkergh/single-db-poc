package gov.ca.cwds.cares.rest.controller.exception;

import gov.ca.cwds.cares.common.exception.CicsUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CWDS J Team
 */
@ControllerAdvice
public class CaresRestExceptionHandler {
  @ResponseBody
  @ExceptionHandler(CicsUpdateException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  String cicsUpdateHandler(CicsUpdateException ex) {
    return ex.getMessage();
  }
}
