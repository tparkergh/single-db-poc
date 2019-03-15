package gov.ca.cwds.cares.interfaces.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gov.ca.cwds.cares.common.binding.IdentifierSerializer;
import java.io.Serializable;

public class IdentifiableModel extends BaseModel implements Serializable {
  /*
   * Entity 10 digit identifier
   */
  @JsonSerialize(using = IdentifierSerializer.class)
  private String identifier;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }
}
