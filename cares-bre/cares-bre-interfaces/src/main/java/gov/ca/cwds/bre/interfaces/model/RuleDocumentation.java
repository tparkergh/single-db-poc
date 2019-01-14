package gov.ca.cwds.bre.interfaces.model;

import java.io.Serializable;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class RuleDocumentation implements Serializable {

  private static final long serialVersionUID = 9124183648641028487L;

  private String name;
  private TreeMap<String, String> documentation = new TreeMap<>();

  public RuleDocumentation() {    
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TreeMap<String, String> getDocumentation() {
    return documentation;
  }

  public void setDocumentation(TreeMap<String, String> documentation) {
    this.documentation = documentation;
  }
  
  public void addDocumentation(String key, String value) {
    this.documentation.put(key, value);
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
