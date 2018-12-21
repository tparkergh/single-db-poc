package gov.ca.cwds.cics.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import gov.ca.cwds.cares.common.model.ExecutionTimeRecorder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CicsResponse implements ExecutionTimeRecorder {

  private Long executionTimeMillis;

  public Long getExecutionTimeMillis() {
    return executionTimeMillis;
  }

  public void setExecutionTimeMillis(Long executionTimeMillis) {
    this.executionTimeMillis = executionTimeMillis;
  }
}
