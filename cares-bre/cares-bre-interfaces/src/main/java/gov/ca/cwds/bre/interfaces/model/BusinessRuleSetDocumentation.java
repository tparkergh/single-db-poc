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
public class BusinessRuleSetDocumentation extends ObjectBase implements Serializable {

  private static final long serialVersionUID = 8524019465390885796L;
  
  private String businessRuleSetName;
  private String dataClassName;
  private List<RuleDocumentation> rules = new ArrayList<>();
  
  public BusinessRuleSetDocumentation() {
    super();
  }

  public String getBusinessRuleSetName() {
    return businessRuleSetName;
  }

  public void setBusinessRuleSetName(String businessRuleName) {
    this.businessRuleSetName = businessRuleName;
  }

  public void setDataClassName(String dataClassName) {
    this.dataClassName = dataClassName;
  }
  
  public String getDataClassName() {
    return dataClassName;
  }
  
  public List<RuleDocumentation> getRules() {
    return rules;
  }

  public void setRules(List<RuleDocumentation> rules) {
    this.rules = rules;
  }
  
  public void addRule(RuleDocumentation rule) {
    if (rule != null) {
      this.rules.add(rule);
    }
  }
}
