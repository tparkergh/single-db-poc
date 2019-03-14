package gov.ca.cwds.bre.interfaces.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.model.ObjectBase;

/**
 * @author CWDS J Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessRuleSetDefinition extends ObjectBase implements Serializable {

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
}
