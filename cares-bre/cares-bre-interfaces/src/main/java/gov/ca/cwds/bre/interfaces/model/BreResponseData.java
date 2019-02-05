package gov.ca.cwds.bre.interfaces.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.databind.JsonNode;

public class BreResponseData implements Serializable {

  private static final long serialVersionUID = -3377409645481859880L;
  
  private String dataClassName;
  private JsonNode dataNode;
  
  public BreResponseData() {
    // Default no-argument constructor.
  }

  public String getDataClassName() {
    return dataClassName;
  }

  public void setDataClassName(String dataClassName) {
    this.dataClassName = dataClassName;
  }

  public JsonNode getDataNode() {
    return dataNode;
  }

  public void setDataNode(JsonNode dataNode) {
    this.dataNode = dataNode;
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