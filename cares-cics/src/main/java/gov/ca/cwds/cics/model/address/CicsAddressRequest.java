package gov.ca.cwds.cics.model.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.binding.SnakeUpperCaseStrategy;
import gov.ca.cwds.cics.model.CicsRequest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * CWDS J Team
 */
@JsonNaming(SnakeUpperCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CicsAddressRequest implements CicsRequest {
  private AddressData addressData;

  public CicsAddressRequest() {
  }

  public AddressData getAddressData() {
    return addressData;
  }

  public void setAddressData(AddressData addressData) {
    this.addressData = addressData;
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
