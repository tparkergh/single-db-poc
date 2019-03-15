package gov.ca.cwds.cics.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.binding.SnakeUpperCaseStrategy;
import gov.ca.cwds.cares.common.model.ObjectBase;

/**
 * CWDS J Team
 */
@JsonNaming(SnakeUpperCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CicsClientRequest extends ObjectBase implements CicsRequest {

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
}
