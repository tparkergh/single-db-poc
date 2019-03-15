package gov.ca.cwds.cares.interfaces.model.people;

import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.model.ObjectBase;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client extends ObjectBase implements Serializable {
  
  private static final long serialVersionUID = 8200582244872085377L;
  
  private String identifier;
  private LocalDate birthDate;
  private String childClientIndicator;
  private String clientIndexNumber;
  private Integer genderIdentityType;
  private Integer genderExpressionType;
  private Integer sexualOrientationType;
  private String commonFirstName;
  private String commonLastName;
  private String commonMiddleName;
  private LocalDate creationDate;
  private String driverLicenseNumber;
  private String emailAddress;
  private String estimatedDobCode;
  private String genderCode;
  private Integer nameType;
  private String socialSeccurityNumber;
  private String suffixTitleDescription;
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
