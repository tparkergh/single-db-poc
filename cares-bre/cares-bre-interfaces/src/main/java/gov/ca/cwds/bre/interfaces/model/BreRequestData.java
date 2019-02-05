package gov.ca.cwds.bre.interfaces.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author CWDS J Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BreRequestData implements Serializable {

  private static final long serialVersionUID = -3377409645481859880L;
  
  private String dataObjectClassName;
  private JsonNode dataObject;
  
  public BreRequestData() {
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
