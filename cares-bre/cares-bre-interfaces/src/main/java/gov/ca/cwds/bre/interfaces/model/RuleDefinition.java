package gov.ca.cwds.bre.interfaces.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.model.ObjectBase;

/**
 * @author CWDS J Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleDefinition extends ObjectBase implements Serializable {

  private static final long serialVersionUID = -5470357355794537414L;
  
  private String name;
  private JsonNode logic;
  
  public RuleDefinition() {
    // Default no-argument constructor
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public JsonNode getLogic() {
    return logic;
  }

  public void setLogic(JsonNode logic) {
    this.logic = logic;
  }
}
