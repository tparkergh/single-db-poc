package gov.ca.cwds.cics.model.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gov.ca.cwds.cics.binding.GeoLocationSerializer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * CWDS J Team
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressData {
  @JsonProperty("IDENTIFIER")
  private String identifier;
  @JsonProperty("CITY_NM")
  private String city;
  @JsonProperty("EMRG_TELNO")
  private Long emergencyPhoneNumber;
  @JsonProperty("EMRG_EXTNO")
  private Long emergencyPhoneExtension;
  @JsonProperty("FRG_ADRT_B")
  private String foreignAddressIndicator;
  @JsonProperty("GVR_ENTC")
  private Integer governmentEntityCode;
  @JsonProperty("MSG_TEL_NO")
  private Long messagePhoneNumber;
  @JsonProperty("MSG_EXT_NO")
  private Long messagePhoneExtension;
  @JsonProperty("HEADER_ADR")
  private String addressHeader;
  @JsonProperty("PRM_TEL_NO")
  private Long primaryPhoneNumber;
  @JsonProperty("PRM_EXT_NO")
  private Long primaryPhoneExtension;
  @JsonProperty("STATE_C")
  private Integer stateCode;
  @JsonProperty("STREET_NM")
  private String streetName;
  @JsonProperty("STREET_NO")
  private String streetNumber;
  @JsonProperty("ZIP_NO")
  private Integer zipCode;
  @JsonProperty("ADDR_DSC")
  private String addressDescription;
  @JsonProperty("ZIP_SFX_NO")
  private Integer zipSuffix;
  @JsonProperty("POSTDIR_CD")
  private String postStreetDirection;
  @JsonProperty("PREDIR_CD")
  private String preStreetDirection;
  @JsonProperty("ST_SFX_C")
  private Integer streetType;
  @JsonProperty("UNT_DSGC")
  private Integer unitType;
  @JsonProperty("UNIT_NO")
  private String unitNumber;
  @JsonProperty("LAT")
  @JsonSerialize(using = GeoLocationSerializer.class)
  private Double latitude;
  @JsonProperty("LNG")
  @JsonSerialize(using = GeoLocationSerializer.class)
  private Double longitude;

  public AddressData() {
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Long getEmergencyPhoneNumber() {
    return emergencyPhoneNumber;
  }

  public void setEmergencyPhoneNumber(Long emergencyPhoneNumber) {
    this.emergencyPhoneNumber = emergencyPhoneNumber;
  }

  public Long getEmergencyPhoneExtension() {
    return emergencyPhoneExtension;
  }

  public void setEmergencyPhoneExtension(Long emergencyPhoneExtension) {
    this.emergencyPhoneExtension = emergencyPhoneExtension;
  }

  public String getForeignAddressIndicator() {
    return foreignAddressIndicator;
  }

  public void setForeignAddressIndicator(String foreignAddressIndicator) {
    this.foreignAddressIndicator = foreignAddressIndicator;
  }

  public Integer getGovernmentEntityCode() {
    return governmentEntityCode;
  }

  public void setGovernmentEntityCode(Integer governmentEntityCode) {
    this.governmentEntityCode = governmentEntityCode;
  }

  public Long getMessagePhoneNumber() {
    return messagePhoneNumber;
  }

  public void setMessagePhoneNumber(Long messagePhoneNumber) {
    this.messagePhoneNumber = messagePhoneNumber;
  }

  public Long getMessagePhoneExtension() {
    return messagePhoneExtension;
  }

  public void setMessagePhoneExtension(Long messagePhoneExtension) {
    this.messagePhoneExtension = messagePhoneExtension;
  }

  public String getAddressHeader() {
    return addressHeader;
  }

  public void setAddressHeader(String addressHeader) {
    this.addressHeader = addressHeader;
  }

  public Long getPrimaryPhoneNumber() {
    return primaryPhoneNumber;
  }

  public void setPrimaryPhoneNumber(Long primaryPhoneNumber) {
    this.primaryPhoneNumber = primaryPhoneNumber;
  }

  public Long getPrimaryPhoneExtension() {
    return primaryPhoneExtension;
  }

  public void setPrimaryPhoneExtension(Long primaryPhoneExtension) {
    this.primaryPhoneExtension = primaryPhoneExtension;
  }

  public Integer getStateCode() {
    return stateCode;
  }

  public void setStateCode(Integer stateCode) {
    this.stateCode = stateCode;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  public Integer getZipCode() {
    return zipCode;
  }

  public void setZipCode(Integer zipCode) {
    this.zipCode = zipCode;
  }

  public String getAddressDescription() {
    return addressDescription;
  }

  public void setAddressDescription(String addressDescription) {
    this.addressDescription = addressDescription;
  }

  public Integer getZipSuffix() {
    return zipSuffix;
  }

  public void setZipSuffix(Integer zipSuffix) {
    this.zipSuffix = zipSuffix;
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

  public Integer getStreetType() {
    return streetType;
  }

  public void setStreetType(Integer streetType) {
    this.streetType = streetType;
  }

  public Integer getUnitType() {
    return unitType;
  }

  public void setUnitType(Integer unitType) {
    this.unitType = unitType;
  }

  public String getUnitNumber() {
    return unitNumber;
  }

  public void setUnitNumber(String unitNumber) {
    this.unitNumber = unitNumber;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
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
