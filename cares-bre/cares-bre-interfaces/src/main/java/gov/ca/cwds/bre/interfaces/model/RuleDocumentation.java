package gov.ca.cwds.bre.interfaces.model;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.model.ObjectBase;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleDocumentation extends ObjectBase implements Serializable {

  private static final long serialVersionUID = 9124183648641028487L;

  private String name;
  private Map<String, String> documentation = new TreeMap<>();

  public RuleDocumentation() {
    // Default no-argument constructor.
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map<String, String> getDocumentation() {
    return documentation;
  }

  public void setDocumentation(Map<String, String> documentation) {
    this.documentation = documentation;
  }
  
  public void addDocumentation(String key, String value) {
    this.documentation.put(key, value);
  }
}
