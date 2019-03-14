package gov.ca.cwds.bre.interfaces.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.model.ExecutionTimeRecorder;
import gov.ca.cwds.cares.common.model.ObjectBase;
import gov.ca.cwds.rest.exception.IssueDetails;

/**
 * @author CWDS J Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class BreResponse extends ObjectBase implements ExecutionTimeRecorder {
  
  private Long executionTimeMillis;
  private String businessRuleSetName;
  private Set<IssueDetails> issues = new HashSet<>();
  private List<BreResponseData> dataObjects = new ArrayList<>();

  public String getBusinessRuleSetName() {
    return businessRuleSetName;
  }

  public void setBusinessRuleSetName(String businessRuleSetName) {
    this.businessRuleSetName = businessRuleSetName;
  }

  public Set<IssueDetails> getIssues() {
    return issues;
  }

  public void setIssues(Set<IssueDetails> issues) {
    this.issues = issues;
  }
  
  public void addIssue(IssueDetails issue) {
    this.issues.add(issue);
  }
  
  public List<BreResponseData> getDataObjects() {
    return dataObjects;
  }

  public void setDataObjects(List<BreResponseData> dataObjects) {
    this.dataObjects = dataObjects;
  }
  
  public void addDataObject(BreResponseData dataObject) {
    this.dataObjects.add(dataObject);
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
