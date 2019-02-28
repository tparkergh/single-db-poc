package gov.ca.cwds.cics.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * CWDS J Team
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CicsReporterRequest implements CicsRequest {
  @JsonProperty("REPORTER-DATA")
  private ReporterData reporterData;

  public CicsReporterRequest() {
  }

  public ReporterData getReporterData() {
    return reporterData;
  }

  public void setReporterData(ReporterData reporterData) {
    this.reporterData = reporterData;
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }
}
