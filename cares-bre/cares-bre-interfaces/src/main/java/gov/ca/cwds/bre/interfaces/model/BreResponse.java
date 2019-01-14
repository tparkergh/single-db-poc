package gov.ca.cwds.bre.interfaces.model;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.model.ExecutionTimeRecorder;
import gov.ca.cwds.rest.exception.IssueDetails;

/**
 * @author CWDS J Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class BreResponse implements ExecutionTimeRecorder {
  
  private Long executionTimeMillis;
  private String businessRuleSetName;
  private Set<IssueDetails> issues = new HashSet<>();
  private JsonNode data;  

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

  public JsonNode getData() {
    return data;
  }

  public void setData(JsonNode data) {
    this.data = data;
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
