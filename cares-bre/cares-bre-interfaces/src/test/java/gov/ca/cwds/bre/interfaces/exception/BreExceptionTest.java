package gov.ca.cwds.bre.interfaces.exception;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;

public class BreExceptionTest {

  String message = "exception message";
  Throwable cause = new Throwable();

  @Test
  public void shouldConstructObjecWithDefault() throws Exception {
    BreException exception = new BreException();
    assertThat(exception, notNullValue());
  }

  @Test
  public void shouldConstructObjectWithMessage() throws Exception {
    BreException exception = new BreException(message);
    assertEquals(exception.getMessage(), message);
  }

  @Test
  public void shouldConstructObjectWithThrowable() throws Exception {
    BreException exception = new BreException(cause);
    assertEquals(exception.getCause(), cause);
  }

  @Test
  public void shouldConstructObjectWithThrowableMessage() throws Exception {
    BreException exception = new BreException(message, cause);
    assertEquals(exception.getMessage(), message);
    assertEquals(exception.getCause(), cause);
  }

  @Test
  public void shouldContructObjectWithMessageCauseSuppressionStackTrace() throws Exception {
    BreException exception = new BreException(message, cause, Boolean.TRUE, Boolean.FALSE);
    assertThat(exception, notNullValue());
  }

  @Test
  public void shouldGetSetBreRequest() throws Exception {
    BreRequest req = new BreRequest();
    String ruleSetName = "rule set name";
    req.setBusinessRuleSetName(ruleSetName);

    BreException exception = new BreException();
    exception.setBreRequest(req);
    assertEquals(exception.getBreRequest(), req);
  }

  @Test
  public void shouldGetSetBreResponse() throws Exception {
    BreResponse rep = new BreResponse();
    String ruleSetName = "rule set name";
    rep.setBusinessRuleSetName(ruleSetName);

    BreException exception = new BreException();
    exception.setBreResponse(rep);
    assertEquals(exception.getBreResponse(), rep);
  }

}
