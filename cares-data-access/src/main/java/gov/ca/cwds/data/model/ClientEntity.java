
package gov.ca.cwds.data.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "CLIENT_T")
@NamedQuery(name = "findClientById", query = "from client_t c where c.identifier = :identifier")

public class ClientEntity {
  @Id
  @Column(name = "IDENTIFIER")
  private String identifier;

  @Column(name = "BIRTH_DT")
  private LocalDate birthDate;

  @Column(name = "CHLD_CLT_B")
  private String childClientIndicator;

  @Column(name = "CL_INDX_NO")
  private String clientIndexNumber;

  @Column(name = "CLNT_GIC")
  private Integer genderIdentityType;

  @Column(name = "CLNT_GEC")
  private Integer genderExpressionType;

  @Column(name = "CLNT_SOC")
  private Integer sexualOrientationType;

  @Column(name = "COM_FST_NM")
  private String commonFirstName;

  @Column(name = "COM_LST_NM")
  private String commonLastName;

  @Column(name = "COM_MID_NM")
  private String commonMiddleName;

  @Column(name = "CREATN_DT")
  private LocalDate creationDate;

  @Column(name = "DRV_LIC_NO")
  private String driverLicenseNumber;

  @Column(name = "EMAIL_ADDR")
  private String emailAddress;

  @Column(name = "EST_DOB_CD")
  private String estimatedDobCode;

  @Column(name = "GENDER_CD")
  private String genderCode;

  @Column(name = "NAME_TPC")
  private Integer nameType;

  @Column(name = "SS_NO")
  private String socialSeccurityNumber;

  @Column(name = "SUFX_TLDSC")
  private String suffixTitleDescription;

  @Column(name = "SO_UD_CD")
  private String soUnableToDetermineCode;


  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getChildClientIndicator() {
    return childClientIndicator;
  }

  public void setChildClientIndicator(String childClientIndicator) {
    this.childClientIndicator = childClientIndicator;
  }

  public String getClientIndexNumber() {
    return clientIndexNumber;
  }

  public void setClientIndexNumber(String clientIndexNumber) {
    this.clientIndexNumber = clientIndexNumber;
  }

  public Integer getGenderIdentityType() {
    return genderIdentityType;
  }

  public void setGenderIdentityType(Integer genderIdentityType) {
    this.genderIdentityType = genderIdentityType;
  }

  public Integer getGenderExpressionType() {
    return genderExpressionType;
  }

  public void setGenderExpressionType(Integer genderExpressionType) {
    this.genderExpressionType = genderExpressionType;
  }

  public Integer getSexualOrientationType() {
    return sexualOrientationType;
  }

  public void setSexualOrientationType(Integer sexualOrientationType) {
    this.sexualOrientationType = sexualOrientationType;
  }

  public String getCommonFirstName() {
    return commonFirstName;
  }

  public void setCommonFirstName(String commonFirstName) {
    this.commonFirstName = commonFirstName;
  }

  public String getCommonLastName() {
    return commonLastName;
  }

  public void setCommonLastName(String commonLastName) {
    this.commonLastName = commonLastName;
  }

  public String getCommonMiddleName() {
    return commonMiddleName;
  }

  public void setCommonMiddleName(String commonMiddleName) {
    this.commonMiddleName = commonMiddleName;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public String getDriverLicenseNumber() {
    return driverLicenseNumber;
  }

  public void setDriverLicenseNumber(String driverLicenseNumber) {
    this.driverLicenseNumber = driverLicenseNumber;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getEstimatedDobCode() {
    return estimatedDobCode;
  }

  public void setEstimatedDobCode(String estimatedDobCode) {
    this.estimatedDobCode = estimatedDobCode;
  }

  public String getGenderCode() {
    return genderCode;
  }

  public void setGenderCode(String genderCode) {
    this.genderCode = genderCode;
  }

  public Integer getNameType() {
    return nameType;
  }

  public void setNameType(Integer nameType) {
    this.nameType = nameType;
  }

  public String getSocialSeccurityNumber() {
    return socialSeccurityNumber;
  }

  public void setSocialSeccurityNumber(String socialSeccurityNumber) {
    this.socialSeccurityNumber = socialSeccurityNumber;
  }

  public String getSuffixTitleDescription() {
    return suffixTitleDescription;
  }

  public void setSuffixTitleDescription(String suffixTitleDescription) {
    this.suffixTitleDescription = suffixTitleDescription;
  }

  public String getSoUnableToDetermineCode() {
    return soUnableToDetermineCode;
  }

  public void setSoUnableToDetermineCode(String soUnableToDetermineCode) {
    this.soUnableToDetermineCode = soUnableToDetermineCode;
  }

}
