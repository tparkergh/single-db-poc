package gov.ca.cwds.cares.common.exception;

/**
 * CWDS J Team
 */
public class CicsException extends RuntimeException {
  public CicsException(String message) {
    super(message);
  }

  public CicsException(String message, Throwable cause) {
    super(message, cause);
  }
}
