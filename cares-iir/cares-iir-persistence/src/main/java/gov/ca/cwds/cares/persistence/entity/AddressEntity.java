package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import gov.ca.cwds.cares.common.model.ObjectBase;

@Entity
@Table(name = "ADDRS_T")
public class AddressEntity extends ObjectBase implements Serializable {

  private static final long serialVersionUID = -5575086960283978663L;

  @Id
  @Column(name = "IDENTIFIER")
  private String identifier;
  
  @Column(name = "STATE_C")
  private Integer stateCode;
  
  @Column(name = "ZIP_NO")
  private Integer zipCode;
  
  @Column(name = "ZIP_SFX_NO")
  private Integer zipSuffix;
  
  @Column(name = "CITY_NM")
  private String city;
  
  @Column(name = "STREET_NM")
  private String streetName;
  
  @Column(name = "ST_SFX_C")
  private Integer streetType;
  
  @Column(name = "STREET_NO")
  private String streetNumber;
  
  @Column(name = "UNIT_NO")
  private String unitNumber;
  
  @Column(name = "UNT_DSGC")
  private Integer unitType;
  
  @Column(name = "ADDR_DSC")
  private String addressDescription;
  
  @Column(name = "POSTDIR_CD")
  private String postStreetDirection;
  
  @Column(name = "PREDIR_CD")
  private String preStreetDirection;
  
  @Column(name = "EMRG_TELNO")
  private Long emergencyPhoneNumber;
  
  @Column(name = "EMRG_EXTNO")
  private Integer emergencyPhoneExtension;
  
  @Column(name = "MSG_TEL_NO")
  private Long messagePhoneNumber;
  
  @Column(name = "MSG_EXT_NO")
  private Integer messagePhoneExtension;
  
  @Column(name = "PRM_TEL_NO")
  private Long primaryPhoneNumber;
  
  @Column(name = "PRM_EXT_NO")
  private Integer primaryPhoneExtension;
  
  @Column(name = "GVR_ENTC")
  private Integer governmentEntityCode;
  
  @Column(name = "LST_UPD_ID")
  private String lastUpdateId;
  
  @Column(name = "LST_UPD_TS")
  private LocalDateTime lastUpdateTimestamp;
  
  @Column(name = "FRG_ADRT_B")
  private String foreignAddressIndicator;
  
  @Column(name = "HEADER_ADR")
  private String addressHeader;
  
  public AddressEntity() {
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
}
