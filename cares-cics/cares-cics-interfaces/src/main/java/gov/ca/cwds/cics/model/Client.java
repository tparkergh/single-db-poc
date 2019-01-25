package gov.ca.cwds.cics.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.binding.SnakeUpperCaseStrategy;

/**
 * @deprecated Use ClientData
 *
 * @author CWDS J Team
 */
@JsonNaming(SnakeUpperCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class Client implements Serializable {
  
  private static final long serialVersionUID = -3135178657250529469L;

  @JsonProperty("CLIENT-DATA")
  private ClientData clientData;
  
  @JsonProperty("DFHCOMMAREA")
  private DfhCommArea dfhCommArea;
  
  public Client() {
    // Default no-argument constructor
  }

  @JsonProperty("CLIENT-DATA")
  public ClientData getClientData() {
    return clientData;
  }

  @JsonProperty("CLIENT-DATA")
  public void setClientData(ClientData clientData) {
    this.clientData = clientData;
  }

  @JsonProperty("DFHCOMMAREA")
  public DfhCommArea getDfhCommArea() {
    return dfhCommArea;
  }

  @JsonProperty("DFHCOMMAREA")
  public void setDfhCommArea(DfhCommArea dfhCommArea) {
    this.dfhCommArea = dfhCommArea;
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
