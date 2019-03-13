package gov.ca.cwds.cares.interfaces.model.people;

import gov.ca.cwds.cares.interfaces.model.BaseModel;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.interfaces.model.Address;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientAddress extends BaseModel implements Serializable {
  
  private static final long serialVersionUID = 5237768756638520258L;
  
  private String identifier;
  private Integer addressTytpeCode;
  private String bookingInmateId;
  private LocalDate effectiveStartDate;
  private LocalDate effectiveEndDate;
  private String lastUpdateId;
  private LocalDateTime lastUpdateTimestamp;
  private String addressId;
  private String clientId;
  private String referralId;
  private String homelessIndicator;
  private Address address;
  
  public ClientAddress() {
    // Default no-argument constructor
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public Integer getAddressTytpeCode() {
    return addressTytpeCode;
  }

  public void setAddressTytpeCode(Integer addressTytpeCode) {
    this.addressTytpeCode = addressTytpeCode;
  }

  public String getBookingInmateId() {
    return bookingInmateId;
  }

  public void setBookingInmateId(String bookingInmateId) {
    this.bookingInmateId = bookingInmateId;
  }

  public LocalDate getEffectiveStartDate() {
    return effectiveStartDate;
  }

  public void setEffectiveStartDate(LocalDate effectiveStartDate) {
    this.effectiveStartDate = effectiveStartDate;
  }

  public LocalDate getEffectiveEndDate() {
    return effectiveEndDate;
  }

  public void setEffectiveEndDate(LocalDate effectiveEndDate) {
    this.effectiveEndDate = effectiveEndDate;
  }

  public String getLastUpdateId() {
    return lastUpdateId;
  }

  public void setLastUpdateId(String lastUpdateId) {
    this.lastUpdateId = lastUpdateId;
  }

  public LocalDateTime getLastUpdateTimestamp() {
    return lastUpdateTimestamp;
  }

  public void setLastUpdateTimestamp(LocalDateTime lastUpdateTimestamp) {
    this.lastUpdateTimestamp = lastUpdateTimestamp;
  }

  public String getAddressId() {
    return addressId;
  }

  public void setAddressId(String addressId) {
    this.addressId = addressId;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getReferralId() {
    return referralId;
  }

  public void setReferralId(String referralId) {
    this.referralId = referralId;
  }

  public String getHomelessIndicator() {
    return homelessIndicator;
  }

  public void setHomelessIndicator(String homelessIndicator) {
    this.homelessIndicator = homelessIndicator;
  }
  
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
