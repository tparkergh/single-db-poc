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
public class BreResponseData extends ObjectBase implements Serializable {

  private static final long serialVersionUID = -3377409645481859880L;
  
  private String dataObjectClassName;
  private JsonNode dataObject;
  
  public BreResponseData() {
    // Default no-argument constructor.
  }

  public String getDataObjectClassName() {
    return dataObjectClassName;
  }

  public void setDataObjectClassName(String dataObjectClassName) {
    this.dataObjectClassName = dataObjectClassName;
  }

  public JsonNode getDataObject() {
    return dataObject;
  }

  public void setDataObject(JsonNode dataObject) {
    this.dataObject = dataObject;
  }
}