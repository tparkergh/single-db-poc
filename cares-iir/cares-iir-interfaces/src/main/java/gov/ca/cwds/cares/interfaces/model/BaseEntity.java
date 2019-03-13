package gov.ca.cwds.cares.interfaces.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gov.ca.cwds.cares.common.binding.IdentifierSerializer;

public class BaseEntity extends BaseModel {
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
