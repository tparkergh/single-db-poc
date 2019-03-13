package gov.ca.cwds.cares.interfaces.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gov.ca.cwds.cares.common.binding.TrimmingSerializer;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address extends BaseModel implements Serializable {

  private static final long serialVersionUID = 9065397479160656666L;
  
  private String identifier;
  private Integer stateCode;
  private Integer zipCode;
  private Integer zipSuffix;
  @JsonSerialize(using = TrimmingSerializer.class)
  private String city;
  @JsonSerialize(using = TrimmingSerializer.class)
  private String streetName;
  private Integer streetType;
  @JsonSerialize(using = TrimmingSerializer.class)
  private String streetNumber;
  private String unitNumber;
  private Integer unitType;
  private String addressDescription;
  private String postStreetDirection;
  private String preStreetDirection;
  private Long emergencyPhoneNumber;
  private Integer emergencyPhoneExtension;
  private Long messagePhoneNumber;
  private Integer messagePhoneExtension;
  private Long primaryPhoneNumber;
  private Integer primaryPhoneExtension;
  private Integer governmentEntityCode;
  private String lastUpdateId;
  private LocalDateTime lastUpdateTimestamp;
  private String foreignAddressIndicator;
  private String addressHeader;
  private Double longitude;
  private Double latitude;


  public Address() {
    // Default no-argument constructor.
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public Integer getStateCode() {
    return stateCode;
  }

  public void setStateCode(Integer stateCode) {
    this.stateCode = stateCode;
  }

  public Integer getZipCode() {
    return zipCode;
  }

  public void setZipCode(Integer zipCode) {
    this.zipCode = zipCode;
  }

  public Integer getZipSuffix() {
    return zipSuffix;
  }

  public void setZipSuffix(Integer zipSuffix) {
    this.zipSuffix = zipSuffix;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public Integer getStreetType() {
    return streetType;
  }

  public void setStreetType(Integer streetType) {
    this.streetType = streetType;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  public String getUnitNumber() {
    return unitNumber;
  }

  public void setUnitNumber(String unitNumber) {
    this.unitNumber = unitNumber;
  }

  public Integer getUnitType() {
    return unitType;
  }

  public void setUnitType(Integer unitType) {
    this.unitType = unitType;
  }

  public String getAddressDescription() {
    return addressDescription;
  }

  public void setAddressDescription(String addressDescription) {
    this.addressDescription = addressDescription;
  }

  public String getPostStreetDirection() {
    return postStreetDirection;
  }

  public void setPostStreetDirection(String postStreetDirection) {
    this.postStreetDirection = postStreetDirection;
  }

  public String getPreStreetDirection() {
    return preStreetDirection;
  }

  public void setPreStreetDirection(String preStreetDirection) {
    this.preStreetDirection = preStreetDirection;
  }

  public Long getEmergencyPhoneNumber() {
    return emergencyPhoneNumber;
  }

  public void setEmergencyPhoneNumber(Long emergencyPhoneNumber) {
    this.emergencyPhoneNumber = emergencyPhoneNumber;
  }

  public Integer getEmergencyPhoneExtension() {
    return emergencyPhoneExtension;
  }

  public void setEmergencyPhoneExtension(Integer emergencyPhoneExtension) {
    this.emergencyPhoneExtension = emergencyPhoneExtension;
  }

  public Long getMessagePhoneNumber() {
    return messagePhoneNumber;
  }

  public void setMessagePhoneNumber(Long messagePhoneNumber) {
    this.messagePhoneNumber = messagePhoneNumber;
  }

  public Integer getMessagePhoneExtension() {
    return messagePhoneExtension;
  }

  public void setMessagePhoneExtension(Integer messagePhoneExtension) {
    this.messagePhoneExtension = messagePhoneExtension;
  }

  public Long getPrimaryPhoneNumber() {
    return primaryPhoneNumber;
  }

  public void setPrimaryPhoneNumber(Long primaryPhoneNumber) {
    this.primaryPhoneNumber = primaryPhoneNumber;
  }

  public Integer getPrimaryPhoneExtension() {
    return primaryPhoneExtension;
  }

  public void setPrimaryPhoneExtension(Integer primaryPhoneExtension) {
    this.primaryPhoneExtension = primaryPhoneExtension;
  }

  public Integer getGovernmentEntityCode() {
    return governmentEntityCode;
  }

  public void setGovernmentEntityCode(Integer governmentEntityCode) {
    this.governmentEntityCode = governmentEntityCode;
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

  public String getForeignAddressIndicator() {
    return foreignAddressIndicator;
  }

  public void setForeignAddressIndicator(String foreignAddressIndicator) {
    this.foreignAddressIndicator = foreignAddressIndicator;
  }

  public String getAddressHeader() {
    return addressHeader;
  }

  public void setAddressHeader(String addressHeader) {
    this.addressHeader = addressHeader;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }
}
