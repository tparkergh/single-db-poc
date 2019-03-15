package gov.ca.cwds.cics.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.cares.common.model.ExecutionTimeRecorder;
import gov.ca.cwds.cares.common.model.ObjectBase;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CicsResponse extends ObjectBase implements ExecutionTimeRecorder {

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
}
