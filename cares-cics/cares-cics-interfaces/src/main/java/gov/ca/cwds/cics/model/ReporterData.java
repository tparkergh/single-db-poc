package gov.ca.cwds.cics.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CWDS J Team
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReporterData {

  @JsonProperty("TXN_HDR_STAFF_ID")
  private String txnHeaderStaffId;
  @JsonProperty("IDENTIFIER")
  private String identifier;
  @JsonProperty("RPTR_BDGNO")
  private String badgeNumber;
  @JsonProperty("RPTR_CTYNM")
  private String cityName;
  @JsonProperty("COL_RELC")
  private Integer colltrClientRptrReltnshipType;
  @JsonProperty("CMM_MTHC")
  private Integer communicationMethodType;
  @JsonProperty("CNFWVR_IND")
  private String confidentialWaiverInd;
  @JsonProperty("FDBACK_DOC")
  private String drmsMandatedRprtrFeedback;
  @JsonProperty("RPTR_EMPNM")
  private String employerName;
  @JsonProperty("FEEDBCK_DT")
  private String feedbackDate;
  @JsonProperty("FB_RQR_IND")
  private String feedbackRequiredInd;
  @JsonProperty("RPTR_FSTNM")
  private String firstName;
  @JsonProperty("RPTR_LSTNM")
  private String lastName;
  @JsonProperty("MNRPTR_IND")
  private String mandatedReporterInd;
  @JsonProperty("MSG_EXT_NO")
  private Long messagePhoneExtensionNumber;
  @JsonProperty("MSG_TEL_NO")
  private Long messagePhoneNumber;
  @JsonProperty("MID_INI_NM")
  private String middleIntialName;
  @JsonProperty("NMPRFX_DSC")
  private String namePrefixDescription;
  @JsonProperty("PRM_TEL_NO")
  private Long primaryPhoneNumber;
  @JsonProperty("PRM_EXT_NO")
  private Long primaryPhoneExtensionNumber;
  @JsonProperty("STATE_C")
  private Integer stateCode;
  @JsonProperty("RPTR_ST_NM")
  private String streetName;
  @JsonProperty("RPTR_ST_NO")
  private String streetNumber;
  @JsonProperty("SUFX_TLDSC")
  private String suffixTitleDescription;
  @JsonProperty("RPTR_ZIPNO")
  private Integer zipNumber;
  @JsonProperty("FKREFERL_T")
  private String fkreferl_t;
  @JsonProperty("FKLAW_ENFT")
  private String fklaw_enft;
  @JsonProperty("ZIP_SFX_NO")
  private Integer zipSuffixNumber;
  @JsonProperty("CNTY_SPFC")
  private String countySpecificCode;

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


  public Integer getColltrClientRptrReltnshipType() {
    return colltrClientRptrReltnshipType;
  }


  public void setColltrClientRptrReltnshipType(Integer colltrClientRptrReltnshipType) {
    this.colltrClientRptrReltnshipType = colltrClientRptrReltnshipType;
  }


  public Integer getCommunicationMethodType() {
    return communicationMethodType;
  }


  public void setCommunicationMethodType(Integer communicationMethodType) {
    this.communicationMethodType = communicationMethodType;
  }


  public String getConfidentialWaiverInd() {
    return confidentialWaiverInd;
  }


  public void setConfidentialWaiverInd(String confidentialWaiverInd) {
    this.confidentialWaiverInd = confidentialWaiverInd;
  }


  public String getDrmsMandatedRprtrFeedback() {
    return drmsMandatedRprtrFeedback;
  }


  public void setDrmsMandatedRprtrFeedback(String drmsMandatedRprtrFeedback) {
    this.drmsMandatedRprtrFeedback = drmsMandatedRprtrFeedback;
  }


  public String getEmployerName() {
    return employerName;
  }


  public void setEmployerName(String employerName) {
    this.employerName = employerName;
  }


  public String getFeedbackDate() {
    return feedbackDate;
  }


  public void setFeedbackDate(String feedbackDate) {
    this.feedbackDate = feedbackDate;
  }


  public String getFeedbackRequiredInd() {
    return feedbackRequiredInd;
  }


  public void setFeedbackRequiredInd(String feedbackRequiredInd) {
    this.feedbackRequiredInd = feedbackRequiredInd;
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


  public String getMandatedReporterInd() {
    return mandatedReporterInd;
  }


  public void setMandatedReporterInd(String mandatedReporterInd) {
    this.mandatedReporterInd = mandatedReporterInd;
  }


  public Long getMessagePhoneExtensionNumber() {
    return messagePhoneExtensionNumber;
  }


  public void setMessagePhoneExtensionNumber(Long messagePhoneExtensionNumber) {
    this.messagePhoneExtensionNumber = messagePhoneExtensionNumber;
  }


  public Long getMessagePhoneNumber() {
    return messagePhoneNumber;
  }


  public void setMessagePhoneNumber(Long messagePhoneNumber) {
    this.messagePhoneNumber = messagePhoneNumber;
  }


  public String getMiddleIntialName() {
    return middleIntialName;
  }


  public void setMiddleIntialName(String middleIntialName) {
    this.middleIntialName = middleIntialName;
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


  public Long getPrimaryPhoneExtensionNumber() {
    return primaryPhoneExtensionNumber;
  }


  public void setPrimaryPhoneExtensionNumber(Long primaryPhoneExtensionNumber) {
    this.primaryPhoneExtensionNumber = primaryPhoneExtensionNumber;
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


  public String getSuffixTitleDescription() {
    return suffixTitleDescription;
  }


  public void setSuffixTitleDescription(String suffixTitleDescription) {
    this.suffixTitleDescription = suffixTitleDescription;
  }


  public Integer getZipNumber() {
    return zipNumber;
  }


  public void setZipNumber(Integer zipNumber) {
    this.zipNumber = zipNumber;
  }


  public String getFkreferl_t() {
    return fkreferl_t;
  }


  public void setFkreferl_t(String fkreferl_t) {
    this.fkreferl_t = fkreferl_t;
  }


  public String getFklaw_enft() {
    return fklaw_enft;
  }


  public void setFklaw_enft(String fklaw_enft) {
    this.fklaw_enft = fklaw_enft;
  }


  public Integer getZipSuffixNumber() {
    return zipSuffixNumber;
  }


  public void setZipSuffixNumber(Integer zipSuffixNumber) {
    this.zipSuffixNumber = zipSuffixNumber;
  }


  public String getCountySpecificCode() {
    return countySpecificCode;
  }


  public void setCountySpecificCode(String countySpecificCode) {
    this.countySpecificCode = countySpecificCode;
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
