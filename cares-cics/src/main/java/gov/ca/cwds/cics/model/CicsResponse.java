package gov.ca.cwds.cics.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.cares.common.model.ExecutionTimeRecorder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CicsResponse implements ExecutionTimeRecorder {

  private Long executionTimeMillis;
  
  @JsonProperty("DFHCOMMAREA")
  private DfhCommArea dfhCommArea;

  public CicsResponse() {
    // No-argument constructor.
  }
  
  @JsonProperty("DFHCOMMAREA")
  public DfhCommArea getDfhCommArea() {
    return dfhCommArea;
  }

  @JsonProperty("DFHCOMMAREA")
  public void setDfhCommArea(DfhCommArea dfhCommArea) {
    this.dfhCommArea = dfhCommArea;
  }
  
  @Override
  public Long getExecutionTimeMillis() {
    return executionTimeMillis;
  }

  @Override
  public void setExecutionTimeMillis(Long executionTimeMillis) {
    this.executionTimeMillis = executionTimeMillis;
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
