package gov.ca.cwds.cics.model;

import java.time.LocalDate;

public class ReporterDataBuilder {
  String txnHeaderStaffId = "ABC";
  String identifier = "1234567ABC";
  String badgeNumber = "";
  String cityName = "";
  Integer collateralClientReporterRelationship = 0;
  Integer communicationMethod = 0;
  String confidentialWaiverIndicator = "N";
  String feedbackDocument = null;
  String employerName = "";
  LocalDate feedbackDate = null;
  String feedbackRequiredIndicator = " ";
  String firstName = "first name";
  String lastName = "last name";
  String mandatedReporterIndicator = "N";
  Integer messagePhoneExtensionNumber = 0;
  Long messagePhoneNumber = 0L;
  String middleIntialName = " ";
  String namePrefixDescription = " ";
  Long primaryPhoneNumber = 0L;
  Integer primaryPhoneExtensionNumber = 0;
  Integer state = 0;
  String streetName = " ";
  String streetNumber = " ";
  String suffixTitleDescription = " ";
  Integer zipNumber = 0;
  String fkLawEnforcement = "";
  String fkReferral = "";
  Integer zipSuffixNumber = 0;
  String countySpecificCode = "99";

  public ReporterData build() {
    ReporterData reporterData = new ReporterData();
    reporterData.setTxnHeaderStaffId(txnHeaderStaffId);
    reporterData.setIdentifier(identifier);
    reporterData.setBadgeNumber(badgeNumber);
    reporterData.setCityName(cityName);
    reporterData.setCollateralClientReporterRelationship(collateralClientReporterRelationship);
    reporterData.setCommunicationMethod(communicationMethod);
    reporterData.setConfidentialWaiverIndicator(confidentialWaiverIndicator);
    reporterData.setFeedbackDocument(feedbackDocument);
    reporterData.setEmployerName(employerName);
    reporterData.setFeedbackDate(feedbackDate);
    reporterData.setFeedbackRequiredIndicator(feedbackRequiredIndicator);
    reporterData.setFirstName(firstName);
    reporterData.setLastName(lastName);
    reporterData.setMandatedReporterIndicator(mandatedReporterIndicator);
    reporterData.setMessagePhoneExtensionNumber(messagePhoneExtensionNumber);
    reporterData.setMessagePhoneNumber(messagePhoneNumber);
    reporterData.setMiddleInitialName(middleIntialName);
    reporterData.setNamePrefixDescription(namePrefixDescription);
    reporterData.setPrimaryPhoneNumber(primaryPhoneNumber);
    reporterData.setPrimaryPhoneExtensionNumber(primaryPhoneExtensionNumber);
    reporterData.setState(state);
    reporterData.setStreetName(streetName);
    reporterData.setStreetNumber(streetNumber);
    reporterData.setSuffixTitleDescription(suffixTitleDescription);
    reporterData.setZipNumber(zipNumber);
    reporterData.setFkLawEnforcement(fkLawEnforcement);
    reporterData.setFkReferral(fkReferral);
    reporterData.setZipSuffixNumber(zipSuffixNumber);
    reporterData.setCountySpecificCode(countySpecificCode);
    return reporterData;
  }

  public ReporterDataBuilder setTxnHeaderStaffId(String txnHeaderStaffId) {
    this.txnHeaderStaffId = txnHeaderStaffId;
    return this;
  }

  public ReporterDataBuilder setIdentifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  public ReporterDataBuilder setBadgeNumber(String badgeNumber) {
    this.badgeNumber = badgeNumber;
    return this;
  }

  public ReporterDataBuilder setCityName(String cityName) {
    this.cityName = cityName;
    return this;
  }

  public ReporterDataBuilder setCollateralClientReporterRelationship(
      Integer collateralClientReporterRelationship) {
    this.collateralClientReporterRelationship = collateralClientReporterRelationship;
    return this;
  }

  public ReporterDataBuilder setCommunicationMethod(Integer communicationMethodType) {
    this.communicationMethod = communicationMethodType;
    return this;
  }

  public ReporterDataBuilder setConfidentialWaiverIndicator(String confidentialWaiverInd) {
    this.confidentialWaiverIndicator = confidentialWaiverInd;
    return this;
  }

  public ReporterDataBuilder setFeedbackDocument(String drmsMandatedRprtrFeedback) {
    this.feedbackDocument = drmsMandatedRprtrFeedback;
    return this;
  }

  public ReporterDataBuilder setEmployerName(String employerName) {
    this.employerName = employerName;
    return this;
  }

  public ReporterDataBuilder setFeedbackDate(LocalDate feedbackDate) {
    this.feedbackDate = feedbackDate;
    return this;
  }

  public ReporterDataBuilder setFeedbackRequiredIndicator(String feedbackRequiredInd) {
    this.feedbackRequiredIndicator = feedbackRequiredInd;
    return this;
  }

  public ReporterDataBuilder setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ReporterDataBuilder setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ReporterDataBuilder setMandatedReporterIndicator(String mandatedReporterInd) {
    this.mandatedReporterIndicator = mandatedReporterInd;
    return this;
  }

  public ReporterDataBuilder setMessagePhoneExtensionNumber(Integer messagePhoneExtensionNumber) {
    this.messagePhoneExtensionNumber = messagePhoneExtensionNumber;
    return this;
  }

  public ReporterDataBuilder setPhoneNumber(Long messagePhoneNumber) {
    this.messagePhoneNumber = messagePhoneNumber;
    return this;
  }

  public ReporterDataBuilder setMiddleInitialName(String middleIntialName) {
    this.middleIntialName = middleIntialName;
    return this;
  }

  public ReporterDataBuilder setNamePrefixDescription(String namePrefixDescription) {
    this.namePrefixDescription = namePrefixDescription;
    return this;
  }

  public ReporterDataBuilder setPrimaryPhoneNumber(Long primaryPhoneNumber) {
    this.primaryPhoneNumber = primaryPhoneNumber;
    return this;
  }

  public ReporterDataBuilder setPrimaryPhoneExtensionNumber(Integer primaryPhoneExtensionNumber) {
    this.primaryPhoneExtensionNumber = primaryPhoneExtensionNumber;
    return this;
  }

  public ReporterDataBuilder setState(Integer stateCode) {
    this.state = stateCode;
    return this;
  }

  public ReporterDataBuilder setStreetName(String streetName) {
    this.streetName = streetName;
    return this;
  }

  public ReporterDataBuilder setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
    return this;
  }

  public ReporterDataBuilder setSuffixTitleDescription(String suffixTitleDescription) {
    this.suffixTitleDescription = suffixTitleDescription;
    return this;
  }

  public ReporterDataBuilder setZipNumber(Integer zipNumber) {
    this.zipNumber = zipNumber;
    return this;
  }

  public ReporterDataBuilder setFkLawEnforcement(String fkreferl_t) {
    this.fkLawEnforcement = fkreferl_t;
    return this;
  }

  public ReporterDataBuilder setFkReferral(String fklaw_enft) {
    this.fkReferral = fklaw_enft;
    return this;
  }

  public ReporterDataBuilder setZipSuffixNumber(Integer zipSuffixNumber) {
    this.zipSuffixNumber = zipSuffixNumber;
    return this;
  }

  public ReporterDataBuilder setCountySpecificCode(String countySpecificCode) {
    this.countySpecificCode = countySpecificCode;
    return this;
  }
}
