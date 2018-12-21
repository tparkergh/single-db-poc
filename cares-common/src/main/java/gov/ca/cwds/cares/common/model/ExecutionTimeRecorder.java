package gov.ca.cwds.cares.common.model;

public interface ExecutionTimeRecorder {
  
  Long getExecutionTimeMillis();

  void setExecutionTimeMillis(Long executionTimeMillis);
}