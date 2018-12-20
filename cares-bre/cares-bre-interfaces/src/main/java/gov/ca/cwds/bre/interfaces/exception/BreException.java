package gov.ca.cwds.bre.interfaces.exception;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition;

/**
 * Base RuntimeException of BRE exceptions.
 * 
 * @author CWDS J Team
 */
public class BreException extends RuntimeException {

  private static final long serialVersionUID = -7751948785552395397L;

  private BreRequest breRequest;
  private BusinessRuleDefinition businessRuleDescription; 
  
  /**
   * Construct a generic exception.
   */
  public BreException() {
    super();
  }

  /**
   * Construct with a message.
   * 
   * @param message message to display
   */
  public BreException(String message) {
    super(message);
  }

  /**
   * Construct with a Throwable.
   * 
   * @param cause Throwable to re-throw
   */
  public BreException(Throwable cause) {
    super(cause);
  }

  /**
   * Construct with a message and Throwable to wrap and re-throw.
   * 
   * @param message Message
   * @param cause Throwable to re-throw
   */
  public BreException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public BreException(String message, Throwable cause, BreRequest breRequest, BusinessRuleDefinition businessRuleDescription) {
    super(message, cause);
    this.breRequest = breRequest;
    this.businessRuleDescription = businessRuleDescription;
  }

  public BreRequest getBreRequest() {
    return breRequest;
  }

  public void setBreRequest(BreRequest breRequest) {
    this.breRequest = breRequest;
  }

  public BusinessRuleDefinition getBusinessRuleDescription() {
    return businessRuleDescription;
  }

  public void setBusinessRuleDescription(BusinessRuleDefinition businessRuleDescription) {
    this.businessRuleDescription = businessRuleDescription;
  }

  /**
   * Construct and control stack traces.
   * 
   * @param message Message
   * @param cause Throwable to re-throw
   * @param enableSuppression If the JVM can suppress this exception
   * @param writableStackTrace If security permits, the JVM may log the stack trace
   */
  public BreException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  /**
   * {@inheritDoc}
   * 
   * @see Object#hashCode()
   */
  @Override
  public final int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, false);
  }

  /**
   * {@inheritDoc}
   *
   * @see Object#equals(Object)
   */
  @Override
  public final boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj, false);
  }
}
