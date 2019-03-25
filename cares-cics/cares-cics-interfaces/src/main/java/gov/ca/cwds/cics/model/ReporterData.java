package gov.ca.cwds.cics.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import gov.ca.cwds.cares.common.model.ObjectBase;

/**
 * CWDS J Team
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReporterData extends ObjectBase {
  @JsonProperty("TXN_HDR_STAFF_ID")
  private String txnHeaderStaffId;
  @JsonProperty("IDENTIFIER")
  private String identifier;
  @JsonProperty("CMM_MTHC")
  private Integer communicationMethod;
  @JsonProperty("CNFWVR_IND")
  private String confidentialWaiverIndicator;
  @JsonProperty("CNTY_SPFCD")
  private String countySpecificCode;
  @JsonProperty("COL_RELC")
  private Integer collateralClientReporterRelationship;
  @JsonProperty("FB_RQR_IND")
  private String feedbackRequiredIndicator;
  @JsonProperty("FDBACK_DOC")
  private String feedbackDocument;
  @JsonProperty("FEEDBCK_DT")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate feedbackDate;
  @JsonProperty("FKLAW_ENFT")
  private String fkLawEnforcement;
  @JsonProperty("FKREFERL_T")
  private String fkReferral;
  @JsonProperty("MID_INI_NM")
  private String middleInitialName;
  @JsonProperty("MNRPTR_IND")
  private String mandatedReporterIndicator;
  @JsonProperty("MSG_EXT_NO")
  private Integer messagePhoneExtensionNumber;
  @JsonProperty("MSG_TEL_NO")
  private Long messagePhoneNumber;
  @JsonProperty("NMPRFX_DSC")
  private String namePrefixDescription;
  @JsonProperty("PRM_EXT_NO")
  private Integer primaryPhoneExtensionNumber;
  @JsonProperty("PRM_TEL_NO")
  private Long primaryPhoneNumber;
  @JsonProperty("RPTR_BDGNO")
  private String badgeNumber;
  @JsonProperty("RPTR_CTYNM")
  private String cityName;
  @JsonProperty("RPTR_EMPNM")
  private String employerName;
  @JsonProperty("RPTR_FSTNM")
  private String firstName;
  @JsonProperty("RPTR_LSTNM")
  private String lastName;
  @JsonProperty("RPTR_ST_NM")
  private String streetName;
  @JsonProperty("RPTR_ST_NO")
  private String streetNumber;
  @JsonProperty("RPTR_ZIPNO")
  private Integer zipNumber;
  @JsonProperty("STATE_C")
  private Integer state;
  @JsonProperty("SUFX_TLDSC")
  private String suffixTitleDescription;
  @JsonProperty("TITLE_DESC")
  private String titleDescription;
  @JsonProperty("ZIP_SFX_NO")
  private Integer zipSuffixNumber;
  @JsonProperty("BIRTH_DT")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate birthDate;

  public ReporterData() {
  }

  public String getTxnHeaderStaffId() {
    return txnHeaderStaffId;
  }

  public void setTxnHeaderStaffId(String txnHeaderStaffId) {
    this.txnHeaderStaffId = txnHeaderStaffId;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public Integer getCommunicationMethod() {
    return communicationMethod;
  }

  public void setCommunicationMethod(Integer communicationMethod) {
    this.communicationMethod = communicationMethod;
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

  public Integer getCollateralClientReporterRelationship() {
    return collateralClientReporterRelationship;
  }

  public void setCollateralClientReporterRelationship(Integer collateralClientReporterRelationship) {
    this.collateralClientReporterRelationship = collateralClientReporterRelationship;
  }

  public String getFeedbackRequiredIndicator() {
    return feedbackRequiredIndicator;
  }

  public void setFeedbackRequiredIndicator(String feedbackRequiredIndicator) {
    this.feedbackRequiredIndicator = feedbackRequiredIndicator;
  }

  public String getFeedbackDocument() {
    return feedbackDocument;
  }

  public void setFeedbackDocument(String feedbackDocument) {
    this.feedbackDocument = feedbackDocument;
  }

  public LocalDate getFeedbackDate() {
    return feedbackDate;
  }

  public void setFeedbackDate(LocalDate feedbackDate) {
    this.feedbackDate = feedbackDate;
  }

  public String getFkLawEnforcement() {
    return fkLawEnforcement;
  }

  public void setFkLawEnforcement(String fkLawEnforcement) {
    this.fkLawEnforcement = fkLawEnforcement;
  }

  public String getFkReferral() {
    return fkReferral;
  }

  public void setFkReferral(String fkReferral) {
    this.fkReferral = fkReferral;
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

  public Integer getMessagePhoneExtensionNumber() {
    return messagePhoneExtensionNumber;
  }

  public void setMessagePhoneExtensionNumber(Integer messagePhoneExtensionNumber) {
    this.messagePhoneExtensionNumber = messagePhoneExtensionNumber;
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

  public Integer getPrimaryPhoneExtensionNumber() {
    return primaryPhoneExtensionNumber;
  }

  public void setPrimaryPhoneExtensionNumber(Integer primaryPhoneExtensionNumber) {
    this.primaryPhoneExtensionNumber = primaryPhoneExtensionNumber;
  }

  public Long getPrimaryPhoneNumber() {
    return primaryPhoneNumber;
  }

  public void setPrimaryPhoneNumber(Long primaryPhoneNumber) {
    this.primaryPhoneNumber = primaryPhoneNumber;
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

  public Integer getZipNumber() {
    return zipNumber;
  }

  public void setZipNumber(Integer zipNumber) {
    this.zipNumber = zipNumber;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public String getSuffixTitleDescription() {
    return suffixTitleDescription;
  }

  public void setSuffixTitleDescription(String suffixTitleDescription) {
    this.suffixTitleDescription = suffixTitleDescription;
  }

  public Integer getZipSuffixNumber() {
    return zipSuffixNumber;
  }

  public void setZipSuffixNumber(Integer zipSuffixNumber) {
    this.zipSuffixNumber = zipSuffixNumber;
  }

  public String getTitleDescription() {
    return titleDescription;
  }

  public void setTitleDescription(String titleDescription) {
    this.titleDescription = titleDescription;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
}
