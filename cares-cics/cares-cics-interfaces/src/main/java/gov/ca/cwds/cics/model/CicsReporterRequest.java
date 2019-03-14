package gov.ca.cwds.cics.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.cares.common.model.ObjectBase;

/**
 * CWDS J Team
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CicsReporterRequest extends ObjectBase implements CicsRequest {
  @JsonProperty("REPORTER_DATA")
  private ReporterData reporterData;

  public CicsReporterRequest() {
  }

  public ReporterData getReporterData() {
    return reporterData;
  }

  public void setReporterData(ReporterData reporterData) {
    this.reporterData = reporterData;
  }
}
