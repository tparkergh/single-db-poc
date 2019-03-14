package gov.ca.cwds.cics.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.binding.SnakeUpperCaseStrategy;
import gov.ca.cwds.cares.common.model.ObjectBase;

/**
 * @deprecated Use ClientData
 *
 * @author CWDS J Team
 */
@JsonNaming(SnakeUpperCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class Client extends ObjectBase implements Serializable {
  
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
}
