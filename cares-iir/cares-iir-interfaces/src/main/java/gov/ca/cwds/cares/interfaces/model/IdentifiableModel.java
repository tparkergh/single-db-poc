package gov.ca.cwds.cares.interfaces.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gov.ca.cwds.cares.common.binding.IdentifierSerializer;
import gov.ca.cwds.cares.common.model.ObjectBase;

public class IdentifiableModel extends ObjectBase {
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
