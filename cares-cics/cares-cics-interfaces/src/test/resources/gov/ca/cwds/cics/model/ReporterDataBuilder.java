package gov.ca.cwds.cics.model;

public class ReporterDataBuilder {
  String txnHeaderStaffId = "ABC";
  String identifier = "1234567ABC";
  String badgeNumber = "";
  String cityName = "";
  Integer colltrClientRptrReltnshipType = 0;
  Integer communicationMethodType = 0;
  String confidentialWaiverInd = "N";
  String drmsMandatedRprtrFeedback = null;
  String employerName = "";
  String feedbackDate = "";
  String feedbackRequiredInd = " ";
  String firstName = "first name";
  String lastName = "last name";
  String mandatedReporterInd = "N";
  Long messagePhoneExtensionNumber = 0L;
  Long messagePhoneNumber = 0L;
  String middleIntialName = " ";
  String namePrefixDescription = " ";
  Long primaryPhoneNumber = 0L;
  Long primaryPhoneExtensionNumber = 0L;
  Integer stateCode = 0;
  String streetName = " ";
  String streetNumber = " ";
  String suffixTitleDescription = " ";
  Integer zipNumber = 0;
  String fkreferl_t = "";
  String fklaw_enft = "";
  Integer zipSuffixNumber = 0;
  String countySpecificCode = "99";

  public ReporterData build() {
    ReporterData reporterData = new ReporterData();
    reporterData.setTxnHeaderStaffId(txnHeaderStaffId);
    reporterData.setIdentifier(identifier);
    reporterData.setBadgeNumber(badgeNumber);
    reporterData.setCityName(cityName);
    reporterData.setColltrClientRptrReltnshipType(colltrClientRptrReltnshipType);
    reporterData.setCommunicationMethodType(communicationMethodType);
    reporterData.setConfidentialWaiverInd(confidentialWaiverInd);
    reporterData.setDrmsMandatedRprtrFeedback(drmsMandatedRprtrFeedback);
    reporterData.setEmployerName(employerName);
    reporterData.setFeedbackDate(feedbackDate);
    reporterData.setFeedbackRequiredInd(feedbackRequiredInd);
    reporterData.setFirstName(firstName);
    reporterData.setLastName(lastName);
    reporterData.setMandatedReporterInd(mandatedReporterInd);
    reporterData.setMessagePhoneExtensionNumber(messagePhoneExtensionNumber);
    reporterData.setMessagePhoneNumber(messagePhoneNumber);
    reporterData.setMiddleIntialName(middleIntialName);
    reporterData.setNamePrefixDescription(namePrefixDescription);
    reporterData.setPrimaryPhoneNumber(primaryPhoneNumber);
    reporterData.setPrimaryPhoneExtensionNumber(primaryPhoneExtensionNumber);
    reporterData.setStateCode(stateCode);
    reporterData.setStreetName(streetName);
    reporterData.setStreetNumber(streetNumber);
    reporterData.setSuffixTitleDescription(suffixTitleDescription);
    reporterData.setZipNumber(zipNumber);
    reporterData.setFkreferl_t(fkreferl_t);
    reporterData.setFklaw_enft(fklaw_enft);
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

  public ReporterDataBuilder setColltrClientRptrReltnshipType(
      Integer colltrClientRptrReltnshipType) {
    this.colltrClientRptrReltnshipType = colltrClientRptrReltnshipType;
    return this;
  }

  public ReporterDataBuilder setCommunicationMethodType(Integer communicationMethodType) {
    this.communicationMethodType = communicationMethodType;
    return this;
  }

  public ReporterDataBuilder setConfidentialWaiverInd(String confidentialWaiverInd) {
    this.confidentialWaiverInd = confidentialWaiverInd;
    return this;
  }

  public ReporterDataBuilder setDrmsMandatedRprtrFeedback(String drmsMandatedRprtrFeedback) {
    this.drmsMandatedRprtrFeedback = drmsMandatedRprtrFeedback;
    return this;
  }

  public ReporterDataBuilder setEmployerName(String employerName) {
    this.employerName = employerName;
    return this;
  }

  public ReporterDataBuilder setFeedbackDate(String feedbackDate) {
    this.feedbackDate = feedbackDate;
    return this;
  }

  public ReporterDataBuilder setFeedbackRequiredInd(String feedbackRequiredInd) {
    this.feedbackRequiredInd = feedbackRequiredInd;
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

  public ReporterDataBuilder setMandatedReporterInd(String mandatedReporterInd) {
    this.mandatedReporterInd = mandatedReporterInd;
    return this;
  }

  public ReporterDataBuilder setMessagePhoneExtensionNumber(Long messagePhoneExtensionNumber) {
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

  public ReporterDataBuilder setPrimaryPhoneExtensionNumber(Long primaryPhoneExtensionNumber) {
    this.primaryPhoneExtensionNumber = primaryPhoneExtensionNumber;
    return this;
  }

  public ReporterDataBuilder setStateCode(Integer stateCode) {
    this.stateCode = stateCode;
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

  public ReporterDataBuilder setFkreferl_t(String fkreferl_t) {
    this.fkreferl_t = fkreferl_t;
    return this;
  }

  public ReporterDataBuilder setFklaw_enft(String fklaw_enft) {
    this.fklaw_enft = fklaw_enft;
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
