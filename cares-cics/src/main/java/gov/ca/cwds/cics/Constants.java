package gov.ca.cwds.cics;

import java.time.format.DateTimeFormatter;

/**
 * CWDS J Team
 */
public class Constants {
  public static final DateTimeFormatter CICS_TIMESTAMP_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSSSSS");

  private Constants() {
  }
}
