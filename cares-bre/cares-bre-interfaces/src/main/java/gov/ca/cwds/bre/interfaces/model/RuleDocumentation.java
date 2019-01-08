package gov.ca.cwds.bre.interfaces.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleDocumentation {

  private Map <String, String> ruleDocument = new HashMap<>();
    
  public RuleDocumentation() {
    super();
  }
  
  public RuleDocumentation(Map<String, String> ruleDocumentation) {
    this.ruleDocument = ruleDocumentation;
  }
  

  public Map<String, String> getRuleDocument() {
    return this.ruleDocument;
  }
  
  public void setRuleDocument(Map<String, String> ruleDocumentation) {
    this.ruleDocument = ruleDocumentation;
  }
  
  public void setRuleDocumentFromRuleMetaData(Map<String, Object> ruleMeta) {
    
    if (ruleMeta != null) {
      
      Set<Map.Entry< String, Object > > ruleSet = ruleMeta.entrySet();
      
      for (Map.Entry<String, Object> me : ruleSet) {
        // do not return null items or items with empty value
        if (me.getValue() != null && me.getValue().toString().length() > 0) {
    
          if (me.getKey().startsWith("doc_")) {
            // return items with @doc_ notation
            this.ruleDocument.put(me.getKey(), (String)me.getValue());
          }
        }
      }
    }
  }
  
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
  
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

}
