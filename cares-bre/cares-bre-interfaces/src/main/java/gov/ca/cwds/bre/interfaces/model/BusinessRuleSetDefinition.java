package gov.ca.cwds.bre.interfaces.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author CWDS J Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessRuleSetDefinition implements Serializable {

  private static final long serialVersionUID = -1734220717854791420L;
  
  private String businessRuleSetName;
  private List<RuleDefinition> rules = new ArrayList<>();
  
  public BusinessRuleSetDefinition() {
    // Default no-argument constructor
  }
  
  public String getBusinessRuleSetName() {
    return businessRuleSetName;
  }

  public void setBusinessRuleSetName(String businessRuleSetName) {
    this.businessRuleSetName = businessRuleSetName;
  }

  public List<RuleDefinition> getRules() {
    return rules;
  }

  public void setRules(List<RuleDefinition> rules) {
    this.rules = rules;
  }
  
  public void addRule(RuleDefinition rule) {
    this.rules.add(rule);
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
