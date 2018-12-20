package gov.ca.cwds.bre.interfaces.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.EnumUtils;
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
public class BusinessRuleDefinition implements Serializable {

  private static final long serialVersionUID = 9005597804536927834L;
  
  private String businessRuleName;
  private List<Rule> rules = new ArrayList<>();
  private String dataClassName;
  private BreRequest requestSample;
  
  public String getBusinessRuleName() {
    return businessRuleName;
  }

  public void setBusinessRuleName(String businessRuleName) {
    this.businessRuleName = businessRuleName;
  }

  public List<Rule> getRules() {
    return rules;
  }

  public void setRules(List<Rule> rules) {
    this.rules = rules;
  }
  
  public void addRule(Rule rule) {
    this.rules.add(rule);
  }

  public String getDataClassName() {
    return dataClassName;
  }
  
  public void setDataClassName(String dataClassName) {
    this.dataClassName = dataClassName;
  }
  
  public BreRequest getRequestSample() {
    return requestSample;
  }

  public void setRequestSample(BreRequest requestSample) {
    this.requestSample = requestSample;
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
  
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static final class Rule implements Serializable {
    
    public enum Type {
      VALIDATION, DATA_PROCESSING;
      
      public static Type fromName(String typeName) {
        return EnumUtils.getEnum(Type.class, typeName);
      }
    }
    
    private static final long serialVersionUID = 9124183648641028487L;
    
    private String name;
    private Type type;
    private String description;

    public Rule(String name, Type type, String description) {
      this.name = name;
      this.type = type;
      this.description = description;
    }
    
    public String getName() {
      return name;
    }

    public String getDescription() {
      return description;
    }

    public Type getType() {
      return type;
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
}
