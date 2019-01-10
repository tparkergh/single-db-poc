package gov.ca.cwds.bre.interfaces.model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.lang3.builder.EqualsBuilder;
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

  public static final String DOC_NOTATION = "doc_";
  
  private TreeMap <String, String> ruleDocument = new TreeMap<>();
    
  public RuleDocumentation() {
    super();
  }
  
  public RuleDocumentation(Map<String, String> ruleDocumentation) {
    this.ruleDocument = new TreeMap<>(ruleDocumentation);
  }

  public Map<String, String> getRuleDocument() {
    return this.ruleDocument;
  }
  
  public void setRuleDocument(Map<String, String> ruleDocumentation) {
    this.ruleDocument = new TreeMap<>(ruleDocumentation);
  }
  
  public void setRuleDocumentFromRuleMetaData(Map<String, Object> ruleMeta) {
    
    if (ruleMeta != null) {
      
      Set<Entry<String, Object>> ruleSet = ruleMeta.entrySet();
      
      for (Map.Entry<String, Object> mappedRule : ruleSet) {
        // do not return null items or items with empty value
        if (mappedRule.getValue() != null && mappedRule.getValue().toString().length() > 0) {
    
          if (mappedRule.getKey().startsWith(DOC_NOTATION)) {
            // return items with @doc_ notation
            this.ruleDocument.put(mappedRule.getKey(), (String)mappedRule.getValue());
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
  
  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }


}
