
package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import gov.ca.cwds.cares.common.model.ObjectBase;

@Entity
@Table(name = "REPTR_T")
public class ReporterEntity extends ObjectBase implements Serializable {

  private static final long serialVersionUID = 6969398151287232182L;

  @Id
  @Column(name = "IDENTIFIER")
  private String identifier;

  @Column(name = "CMM_MTHC")
  private Integer communicationMethodCode;

  @Column(name = "CNFWVR_IND")
  private String confidentialWaiverIndicator;

  @Column(name = "CNTY_SPFCD")
  private String countySpecificCode;

  @Column(name = "COL_RELC")
  private Integer colltrClientRptrReltnshpType;

  @Column(name = "FB_RQR_IND")
  private String feedbackRequiredIndicator;

  @Column(name = "FDBACK_DOC")
  private String drmsMandatedRprtrFeedback;

  @Column(name = "FEEDBCK_DT")
  private LocalDate feedbackDate;

  @Column(name = "FKLAW_ENFT")
  private String lawEnforcementId;

  @Column(name = "FKREFERL_T")
  private String referralId;

  @Column(name = "LST_UPD_ID")
  private String lastUpdateId;

  @Column(name = "LST_UPD_TS")
  private LocalDateTime lastUpdateTimestamp;

  @Column(name = "MID_INI_NM")
  private String middleInitialName;

  @Column(name = "MNRPTR_IND")
  private String mandatedReporterIndicator;

  @Column(name = "MSG_EXT_NO")
  private Integer messagePhoneExtension;

  @Column(name = "MSG_TEL_NO")
  private Long messagePhoneNumber;

  @Column(name = "NMPRFX_DSC")
  private String namePrefixDescription;

  @Column(name = "PRM_TEL_NO")
  private Long primaryPhoneNumber;

  @Column(name = "PRM_EXT_NO")
  private Integer primaryPhoneExtension;

  @Column(name = "RPTR_BDGNO")
  private String badgeNumber;

  @Column(name = "RPTR_CTYNM")
  private String cityName;

  @Column(name = "RPTR_EMPNM")
  private String employerName;

  @Column(name = "RPTR_FSTNM")
  private String firstName;

  @Column(name = "RPTR_LSTNM")
  private String lastName;

  @Column(name = "RPTR_ST_NM")
  private String streetName;

  @Column(name = "RPTR_ST_NO")
  private String streetNumber;

  @Column(name = "RPTR_ZIPNO")
  private Integer zipCode;

  @Column(name = "STATE_C")
  private Integer stateCode;

  @Column(name = "SUFX_TLDSC")
  private String suffixTitleDescription;

  @Column(name = "ZIP_SFX_NO")
  private Integer zipSuffix;

  @Column(name = "BIRTH_DT")
  private LocalDate birthDate;

  @Column(name = "TITLE_DESC")
  private String titleDescription;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public Integer getCommunicationMethodCode() {
    return communicationMethodCode;
  }

  public void setCommunicationMethodCode(Integer communicationMethodCode) {
    this.communicationMethodCode = communicationMethodCode;
  }

  public String getConfidentialWaiverIndicator() {
    return confidentialWaiverIndicator;
  }

  public void setConfidentialWaiverIndicator(String confidentialWaiverIndicator) {
    this.confidentialWaiverIndicator = confidentialWaiverIndicator;
  }

  public String getCountySpecificCode() {
    return countySpecificCode;
  }

  public void setCountySpecificCode(String countySpecificCode) {
    this.countySpecificCode = countySpecificCode;
  }

  public Integer getColltrClientRptrReltnshpType() {
    return colltrClientRptrReltnshpType;
  }

  public void setColltrClientRptrReltnshpType(Integer colltrClientRptrReltnshpType) {
    this.colltrClientRptrReltnshpType = colltrClientRptrReltnshpType;
  }

  public String getFeedbackRequiredIndicator() {
    return feedbackRequiredIndicator;
  }

  public void setFeedbackRequiredIndicator(String feedbackRequiredIndicator) {
    this.feedbackRequiredIndicator = feedbackRequiredIndicator;
  }

  public String getDrmsMandatedRprtrFeedback() {
    return drmsMandatedRprtrFeedback;
  }

  public void setDrmsMandatedRprtrFeedback(String drmsMandatedRprtrFeedback) {
    this.drmsMandatedRprtrFeedback = drmsMandatedRprtrFeedback;
  }

  public LocalDate getFeedbackDate() {
    return feedbackDate;
  }

  public void setFeedbackDate(LocalDate feedbackDate) {
    this.feedbackDate = feedbackDate;
  }

  public String getLawEnforcementId() {
    return lawEnforcementId;
  }

  public void setLawEnforcementId(String lawEnforcementId) {
    this.lawEnforcementId = lawEnforcementId;
  }

  public String getReferralId() {
    return referralId;
  }

  public void setReferralId(String referralId) {
    this.referralId = referralId;
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

  public String getMiddleInitialName() {
    return middleInitialName;
  }

  public void setMiddleInitialName(String middleInitialName) {
    this.middleInitialName = middleInitialName;
  }

  public String getMandatedReporterIndicator() {
    return mandatedReporterIndicator;
  }

  public void setMandatedReporterIndicator(String mandatedReporterIndicator) {
    this.mandatedReporterIndicator = mandatedReporterIndicator;
  }

  public Integer getMessagePhoneExtension() {
    return messagePhoneExtension;
  }

  public void setMessagePhoneExtension(Integer messagePhoneExtension) {
    this.messagePhoneExtension = messagePhoneExtension;
  }

  public Long getMessagePhoneNumber() {
    return messagePhoneNumber;
  }

  public void setMessagePhoneNumber(Long messagePhoneNumber) {
    this.messagePhoneNumber = messagePhoneNumber;
  }

  public String getNamePrefixDescription() {
    return namePrefixDescription;
  }

  public void setNamePrefixDescription(String namePrefixDescription) {
    this.namePrefixDescription = namePrefixDescription;
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

  public String getBadgeNumber() {
    return badgeNumber;
  }

  public void setBadgeNumber(String badgeNumber) {
    this.badgeNumber = badgeNumber;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getEmployerName() {
    return employerName;
  }

  public void setEmployerName(String employerName) {
    this.employerName = employerName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  public Integer getStateCode() {
    return stateCode;
  }

  public void setStateCode(Integer stateCode) {
    this.stateCode = stateCode;
  }

  public String getSuffixTitleDescription() {
    return suffixTitleDescription;
  }

  public void setSuffixTitleDescription(String suffixTitleDescription) {
    this.suffixTitleDescription = suffixTitleDescription;
  }

  public Integer getZipSuffix() {
    return zipSuffix;
  }

  public void setZipSuffix(Integer zipSuffix) {
    this.zipSuffix = zipSuffix;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getTitleDescription() {
    return titleDescription;
  }

  public void setTitleDescription(String titleDescription) {
    this.titleDescription = titleDescription;
  }
}
