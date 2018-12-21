package gov.ca.cwds.cics.model.client;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.binding.SnakeUpperCaseStrategy;
import gov.ca.cwds.cics.model.CicsRequest;

/**
 * CWDS J Team
 */
@JsonNaming(SnakeUpperCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CicsClientRequest implements CicsRequest {

  @JsonProperty("CLIENT-DATA")
  private ClientData clientData;
  
  public CicsClientRequest() {
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
