package gov.ca.cwds.cics.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.binding.SnakeUpperCaseStrategy;
import gov.ca.cwds.cares.common.model.ObjectBase;

/**
 * CWDS J Team
 */
@JsonNaming(SnakeUpperCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CicsAddressRequest extends ObjectBase implements CicsRequest {
  private AddressData addressData;

  public CicsAddressRequest() {
  }

  public AddressData getAddressData() {
    return addressData;
  }

  public void setAddressData(AddressData addressData) {
    this.addressData = addressData;
  }
}
