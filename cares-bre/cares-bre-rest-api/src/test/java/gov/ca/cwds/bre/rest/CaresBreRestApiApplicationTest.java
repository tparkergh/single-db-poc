package gov.ca.cwds.bre.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.api.BusinessRuleService;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreRequestData;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.services.rules.CalsChildClientBusinessRules;
import gov.ca.cwds.bre.services.rules.CalsClientBusinessRules;
import gov.ca.cwds.cms.data.access.dto.ChildClientEntityAwareDTO;
import gov.ca.cwds.cms.data.access.dto.ClientEntityAwareDTO;
import gov.ca.cwds.cms.data.access.dto.OtherClientNameDTO;
import gov.ca.cwds.data.legacy.cms.entity.BackgroundCheck;
import gov.ca.cwds.data.legacy.cms.entity.ChildClient;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.data.legacy.cms.entity.ClientPaternityDetail;
import gov.ca.cwds.data.legacy.cms.entity.ClientRelationship;
import gov.ca.cwds.data.legacy.cms.entity.ClientServiceProvider;
import gov.ca.cwds.data.legacy.cms.entity.CountyLicenseCase;
import gov.ca.cwds.data.legacy.cms.entity.CreditReportHistory;
import gov.ca.cwds.data.legacy.cms.entity.CsecHistory;
import gov.ca.cwds.data.legacy.cms.entity.DasHistory;
import gov.ca.cwds.data.legacy.cms.entity.DeliveredService;
import gov.ca.cwds.data.legacy.cms.entity.FCEligibility;
import gov.ca.cwds.data.legacy.cms.entity.HealthInterventionPlan;
import gov.ca.cwds.data.legacy.cms.entity.HealthReferral;
import gov.ca.cwds.data.legacy.cms.entity.HealthScreening;
import gov.ca.cwds.data.legacy.cms.entity.LicensingIssue;
import gov.ca.cwds.data.legacy.cms.entity.LicensingVisit;
import gov.ca.cwds.data.legacy.cms.entity.LongText;
import gov.ca.cwds.data.legacy.cms.entity.MedicalEligibilityApplication;
import gov.ca.cwds.data.legacy.cms.entity.NearFatality;
import gov.ca.cwds.data.legacy.cms.entity.OtherAdultsInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherChildrenInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherPeopleScpRelationship;
import gov.ca.cwds.data.legacy.cms.entity.OutOfHomePlacement;
import gov.ca.cwds.data.legacy.cms.entity.OutOfStateCheck;
import gov.ca.cwds.data.legacy.cms.entity.ParentalRightsTermination;
import gov.ca.cwds.data.legacy.cms.entity.PlacementEpisode;
import gov.ca.cwds.data.legacy.cms.entity.PlacementFacilityTypeHistory;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeNotes;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeProfile;
import gov.ca.cwds.data.legacy.cms.entity.SafetyAlert;
import gov.ca.cwds.data.legacy.cms.entity.SchoolOriginHistory;
import gov.ca.cwds.data.legacy.cms.entity.SpecialEducation;
import gov.ca.cwds.data.legacy.cms.entity.StaffPerson;
import gov.ca.cwds.data.legacy.cms.entity.SubstituteCareProvider;
import gov.ca.cwds.data.legacy.cms.entity.TribalAncestryNotification;
import gov.ca.cwds.data.legacy.cms.entity.enums.Adoptable;
import gov.ca.cwds.data.legacy.cms.entity.enums.AdoptionStatus;
import gov.ca.cwds.data.legacy.cms.entity.enums.AllegedFatherPaternityStatus;
import gov.ca.cwds.data.legacy.cms.entity.enums.AwolAbducted;
import gov.ca.cwds.data.legacy.cms.entity.enums.CompetencyType;
import gov.ca.cwds.data.legacy.cms.entity.enums.CreditReportOutcomeCode;
import gov.ca.cwds.data.legacy.cms.entity.enums.DateOfBirthStatus;
import gov.ca.cwds.data.legacy.cms.entity.enums.Disability;
import gov.ca.cwds.data.legacy.cms.entity.enums.Gender;
import gov.ca.cwds.data.legacy.cms.entity.enums.HispanicOrigin;
import gov.ca.cwds.data.legacy.cms.entity.enums.IcwaEligibility;
import gov.ca.cwds.data.legacy.cms.entity.enums.IncapacitatedParentStatus;
import gov.ca.cwds.data.legacy.cms.entity.enums.IndividualType;
import gov.ca.cwds.data.legacy.cms.entity.enums.InterventionPlanType;
import gov.ca.cwds.data.legacy.cms.entity.enums.IveEligebleStatus;
import gov.ca.cwds.data.legacy.cms.entity.enums.LegalStatus;
import gov.ca.cwds.data.legacy.cms.entity.enums.LiterateStatus;
import gov.ca.cwds.data.legacy.cms.entity.enums.MilitaryStatus;
import gov.ca.cwds.data.legacy.cms.entity.enums.ParentUnemployedStatus;
import gov.ca.cwds.data.legacy.cms.entity.enums.PreviouslyAdopted;
import gov.ca.cwds.data.legacy.cms.entity.enums.ReferralOutcome;
import gov.ca.cwds.data.legacy.cms.entity.enums.ScreeningResult;
import gov.ca.cwds.data.legacy.cms.entity.enums.Sensitivity;
import gov.ca.cwds.data.legacy.cms.entity.enums.Soc158placementsStatus;
import gov.ca.cwds.data.legacy.cms.entity.enums.TypeOfApplication;
import gov.ca.cwds.data.legacy.cms.entity.enums.UnableToDetermineReason;
import gov.ca.cwds.data.legacy.cms.entity.enums.YesNoUnknown;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ClientRelationshipType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.County;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.CreditAgencyType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.CreditReportRequestedByType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.CreditReportStatusType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.FcEligibilityDentalReason;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.FcEligibilityTermReason;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.HealthConsentType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.HealthPlanTerminationType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.HealthReferralType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.HealthReferredToType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.HealthScreenedByType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.HealthScreeningType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.NameType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ParentalRightTerminationType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.SafetyAlertActivationReasonType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ServiceContactType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.SexualExploitationType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.SystemCode;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.SystemCodeTable;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.VisitType;

/**
 * CWDS J Team
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = CaresBreRestApiApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource
public class CaresBreRestApiApplicationTest {
  
  @Autowired
  @Lazy
  @Qualifier(value="BreRestApiClient")
  private BusinessRuleService breApiRestClient;

  @Autowired
  @Qualifier(value="BreService")
  private BusinessRuleService breService;

  @Autowired
  private ObjectMapper jacksonObjectMapper;

  @Test
  public void performanceRestTestCalsClientBusinessRules() throws Exception {
    int timesToCall = 1;
    ClientEntityAwareDTO dto = createClientEntityAwareDTO(new ClientEntityAwareDTO());
    String ruleName = CalsClientBusinessRules.class.getSimpleName();
    doPerformanceTest(breApiRestClient, dto, ruleName, timesToCall);
  }

  @Test
  public void performanceRestTestCalsChildClientBusinessRules() throws Exception {
    int timesToCall = 1;
    ChildClientEntityAwareDTO dto = createChildClientEntityAwareDTO();
    String ruleName = CalsChildClientBusinessRules.class.getSimpleName();
    doPerformanceTest(breApiRestClient, dto, ruleName, timesToCall);
  }

  @Test
  public void performanceServiceTestCalsClientBusinessRules() throws Exception {
    int timesToCall = 1;
    ClientEntityAwareDTO dto = createClientEntityAwareDTO(new ClientEntityAwareDTO());
    String ruleName = CalsClientBusinessRules.class.getSimpleName();
    doPerformanceTest(breService, dto, ruleName, timesToCall);
  }

  @Test
  public void performanceTestCalsChildClientBusinessRules() throws Exception {
    int timesToCall = 1;
    ChildClientEntityAwareDTO dto = createChildClientEntityAwareDTO();
    String ruleName = CalsChildClientBusinessRules.class.getSimpleName();
    doPerformanceTest(breService, dto, ruleName, timesToCall);
  }

  private BreResponse doTest(String request, String expectedResponse) throws IOException, JSONException {
    BreRequest breRequest = jacksonObjectMapper.readValue(request, BreRequest.class);
    BreResponse breResponse = breApiRestClient.executeBusinessRules(breRequest);

    String actualResponse = jacksonObjectMapper.writeValueAsString(breResponse);

    Customization customization = new Customization("execution_time_millis", (o1, o2) -> true);
    CustomComparator comparator = new CustomComparator(JSONCompareMode.STRICT, customization);
    JSONAssert.assertEquals(expectedResponse, actualResponse, comparator);
    
    return breResponse;
  }

  private void doPerformanceTest(BusinessRuleService businessRuleService, 
      Object data, 
      String ruleName, 
      int timesToCall) {
    BreRequest breRequest = new BreRequest();
    breRequest.setBusinessRuleSetName(ruleName);
    BreRequestData breRequestData = new BreRequestData();
    breRequestData.setDataObjectClassName(data.getClass().getName());
    breRequestData.setDataObject(jacksonObjectMapper.convertValue(data, JsonNode.class));
    breRequest.addDataObject(breRequestData);

    long begin = System.currentTimeMillis();
    for (int i = 0; i < timesToCall; i++) {
      businessRuleService.executeBusinessRules(breRequest);
    }
    long end = System.currentTimeMillis();
    System.out.println(ruleName + ". " + timesToCall + " executions time: " + (end - begin) + " ms");
  }



  private ChildClientEntityAwareDTO createChildClientEntityAwareDTO() {
    ChildClientEntityAwareDTO dto = (ChildClientEntityAwareDTO) createClientEntityAwareDTO(new ChildClientEntityAwareDTO());

    dto.getActiveHealthInterventionPlans().add(createHealthInterventionPlan());

    dto.getCreditReportHistories().add(createCreditReportHistory());

    dto.getCsecHistories().add(createCsecHistory());

    dto.getFcEligibilities().add(createFCEligibility());

    dto.getHealthReferrals().add(createHealthReferral());

    dto.getHealthScreenings().add(createHealthScreening());

    dto.getMedicalEligibilityApplications().add(createMedicalEligibilityApplication());

//    dto.getPaternityDetails().add(createClientPaternityDetail());

    dto.getParentalRightsTerminations().add(createParentalRightsTermination());

    dto.getSchoolOriginHistories().add(createSchoolOriginHistory());

    dto.getSpecialEducations().add(createSpecialEducation());

    dto.getTribalAncestryNotifications().add(createTribalAncestryNotification());

    dto.setEntity(createChildClient());

    return dto;
  }

  private TribalAncestryNotification createTribalAncestryNotification() {
    TribalAncestryNotification tribalAncestryNotification = new TribalAncestryNotification();
    tribalAncestryNotification.setNotificationDate(LocalDate.now().minusMonths(1));
    tribalAncestryNotification.setLastUpdateTime(LocalDateTime.now());
    tribalAncestryNotification.setLastUpdateId("Last Update Id");

    tribalAncestryNotification.setId(createTribalAncestryNotificationId());

    tribalAncestryNotification.setCounty(createCounty());

    tribalAncestryNotification.setChildClient(createChildClient());

    return tribalAncestryNotification;
  }

  private TribalAncestryNotification.Id createTribalAncestryNotificationId() {
    return new TribalAncestryNotification.Id("Child Client Id", "Third Id");
  }

  private SpecialEducation createSpecialEducation() {
    SpecialEducation specialEducation = new SpecialEducation();
    specialEducation.setChildClientId("Child Client Id");
    specialEducation.setThirdId("Third Id");
    specialEducation.setEndDate(LocalDate.now().plusYears(1));
    specialEducation.setSpecialEducationInd(false);
    specialEducation.setStartDate(LocalDate.now().minusYears(1));

    return specialEducation;
  }

  private SchoolOriginHistory createSchoolOriginHistory() {
    SchoolOriginHistory schoolOriginHistory = new SchoolOriginHistory();
    schoolOriginHistory.setFkchldClt("Fkchld Clt");
    schoolOriginHistory.setThirdId("Third Id");
    schoolOriginHistory.setSchoolDecision(YesNoUnknown.UNKNOWN);
    schoolOriginHistory.setSchoolDecisionDate(LocalDate.now().minusWeeks(1));
    schoolOriginHistory.setFkedPvdrt("Fked Pvdrt");
    schoolOriginHistory.setLastUpdateTime(LocalDateTime.now());
    schoolOriginHistory.setLastUpdateId("Last Update Id");

    return schoolOriginHistory;
  }

  private ParentalRightTerminationType createParentalRightTerminationType() {
    return (ParentalRightTerminationType) createSystemCodeTable(new ParentalRightTerminationType());
  }

  private ClientPaternityDetail createClientPaternityDetail() {
    ClientPaternityDetail clientPaternityDetail = new ClientPaternityDetail();
    clientPaternityDetail.setId("Id");
    clientPaternityDetail.setAllegedFatherEstablishedPaternity(AllegedFatherPaternityStatus.UNKNOWN);
    clientPaternityDetail.setPaternityHearingDate(LocalDate.now().minusMonths(1));
    clientPaternityDetail.setPaternityTestDate(LocalDate.now().minusMonths(2));
    clientPaternityDetail.setCommentDescription("Comment Description");
    clientPaternityDetail.setEstablishedLocationDescription("Established Location Description");
    clientPaternityDetail.setPaternityTestResultsDescription("Paternity Test Results Description");
    clientPaternityDetail.setClientLegalStatus(LegalStatus.ALLEGED);
    clientPaternityDetail.setBirthFatherStatus(YesNoUnknown.UNKNOWN);
    clientPaternityDetail.setLastUpdateTime(LocalDateTime.now());
    clientPaternityDetail.setLastUpdateId("Last Update Id");

    clientPaternityDetail.setClient(createClient(new Client()));

    clientPaternityDetail.setChildClient(createChildClient());

    return clientPaternityDetail;
  }

  private MedicalEligibilityApplication createMedicalEligibilityApplication() {
    MedicalEligibilityApplication medicalEligibilityApplication = new MedicalEligibilityApplication();
    medicalEligibilityApplication.setIdentifier("Identifier");
    medicalEligibilityApplication.setAdoptionAgreementTermDate(LocalDate.now().plusWeeks(1));
    medicalEligibilityApplication.setCa6AttachedInd(false);
    medicalEligibilityApplication.setIveEligebleStatus(IveEligebleStatus.NOT_YET_MIGRATED);
    medicalEligibilityApplication.setMedEligibilityApplicationDoc("Med Eligibility Application Doc");
    medicalEligibilityApplication.setRetroactiveMonthsCount((short)-1);
    medicalEligibilityApplication.setSignedDate(LocalDate.now().minusWeeks(1));
    medicalEligibilityApplication.setSsnClaimNumber("Ssn Claim Number");
    medicalEligibilityApplication.setSubmissionDate(LocalDate.now().minusWeeks(2));
    medicalEligibilityApplication.setTypeOfApplication(TypeOfApplication.NEW_APPLICATION);
    medicalEligibilityApplication.setLastUpdateTime(LocalDateTime.now());
    medicalEligibilityApplication.setLastUpdateId("Last Update Id");

    medicalEligibilityApplication.setChildClient(createChildClient());

    medicalEligibilityApplication.setStaffPerson(createStaffPerson());

    return medicalEligibilityApplication;
  }

  private HealthScreening createHealthScreening() {
    HealthScreening healthScreening = new HealthScreening();
    healthScreening.setScreeningDate(LocalDate.now().minusMonths(1));
    healthScreening.setScreeningResult(ScreeningResult.NO_REFERRAL_NEEDED);
    healthScreening.setLastUpdateTime(LocalDateTime.now());
    healthScreening.setLastUpdateId("Last Update Id");

    healthScreening.setChildClient(createChildClient());

    healthScreening.setId(createHealthInterventionPlanId());

    healthScreening.setHealthScreeningType(createHealthScreeningType());

    healthScreening.setHealthScreenedByType(createHealthScreenedByType());

    healthScreening.setCommentText(createLongText());

    return healthScreening;
  }

  private HealthScreenedByType createHealthScreenedByType() {
    return (HealthScreenedByType) createSystemCodeTable(new HealthScreenedByType());
  }

  private HealthScreeningType createHealthScreeningType() {
    return (HealthScreeningType) createSystemCodeTable(new HealthScreeningType());
  }

  private HealthInterventionPlan.Id createHealthInterventionPlanId() {
    return new HealthInterventionPlan.Id("Child Client Id", "Third Id");
  }

  private HealthReferral createHealthReferral() {
    HealthReferral healthReferral = new HealthReferral();
    healthReferral.setReferralDate(LocalDate.now().minusYears(1));
    healthReferral.setConsentOnFileDate(LocalDate.now().minusYears(2));
    healthReferral.setComment("Comment");
    healthReferral.setHlRefoCd(ReferralOutcome.ACCEPTED);
    healthReferral.setReferralOutcomeDate(LocalDate.now().minusYears(3));
    healthReferral.setOutOfCountyIndicator(false);
    healthReferral.setLastUpdateTime(LocalDateTime.now());
    healthReferral.setLastUpdateId("Last Update Id");

    healthReferral.setId(createHealthReferralId());

    healthReferral.setChildClient(createChildClient());

    healthReferral.setHealthReferralType(createHealthReferralType());

    healthReferral.setHealthConsentType(createHealthConsentType());

    healthReferral.setHealthReferredToType(createHealthReferredToType());

    return healthReferral;
  }

  private HealthReferredToType createHealthReferredToType() {
    return (HealthReferredToType) createSystemCodeTable(new HealthReferredToType());
  }

  private HealthConsentType createHealthConsentType() {
    return (HealthConsentType) createSystemCodeTable(new HealthConsentType());
  }

  private HealthReferralType createHealthReferralType() {
    return (HealthReferralType) createSystemCodeTable(new HealthReferralType());
  }

  private HealthReferral.Id createHealthReferralId() {
    return new HealthReferral.Id("Child Client Id", "Third Id");
  }

  private FCEligibility createFCEligibility() {
    FCEligibility fcEligibility = new FCEligibility();
    fcEligibility.setDate(LocalDate.now().minusMonths(1));
    fcEligibility.setChildClientId("Child Client Id");
    fcEligibility.setFundingApprovedIndicator(false);
    fcEligibility.setRededeterminationDate(LocalDate.now().minusMonths(2));
    fcEligibility.setTerminationDate(LocalDate.now().plusMonths(1));
    fcEligibility.setThirdId("Third Id");
    fcEligibility.setLastUpdateTime(LocalDateTime.now());
    fcEligibility.setLastUpdateId("Last Update Id");

    fcEligibility.setFcEligibilityDentalReason(createFcEligibilityDentalReason());

    fcEligibility.setFcEligibilityTermReason(createFcEligibilityTermReason());

    return fcEligibility;
  }

  private FcEligibilityTermReason createFcEligibilityTermReason() {
    return (FcEligibilityTermReason) createSystemCodeTable(new FcEligibilityTermReason());
  }

  private FcEligibilityDentalReason createFcEligibilityDentalReason() {
    return (FcEligibilityDentalReason) createSystemCodeTable(new FcEligibilityDentalReason());
  }

  private ParentalRightsTermination createParentalRightsTermination() {
    ParentalRightsTermination parentalRightsTermination = new ParentalRightsTermination();
    parentalRightsTermination.setDate(LocalDate.now().minusMonths(1));
    parentalRightsTermination.setUnderAppealedIndicator(false);
    parentalRightsTermination.setVoluntaryRelinquishmentIndicator(false);
    parentalRightsTermination.setCourtId("Court Id");
    parentalRightsTermination.setCommentDescription("Comment Description");
    parentalRightsTermination.setLocationDescription("Location Description");
    parentalRightsTermination.setCompetencyType(CompetencyType.ADOPTION_WORKER);
    parentalRightsTermination.setCompetencyExaminationDate(LocalDate.now().minusMonths(2));
    parentalRightsTermination.setCompetencyProfessionalName("Competency Professional Name");
    parentalRightsTermination.setConservatorshipAllowsRelIndicator(false);
    parentalRightsTermination.setCourtCaseNumber("Court Case Number");
    parentalRightsTermination.setLastUpdateTime(LocalDateTime.now());
    parentalRightsTermination.setLastUpdateId("Last Update Id");

    parentalRightsTermination.setParent(createClient(new Client()));

    parentalRightsTermination.setChild(createChildClient());

    parentalRightsTermination.setParentalRightTerminationType(createParentalRightTerminationType());

    return parentalRightsTermination;
  }

  private CsecHistory createCsecHistory() {
    CsecHistory csecHistory = new CsecHistory();
    csecHistory.setCreationTimestamp(LocalDateTime.now().minusDays(1));
    csecHistory.setStartDate(LocalDate.now().minusDays(2));
    csecHistory.setEndDate(LocalDate.now().plusDays(1));
    csecHistory.setChildClient("Child Client");
    csecHistory.setThirdId("Third Id");
    csecHistory.setLastUpdateTime(LocalDateTime.now());
    csecHistory.setLastUpdateId("Last Update Id");

    csecHistory.setSexualExploitationType(createSexualExploitationType());

    return csecHistory;
  }

  private SexualExploitationType createSexualExploitationType() {
    return (SexualExploitationType) createSystemCodeTable(new SexualExploitationType());
  }

  private CreditReportHistory createCreditReportHistory() {
    CreditReportHistory creditReportHistory = new CreditReportHistory();
    creditReportHistory.setCommentText("Comment Text");
    creditReportHistory.setCountyRequestDate(LocalDate.now().minusWeeks(1));
    creditReportHistory.setCreditReportOutcomeCode(CreditReportOutcomeCode.CLEAR);
    creditReportHistory.setCreditReportStatusDate(LocalDate.now().minusWeeks(2));
    creditReportHistory.setChildClientId("Child Client Id");
    creditReportHistory.setOutcomeClearedDate(LocalDate.now().minusWeeks(3));
    creditReportHistory.setReportProvidedDate(LocalDate.now().minusWeeks(4));
    creditReportHistory.setRequestRefusualDate(LocalDate.now().minusWeeks(5));
    creditReportHistory.setThirdId("Third Id");

    creditReportHistory.setCreditAgencyType(createCreditAgencyType());

    creditReportHistory.setCreditReportStatusType(new CreditReportStatusType());

    creditReportHistory.setRequestedByType(new CreditReportRequestedByType());

    return creditReportHistory;
  }

  private CreditAgencyType createCreditAgencyType() {
    return (CreditAgencyType) createSystemCodeTable(new CreditAgencyType());
  }

  private HealthInterventionPlan createHealthInterventionPlan() {
    HealthInterventionPlan healthInterventionPlan = new HealthInterventionPlan();
    healthInterventionPlan.setThirdId("Third Id");
    healthInterventionPlan.setInterventionPlanCode(InterventionPlanType.INITIAL_DEVELOPMENTAL);
    healthInterventionPlan.setStartDate(LocalDate.now().minusMonths(1));
    healthInterventionPlan.setEndDate(LocalDate.now());
    healthInterventionPlan.setMedicalNecessityDate(LocalDate.now().minusMonths(2));
    healthInterventionPlan.setMedicalNecessityCode("Medical Necessity Code");
    healthInterventionPlan.setLastUpdateTime(LocalDateTime.now());
    healthInterventionPlan.setLastUpdateId("Last Update Id");

    healthInterventionPlan.setChildClient(createChildClient());

    healthInterventionPlan.setCommentText(createLongText());

    healthInterventionPlan.setHealthPlanTerminationType(createHealthPlanTerminationType());

    return healthInterventionPlan;
  }

  private HealthPlanTerminationType createHealthPlanTerminationType() {
    return (HealthPlanTerminationType) createSystemCodeTable(new HealthPlanTerminationType());
  }

  private ChildClient createChildClient() {
    ChildClient childClient = (ChildClient) createClient(new ChildClient());

    childClient.setAdoptable(Adoptable.ADOPTABLE);
    childClient.setAdoptedAge((short)-1);
    childClient.setAfdcFcEligibilityIndicatorVar(false);
    childClient.setAllEducationInfoOnFileIndicator(false);
    childClient.setAllHealthInfoOnFileIndicator(false);
    childClient.setAttemptToAcquireEducInfoDesc("Attempt To Acquire Educ Info Desc");
    childClient.setAttemptToAcquireHlthInfoDesc("Attempt To Acquire Hlth Info Desc");
    childClient.setAwolAbducted(AwolAbducted.NOT_APPLICABLE);
    childClient.setBirthHistoryIndicatorVar(false);
    childClient.setChildClientLastUpdateId("Child Client Last Update Id");
    childClient.setChildClientLastUpdateTime(LocalDateTime.now());
    childClient.setChildIndianAncestryIndicator(false);
    childClient.setCollegeIndicator(false);
    childClient.setCurrentCaseId("Current Case Id");
    childClient.setDeathCircumstancesTypeCode((short)-2);
    childClient.setDisabilityDiagnosed(Disability.NO);
    childClient.setDrmsHealthEducPassportDoc("Drms Health Educ Passport Doc");
    childClient.setDrmsHePassportDocOld("Drms He Passport Doc Old");
    childClient.setDrmsVoluntaryPlcmntAgrmntDoc("Drms Voluntary Plcmnt Agrmnt Doc");
    childClient.setFc2EligApplicationIndicatorVar(false);
    childClient.setFoodStampsApplicationDate(LocalDate.now().minusWeeks(1));
    childClient.setFoodStampsApplicationIndicator(false);
    childClient.setIcwaEligibility(IcwaEligibility.UNKNOWN);
    childClient.setIntercountryAdoptDisruptedIndicator(false);
    childClient.setIntercountryAdoptDissolvedIndicator(false);
    childClient.setMedEligibilityApplicationIndicatorVar(false);
    childClient.setMinorNmdParentIndicator(false);
    childClient.setParentalRightsLimitedIndicator(false);
    childClient.setParentalRightsTermintnIndicatorVar(false);
    childClient.setPaternityIndividualIndicatorVar(false);
    childClient.setPostsecVocIndicator(false);
    childClient.setPreviouslyAdopted(PreviouslyAdopted.UNKNOWN);
    childClient.setSafelySurrenderedBabiesIndicatorVar(false);
    childClient.setSaw1EligApplicationIndicatorVar(false);
    childClient.setSawsCaseSerialNumber(-3);
    childClient.setSiiNextScreeningDueDate(LocalDate.now().minusWeeks(2));
    childClient.setSijsScheduledInterviewDate(LocalDate.now().minusWeeks(3));
    childClient.setSsiSspApplicationIndicator(false);
    childClient.setTribalAncestryNotifctnIndicatorVar(false);
    childClient.setTribalCustomaryAdoptionDate(LocalDate.now().minusWeeks(4));
    childClient.setTribalCustomaryAdoptionIndicator(false);
    childClient.setVictimClientId("Victim Client Id");

    return childClient;
  }

  private ClientEntityAwareDTO createClientEntityAwareDTO(ClientEntityAwareDTO dto) {
    dto.setUpdateClientPhoneticName(false);
    dto.setEnriched(false);

    dto.setPersistentClientState(createClient(new Client()));

    dto.setOtherClientName(createOtherClientName());

    dto.getClientRelationships().add(createClientRelationship());

    dto.getClientServiceProviders().add(createClientServiceProvider());

    dto.getDasHistories().add(createDasHistory());

    dto.getDeliveredService().add(createDeliveredService());

    dto.getNearFatalities().add(createNearFatality());

    dto.getPlacementEpisodes().add(createPlacementEpisode());

    dto.getSafetyAlerts().add(createSafetyAlerts());

    dto.setEntity(createClient(new Client()));

    return dto;
  }

  private SafetyAlert createSafetyAlerts() {
    SafetyAlert safetyAlert = new SafetyAlert();
    safetyAlert.setFkClientId("Fk Client Id");
    safetyAlert.setThirdId("Third Id");
    safetyAlert.setActivationDate(LocalDate.now().minusWeeks(1));
    safetyAlert.setDeactivationDate(LocalDate.now().plusWeeks(1));
    safetyAlert.setLastUpdateTime(LocalDateTime.now());
    safetyAlert.setLastUpdateId("Last Update Id");

    safetyAlert.setActivationGovernmentEntityType(createCounty());

    safetyAlert.setDeactivationGovernmentEntityType(createCounty());

    safetyAlert.setActivationExplanationText(createLongText());

    safetyAlert.setDeactivationExplanationText(createLongText());

    safetyAlert.setActivationReasonType(new SafetyAlertActivationReasonType());

    return safetyAlert;
  }

  private LongText createLongText() {
    LongText longText = new LongText();
    longText.setIdentifier("Identifier");
    longText.setCountySpecificCode("County Specific Code");
    longText.setTextDescription("Text Description");
    longText.setLastUpdateTime(LocalDateTime.now());
    longText.setLastUpdateId("Last Update Id");

    return longText;
  }

  private NearFatality createNearFatality() {
    NearFatality nearFatality = new NearFatality();
    nearFatality.setClientId("Client Id");
    nearFatality.setFatalityDate(LocalDateTime.now().minusYears(1));
    nearFatality.setThirdId("Third Id");
    nearFatality.setLastUpdateTime(LocalDateTime.now());
    nearFatality.setLastUpdateId("Last Update Id");

    return nearFatality;
  }

  private DeliveredService createDeliveredService() {
    DeliveredService deliveredService = new DeliveredService();
    deliveredService.setCountySpecificCode(createCounty());
    deliveredService.setIndividualType(IndividualType.CLIENTS);
    deliveredService.setIndividualId("Individual Id");
    deliveredService.setStartDate(LocalDate.now().minusMonths(1));
    deliveredService.setEndDate(LocalDate.now().plusMonths(1));
    deliveredService.setPrimaryDeliveredServiceId("Primary Delivered Service Id");
    deliveredService.setLastUpdateTime(LocalDateTime.now());
    deliveredService.setLastUpdateId("Last Update Id");

    deliveredService.setServiceContactType(createServiceContactType());

    return deliveredService;
  }

  private ServiceContactType createServiceContactType() {
    return (ServiceContactType) createSystemCodeTable(new ServiceContactType());
  }

  private DasHistory createDasHistory() {
    DasHistory dasHistory = new DasHistory();
    dasHistory.setFkclientT("Fkclient T");
    dasHistory.setThirdId("Third Id");
    dasHistory.setStartDate(LocalDate.now().minusMonths(1));
    dasHistory.setEndDate(LocalDate.now().plusMonths(1));
    dasHistory.setProvidedByCode("Provided By Code");
    dasHistory.setOtherDescription("Other Description");
    dasHistory.setLstUpdId("Lst Upd Id");
    dasHistory.setLstUpdTs(new Timestamp(new Date().getTime()));

    return dasHistory;
  }

  private ClientServiceProvider createClientServiceProvider() {
    ClientServiceProvider clientServiceProvider = new ClientServiceProvider();
    clientServiceProvider.setId("Id");
    clientServiceProvider.setDescription("Description");
    clientServiceProvider.setEndDate(LocalDate.now().plusYears(1));
    clientServiceProvider.setStartDate(LocalDate.now().minusYears(1));
    clientServiceProvider.setServiceProviderId("Service Provider Id");
    clientServiceProvider.setClient(createClient(new Client()));
    clientServiceProvider.setLastUpdateTime(LocalDateTime.now());
    clientServiceProvider.setLastUpdateId("Last Update Id");

    return clientServiceProvider;
  }

  private ClientRelationship createClientRelationship() {
    ClientRelationship clientRelationship = new ClientRelationship();
    clientRelationship.setIdentifier("Identifier");
    clientRelationship.setAbsentParentIndicator(false);
    clientRelationship.setEndDate(LocalDate.now());
    clientRelationship.setSameHomeStatus(YesNoUnknown.UNKNOWN);
    clientRelationship.setStartDate(LocalDate.now().minusYears(1));
    clientRelationship.setSecondaryClient(createClient(new Client()));
    clientRelationship.setPrimaryClient(createClient(new Client()));
    clientRelationship.setLastUpdateTime(LocalDateTime.now());
    clientRelationship.setLastUpdateId("Last Update Id");

    clientRelationship.setType(createClientRelationshipType());

    return clientRelationship;
  }

  private ClientRelationshipType createClientRelationshipType() {
    return (ClientRelationshipType) createSystemCodeTable(new ClientRelationshipType());
  }

  private OtherClientNameDTO createOtherClientName() {
    OtherClientNameDTO otherClientNameDTO = new OtherClientNameDTO();
    otherClientNameDTO.setFirstName("First Name");
    otherClientNameDTO.setLastName("Last Name");
    otherClientNameDTO.setMiddleName("Middle Name");
    otherClientNameDTO.setNamePrefixDescription("Name Prefix Description");
    otherClientNameDTO.setSuffixTitleDescription("Suffix Title Description");
    otherClientNameDTO.setClientId("Client Id");
    otherClientNameDTO.setNameType((short)-1);

    return otherClientNameDTO;
  }

  private Client createClient(Client client) {
    client.setIdentifier("Identifier");
    client.setAdoptionStatus(AdoptionStatus.NOT_APPLICABLE);
    client.setAlienRegistrationNumber("Alien Registration Number");
    client.setBirthDate(LocalDate.now().minusYears(2));
    client.setBirthFacilityName("Birth Facility Name");
    client.setBirthStateCode((short)-1);
    client.setBirthCountryCode((short)-2);
    client.setChildClientIndicator(false);
    client.setCommonFirstName("Common First Name");
    client.setCommonLastName("Common Last Name");
    client.setCommonMiddleName("Common Middle Name");
    client.setConfidentialityInEffectIndicator(false);
    client.setConfidentialityActionDate(LocalDate.now().minusMonths(1));
    client.setCreationDate(LocalDate.now().minusDays(10));
    client.setDeathDate(LocalDate.now().plusYears(100));
    client.setDeathReasonText("Death Reason Text");
    client.setDriverLicenseNumber("Driver License Number");
    client.setDriverLicenseStateCode((short)-3);
    client.setGender(Gender.UNKNOWN);
    client.setImmigrationCountryCode((short)-4);
    client.setImmigrationStatusCode((short)-5);
    client.setIncapacitatedParentStatus(IncapacitatedParentStatus.NOT_APPLICABLE);
    client.setLiterateStatus(LiterateStatus.NOT_APPLICABLE);
    client.setMaritalCohabitationHistoryIndicator(false);
    client.setMaritalStatusCode((short)-6);
    client.setMilitaryStatus(MilitaryStatus.NO_INFORMATION_AVAILABLE);
    client.setNamePrefixDescription("Name Prefix Description");
    client.setOutstandingWarrantIndicator(false);
    client.setPrimaryEthnicityCode((short)-7);
    client.setPrimaryLanguageCode((short)-8);
    client.setReligionCode((short)-9);
    client.setSecondaryLanguageCode((short)-10);
    client.setSensitivity(Sensitivity.NOT_APPLICABLE);
    client.setSensitiveHealthInfoOnFileIndicator(false);
    client.setSocialSecurityNumber("Social Security Number");
    client.setSocialSecurityNumberChangedCode("Social Security Number Changed Code");
    client.setSuffixTitleDescription("Suffix Title Description");
    client.setParentUnemployedStatus(ParentUnemployedStatus.NOT_APPLICABLE);
    client.setCommentDescription("Comment Description");
    client.setDateOfBirthStatus(DateOfBirthStatus.NOT_PROVIDED);
    client.setBirthplaceVerifiedIndicator(false);
    client.setHispanicOrigin(HispanicOrigin.UNABLE_TO_DETERMINE);
    client.setCurrentCaChildrenServiceIndicator(false);
    client.setCurrentlyRegionalCenterIndicator(false);
    client.setCurrentlyOtherDescription("Currently Other Description");
    client.setPreviousCaChildrenServiceIndicator(false);
    client.setPreviousRegionalCenterIndicator(false);
    client.setPreviousOtherDescription("Previous Other Description");
    client.setIndividualHealthCarePlanIndicator(false);
    client.setLimitationOnScpHealthIndicator(false);
    client.setBirthCity("Birth City");
    client.setHealthSummaryText("Health Summary Text");
    client.setMotherParentalRightTermDate(LocalDate.now().minusMonths(2));
    client.setFatherParentalRightTermDate(LocalDate.now().minusMonths(3));
    client.setZippyCreatedIndicator(false);
    client.setDeathPlace("Death Place");
    client.setTribalMembershipVerifcationIndicator(false);
    client.setTribalAncestryClientIndicator(false);
    client.setSoc158SealedClientIndicator(false);
    client.setDeathDateVerifiedIndicator(false);
    client.setEmailAddress("Email Address");
    client.setAdjudicatedDelinquentIndicator(false);
    client.setEthnicityUnableToDetermineReason(UnableToDetermineReason.INDIVIDUAL_DOES_NOT_KNOW);
    client.setHispanicUnableToDetermineReason(UnableToDetermineReason.INDIVIDUAL_DOES_NOT_KNOW);
    client.setSoc158placementsStatus(Soc158placementsStatus.NO_SOC_158_PLACEMENTS);
    client.setSexualOrientationCode((short)-11);
    client.setSexualOrientationUnableToDetermineCode("Sexual Orientation Unable To Determine Code");
    client.setSexualOrientationNotListedDescription("Sexual Orientation Not Listed Description");
    client.setGenderIdentityCode((short)-12);
    client.setGenderIdentityNotListedDescription("Gender Identity Not Listed Description");
    client.setGenderEspressionCode((short)-13);
    client.setClientIndexNumber("Client Index Number");
    client.setLastUpdateTime(new Timestamp(new Date().getTime()));
    client.setLastUpdateId("Last Update Id");

    client.setPlacementEpisodes(createPlacementEpisodes());

    client.setNameType(createNameType());

    return client;
  }

  private Set<PlacementEpisode> createPlacementEpisodes() {
    Set<PlacementEpisode> placementEpisodes = new HashSet<>();
    placementEpisodes.add(createPlacementEpisode());

    return placementEpisodes;
  }

  private PlacementEpisode createPlacementEpisode() {
    PlacementEpisode placementEpisode = new PlacementEpisode();
    placementEpisode.setCounty(createCounty());
    placementEpisode.setRemovalDt(LocalDate.now().minusWeeks(1));
    placementEpisode.setAgyRspc((short)-1);
    placementEpisode.setAsgnSwCd("Asgn Sw Cd");
    placementEpisode.setEligWkCd("Elig Wk Cd");
    placementEpisode.setChlRgtCd("Chl Rgt Cd");
    placementEpisode.setDetnordDt(LocalDate.now().minusMonths(1));
    placementEpisode.setDspOrdDt(LocalDate.now().minusMonths(2));
    placementEpisode.setPlepsEndt(LocalDate.now().minusMonths(3));
    placementEpisode.setNfcPlctB("Nfc Plct B");
    placementEpisode.setOutCstDt(LocalDate.now().minusMonths(4));
    placementEpisode.setOutCstTm(new Time(new Date().getTime()));
    placementEpisode.setPetnFildt(LocalDate.now().minusMonths(5));
    placementEpisode.setFcisrvwtB("Fcisrvwt B");
    placementEpisode.setFcishrgtB("Fcishrgt B");
    placementEpisode.setPrvtSvc((short)-2);
    placementEpisode.setRlsRsnc((short)-3);
    placementEpisode.setRmvRsnc((short)-4);
    placementEpisode.setRemovalTm(new Time(new Date().getTime()));
    placementEpisode.setRmvByCd("Rmv By Cd");
    placementEpisode.setRmvFrmNm("Rmv Frm Nm");
    placementEpisode.setRmvFrm1C((short)-5);
    placementEpisode.setTmpCstind("Tmp Cstind");
    placementEpisode.setLstUpdId("Lst Upd Id");
    placementEpisode.setLstUpdTs(new Timestamp(new Date().getTime()));
    placementEpisode.setFkclientT("Fkclient T");
    placementEpisode.setThirdId("Third Id");
    placementEpisode.setComntDsc("Comnt Dsc");
    placementEpisode.setEndEntDt(LocalDate.now().minusMonths(6));
    placementEpisode.setRmvEntDt(LocalDate.now().minusMonths(7));
    placementEpisode.setRmvcr1Id("Rmvcr1 Id");
    placementEpisode.setRmvcr1Cd("Rmvcr1 Cd");
    placementEpisode.setRmvcr2Id("Rmvcr2 Id");
    placementEpisode.setRmvcr2Cd("Rmvcr2 Cd");
    placementEpisode.setRmvFrm2C((short)-6);
    placementEpisode.setTermRsCd("Term Rs Cd");
    placementEpisode.setTermDsc("Term Dsc");
    placementEpisode.setTermTyC((short)-7);
    placementEpisode.setFmlystrCd("Fmlystr Cd");
    placementEpisode.setBirthyr1("Birthyr1");
    placementEpisode.setBirthyr2("Birthyr2");
    placementEpisode.setRsfsurbNm("Rsfsurb Nm");
    placementEpisode.setGvrEntc((short)-8);
    placementEpisode.setPlc24HrCd("Plc24 Hr Cd");

    placementEpisode.setOutOfHomePlacements(createOutOfHomePlacements());

    return placementEpisode;
  }

  private Set<OutOfHomePlacement> createOutOfHomePlacements() {
    Set<OutOfHomePlacement> outOfHomePlacements = new HashSet<>();
    outOfHomePlacements.add(createOutOfHomePlacement());

    return outOfHomePlacements;
  }

  private OutOfHomePlacement createOutOfHomePlacement() {
    OutOfHomePlacement outOfHomePlacement = new OutOfHomePlacement();
    outOfHomePlacement.setIdentifier("Identifier");
    outOfHomePlacement.setFkplcHmT("Fkplc Hm T");
    outOfHomePlacement.setAgrEffDt(LocalDate.now().minusWeeks(1));
    outOfHomePlacement.setAprvlNo("Aprvl No");
    outOfHomePlacement.setApvStc((short)-1);
    outOfHomePlacement.setChdpRfDt(LocalDate.now().minusWeeks(2));
    outOfHomePlacement.setChdpRqind("Chdp Rqind");
    outOfHomePlacement.setDfprntInd("Dfprnt Ind");
    outOfHomePlacement.setSoc158Doc("Soc158 Doc");
    outOfHomePlacement.setAfdcPrdoc("Afdc Prdoc");
    outOfHomePlacement.setAgnfpAdoc("Agnfp Adoc");
    outOfHomePlacement.setAgnghAdoc("Agngh Adoc");
    outOfHomePlacement.setEmrgPlind("Emrg Plind");
    outOfHomePlacement.setEndDt(LocalDate.now().plusWeeks(1));
    outOfHomePlacement.setExmpHmind("Exmp Hmind");
    outOfHomePlacement.setGhmPlcind("Ghm Plcind");
    outOfHomePlacement.setIntNtcDt(LocalDate.now().minusWeeks(3));
    outOfHomePlacement.setPayeetpc((short)-2);
    outOfHomePlacement.setPndLicind("Pnd Licind");
    outOfHomePlacement.setPlcgRnc((short)-3);
    outOfHomePlacement.setProgramNo("Program No");
    outOfHomePlacement.setScpRltc((short)-4);
    outOfHomePlacement.setExtApvno("Ext Apvno");
    outOfHomePlacement.setXtApvStc((short)-5);
    outOfHomePlacement.setStartDt(LocalDate.now().minusWeeks(4));
    outOfHomePlacement.setPayeeEndt(LocalDate.now().plusWeeks(2));
    outOfHomePlacement.setSubpFstnm("Subp Fstnm");
    outOfHomePlacement.setSubpLstnm("Subp Lstnm");
    outOfHomePlacement.setSubpMidnm("Subp Midnm");
    outOfHomePlacement.setPyeStrtdt(LocalDate.now().minusWeeks(5));
    outOfHomePlacement.setYouakmCd("Youakm Cd");
    outOfHomePlacement.setLstUpdId("Lst Upd Id");
    outOfHomePlacement.setLstUpdTs(new Timestamp(new Date().getTime()));
    outOfHomePlacement.setFkplcEpst("Fkplc Epst");
    outOfHomePlacement.setFkplcEps0("Fkplc Eps0");
    outOfHomePlacement.setPlRtnldsc("Pl Rtnldsc");
    outOfHomePlacement.setRemvlDsc("Remvl Dsc");
    outOfHomePlacement.setScproxind("Scproxind");
    outOfHomePlacement.setHepDt(LocalDate.now().minusWeeks(6));
    outOfHomePlacement.setSbplrsnc((short)-5);
    outOfHomePlacement.setSibplcTxt("Sibplc Txt");
    outOfHomePlacement.setScproxTxt("Scprox Txt");
    outOfHomePlacement.setGrddepInd("Grddep Ind");
    outOfHomePlacement.setSchPplCd("Sch Ppl Cd");
    outOfHomePlacement.setSibtghrCd("Sibtghr Cd");
    outOfHomePlacement.setTdcnslInd("Tdcnsl Ind");
    outOfHomePlacement.setTdagrDt(LocalDate.now().minusWeeks(6));
    outOfHomePlacement.setCpwnmdInd("Cpwnmd Ind");
    outOfHomePlacement.setCpwnmdCnt((short)-6);

    outOfHomePlacement.setPlacementHome(createPlacementHome());

    return outOfHomePlacement;
  }

  private PlacementHome createPlacementHome() {
    PlacementHome placementHome = new PlacementHome();
    placementHome.setFacilityType((short)-1);
    placementHome.setStateCode((short)-2);
    placementHome.setPayeeStateCode((short)-3);
    placementHome.setLicStc((short)-4);
    placementHome.setIdentifier("Identifier");
    placementHome.setLicenseNo("License No");
    placementHome.setAgeFrmNo((short)-5);
    placementHome.setAgeToNo((short)-6);
    placementHome.setAtCapInd("At Cap Ind");
    placementHome.setBckPersnm("Bck Persnm");
    placementHome.setBckExtNo("Bck Ext No");
    placementHome.setBckTelNo("Bck Tel No");
    placementHome.setCertfPndt(LocalDate.now().minusDays(1));
    placementHome.setChlcrPlcd("Chlcr Plcd");
    placementHome.setCityNm("City Nm");
    placementHome.setClSrvdc((short)-7);
    placementHome.setConfEfind("Conf Efind");
    placementHome.setCurOcpNo((short)-8);
    placementHome.setEmrShltcd("Emr Shltcd");
    placementHome.setFaxNo("Fax No");
    placementHome.setFrgAdrtB("Frg Adrt B");
    placementHome.setGndrAcpcd("Gndr Acpcd");
    placementHome.setGeoRgntcd("Geo Rgntcd");
    placementHome.setInhmVstcd("Inhm Vstcd");
    placementHome.setMaxCapNo((short)-9);
    placementHome.setLaVndrId("La Vndr Id");
    placementHome.setLicAplDt(LocalDate.now().minusDays(2));
    placementHome.setLicCapNo((short)-10);
    placementHome.setLicEfctdt(LocalDate.now().minusDays(3));
    placementHome.setLicExpDt(LocalDate.now().minusDays(4));
    placementHome.setLicStatdt(LocalDate.now().minusDays(5));
    placementHome.setLicBsnc((short)-11);
    placementHome.setLicnseeNm("Licnsee Nm");
    placementHome.setLicensrCd("Licensr Cd");
    placementHome.setFacltyNm("Faclty Nm");
    placementHome.setOprtdByid("Oprtd Byid");
    placementHome.setOprtdBycd("Oprt Bycd");
    placementHome.setpCityNm("p City Nm");
    placementHome.setPyeFstnm("Pye Fstnm");
    placementHome.setPyeLstnm("Pye Lstnm");
    placementHome.setPyeMidnm("Pye Midnm");
    placementHome.setPstreetNm("Pstreet Nm");
    placementHome.setPstreetNo("Pstreet No");
    placementHome.setpZipNo("Zip No");
    placementHome.setPrmCnctnm("Prm Cnctnm");
    placementHome.setPrmExtNo("Prm Ext No");
    placementHome.setPrmSubsnm("Prm Subsnm");
    placementHome.setPrmTelNo("Prm Tel No");
    placementHome.setPvdTrnscd("Pvd Trnscd");
    placementHome.setPubTrnscd("Pub Trnscd");
    placementHome.setStreetNm("Street Nm");
    placementHome.setStreetNo("Street No");
    placementHome.setZipNo("Zip No");
    placementHome.setAddrDsc("Addr Dsc");
    placementHome.setSpcharDsc("Spchar Dsc");
    placementHome.setCtyprfDsc("Ctyprf Dsc");
    placementHome.setEdPvrDsc("Ed Pvr Dsc");
    placementHome.setEnvFctdsc("Env Fctdsc");
    placementHome.setHazrdsDsc("Hazrds Dsc");
    placementHome.setLisPrfdsc("Lis Prfdsc");
    placementHome.setPetsDsc("Pets Dsc");
    placementHome.setRlgActdsc("Rlg Actdsc");
    placementHome.setPyZipSfx("Py Zip Sfx");
    placementHome.setZipSfxNo("Zip Sfx No");
    placementHome.setApStatTp((short)-12);
    placementHome.setCertCmplt("Cert Cmplt");
    placementHome.setLaPCtynm("La P Ctynm");
    placementHome.setLaPFstnm("La P Fstnm");
    placementHome.setLaPLstnm("La P Lstnm");
    placementHome.setLaPMidnm("La P Midnm");
    placementHome.setLaPStnm("La P Stnm");
    placementHome.setLaPStno("La P Stno");
    placementHome.setLaPZipno("La P Zipno");
    placementHome.setLaPZpsfx("La P Zpsfx");
    placementHome.setLaPBsnss("La P Bsnss");
    placementHome.setApStatDt(LocalDate.now().minusDays(6));
    placementHome.setLaPPhNo("La P Ph No");
    placementHome.setLaPPhEx("La P Ph Ex");
    placementHome.setAdhmonly("Adhmonly");
    placementHome.setPyeExtNo("Pye Ext No");
    placementHome.setPyeTelNo("Pye Tel No");
    placementHome.setArcassInd("Arcass Ind");
    placementHome.setComfacInd("Comfac Ind");
    placementHome.setTrnhsgInd("Trnhsg Ind");
    placementHome.setTrnhsgFac("Trnhsg Fac");
    placementHome.setNewlicNo("Newlic No");
    placementHome.setNewlicUpd("Newlic Upd");
    placementHome.setOldfacId("Oldfac Id");
    placementHome.setEmCntB("Em Cnt B");
    placementHome.setEndDt(LocalDate.now().plusDays(1));
    placementHome.setPhEndc((short)-13);
    placementHome.setEndComdsc("End Comdsc");
    placementHome.setGvrEntc((short)-14);
    placementHome.setLaPayeeState((short)-15);
    placementHome.setLastUpdateTime(LocalDateTime.now());
    placementHome.setLastUpdateId("Last Update Id");

    placementHome.setCountyLicenseCase(createCountyLicenseCase());

    placementHome.setPlacementHomeProfiles(createPlacementHomeProfiles());

    placementHome.setPlacementHomeNotes(createPlacementHomeNoteses());

    placementHome.setPlacementFacilityTypeHistory(createPlacementFacilityTypeHistories());

    placementHome.setPrimarySubstituteCareProvider(createSubstituteCareProvider());

    placementHome.setOtherAdultsInPlacementHomes(createOtherAdultsInPlacementHomes());

    placementHome.setOtherChildrenInPlacementHomes(createOtherChildrenInPlacementHomes());

    return placementHome;
  }

  private List<OtherChildrenInPlacementHome> createOtherChildrenInPlacementHomes() {
    List<OtherChildrenInPlacementHome> otherChildrenInPlacementHomes = new ArrayList<>();
    otherChildrenInPlacementHomes.add(createOtherChildrenInPlacementHome());

    return otherChildrenInPlacementHomes;
  }

  private OtherChildrenInPlacementHome createOtherChildrenInPlacementHome() {
    OtherChildrenInPlacementHome otherChildrenInPlacementHome = new OtherChildrenInPlacementHome();
    otherChildrenInPlacementHome.setIdentifier("Identifier");
    otherChildrenInPlacementHome.setBirthDt(LocalDate.now().minusYears(1));
    otherChildrenInPlacementHome.setGenderCd("Gender Cd");
    otherChildrenInPlacementHome.setOthchldNm("Othchld Nm");
    otherChildrenInPlacementHome.setLstUpdId("Lst Upd Id");
    otherChildrenInPlacementHome.setLstUpdTs(LocalDateTime.now());
    otherChildrenInPlacementHome.setFkplcHmT("Fkplc Hm T");
    otherChildrenInPlacementHome.setYrIncAmt(new BigDecimal("100000"));

    otherChildrenInPlacementHome.setOtherPeopleScpRelationships(createOtherPeopleScpRelationships());

    otherChildrenInPlacementHome.setBackgroundChecks(createBackgroundChecks());

    return otherChildrenInPlacementHome;
  }

  private List<OtherAdultsInPlacementHome> createOtherAdultsInPlacementHomes() {
    List<OtherAdultsInPlacementHome> otherAdultsInPlacementHomes = new ArrayList<>();
    otherAdultsInPlacementHomes.add(createOtherAdultsInPlacementHome());

    return otherAdultsInPlacementHomes;
  }

  private List<PlacementFacilityTypeHistory> createPlacementFacilityTypeHistories() {
    List<PlacementFacilityTypeHistory> placementFacilityTypeHistories = new ArrayList<>();
    placementFacilityTypeHistories.add(createPlacementFacilityTypeHistory());

    return placementFacilityTypeHistories;
  }

  private List<PlacementHomeNotes> createPlacementHomeNoteses() {
    List<PlacementHomeNotes> placementHomeNotes = new ArrayList<>();
    placementHomeNotes.add(createPlacementHomeNotes());

    return placementHomeNotes;
  }

  private List<PlacementHomeProfile> createPlacementHomeProfiles() {
    List<PlacementHomeProfile> placementHomeProfiles = new ArrayList<>();
    placementHomeProfiles.add(createPlacementHomeProfile());

    return placementHomeProfiles;
  }

  private OtherAdultsInPlacementHome createOtherAdultsInPlacementHome() {
    OtherAdultsInPlacementHome otherAdultsInPlacementHome = new OtherAdultsInPlacementHome();
    otherAdultsInPlacementHome.setIdentifier("Identifier");
    otherAdultsInPlacementHome.setBirthDt(LocalDate.now().minusYears(1));
    otherAdultsInPlacementHome.setEndDt(LocalDate.now().plusYears(1));
    otherAdultsInPlacementHome.setGenderCd("Gender Cd");
    otherAdultsInPlacementHome.setOthAdltnm("Oth Adltnm");
    otherAdultsInPlacementHome.setStartDt(LocalDate.now().minusYears(2));
    otherAdultsInPlacementHome.setLstUpdId("Lst Upd Id");
    otherAdultsInPlacementHome.setLstUpdTs(LocalDateTime.now());
    otherAdultsInPlacementHome.setFkplcHmT("Fkplc Hm T");
    otherAdultsInPlacementHome.setComntDsc("Comnt Dsc");
    otherAdultsInPlacementHome.setOthAdlCd("Oth Adl Cd");
    otherAdultsInPlacementHome.setIdentfdDt(LocalDate.now().minusYears(3));
    otherAdultsInPlacementHome.setResostInd("Resost Ind");
    otherAdultsInPlacementHome.setPassbcCd("Passbc Cd");

    otherAdultsInPlacementHome.setOtherPeopleScpRelationships(createOtherPeopleScpRelationships());

    otherAdultsInPlacementHome.setBackgroundChecks(createBackgroundChecks());

    otherAdultsInPlacementHome.setOutOfStateChecks(createOutOfStateChecks());

    return otherAdultsInPlacementHome;
  }

  private List<OutOfStateCheck> createOutOfStateChecks() {
    List<OutOfStateCheck> outOfStateChecks = new ArrayList<>();
    outOfStateChecks.add(createOutOfStateCheck());

    return outOfStateChecks;
  }

  private List<BackgroundCheck> createBackgroundChecks() {
    List<BackgroundCheck> backgroundChecks = new ArrayList<>();
    backgroundChecks.add(createBackgroundCheck());

    return backgroundChecks;
  }

  private List<OtherPeopleScpRelationship> createOtherPeopleScpRelationships() {
    List<OtherPeopleScpRelationship> otherPeopleScpRelationships = new ArrayList<>();
    OtherPeopleScpRelationship otherPeopleScpRelationship = createOtherPeopleScpRelationship();

    otherPeopleScpRelationships.add(otherPeopleScpRelationship);

    return otherPeopleScpRelationships;
  }

  private OtherPeopleScpRelationship createOtherPeopleScpRelationship() {
    OtherPeopleScpRelationship otherPeopleScpRelationship = new OtherPeopleScpRelationship();
    otherPeopleScpRelationship.setIdentifier("Identifier");
    otherPeopleScpRelationship.setClntrelc((short)-1);
    otherPeopleScpRelationship.setLstUpdId("Lst Upd Id");
    otherPeopleScpRelationship.setLstUpdTs(LocalDateTime.now());
    otherPeopleScpRelationship.setFkothAdlt("Fkoth Adlt");
    otherPeopleScpRelationship.setFkothKidt("Fkoth Kidt");

    otherPeopleScpRelationship.setSubstituteCareProvider(createSubstituteCareProvider());

    return otherPeopleScpRelationship;
  }

  private PlacementFacilityTypeHistory createPlacementFacilityTypeHistory() {
    PlacementFacilityTypeHistory placementFacilityTypeHistory = new PlacementFacilityTypeHistory();
    placementFacilityTypeHistory.setCreationTimestamp(LocalDateTime.now().minusDays(1));
    placementFacilityTypeHistory.setEndTimestamp(LocalDateTime.now().plusDays(1));
    placementFacilityTypeHistory.setFkplcHmT("Fkplc Hm T");
    placementFacilityTypeHistory.setLastUpdateId("Last Update Id");
    placementFacilityTypeHistory.setLastUpdateTimestamp(LocalDateTime.now());
    placementFacilityTypeHistory.setPlacementFacilityType((short)-1);
    placementFacilityTypeHistory.setStartTimestamp(LocalDateTime.now().minusDays(2));
    placementFacilityTypeHistory.setThirdId("Third Id");

    return placementFacilityTypeHistory;
  }

  private PlacementHomeNotes createPlacementHomeNotes() {
    PlacementHomeNotes placementHomeNotes = new PlacementHomeNotes();
    placementHomeNotes.setIdentifier("Identifier");
    placementHomeNotes.setReceiveDt(LocalDate.now().minusWeeks(1));
    placementHomeNotes.setRefLicind("Ref Licind");
    placementHomeNotes.setSubmitrNm("Submitr Nm");
    placementHomeNotes.setLstUpdId("Lst Upd Id");
    placementHomeNotes.setLstUpdTs(LocalDateTime.now());
    placementHomeNotes.setFkplcHmT("Fkplc Hm T");
    placementHomeNotes.setComntDsc("Comnt Dsc");

    return placementHomeNotes;
  }

  private PlacementHomeProfile createPlacementHomeProfile() {
    PlacementHomeProfile placementHomeProfile = new PlacementHomeProfile();
    placementHomeProfile.setThirdId("Third Id");
    placementHomeProfile.setChrctrC((short)-1);
    placementHomeProfile.setChrctrCd("Chrctr Cd");
    placementHomeProfile.setLstUpdId("Lst Upd Id");
    placementHomeProfile.setLstUpdTs(LocalDateTime.now());
    placementHomeProfile.setFkplcHmT("Fkplc Hm T");

    return placementHomeProfile;
  }

  private SubstituteCareProvider createSubstituteCareProvider() {
    SubstituteCareProvider substituteCareProvider = new SubstituteCareProvider();
    substituteCareProvider.setIdentifier("Identifier");
    substituteCareProvider.setAddTelNo(-1L);
    substituteCareProvider.setAddExtNo(-2);
    substituteCareProvider.setBirthDt(LocalDate.now().minusYears(1));
    substituteCareProvider.setCaDlicNo("Ca Dlic No");
    substituteCareProvider.setCityNm("City Nm");
    substituteCareProvider.setEmplyrNm("Emplyr Nm");
    substituteCareProvider.setFirstNm("First Nm");
    substituteCareProvider.setFrgAdrtB("Frg Adrt B");
    substituteCareProvider.setGenderInd("Gender Ind");
    substituteCareProvider.setIndTrbc((short)-3);
    substituteCareProvider.setLastNm("Last Nm");
    substituteCareProvider.setMidIniNm("Mid Ini Nm");
    substituteCareProvider.setNmprfxDsc("Nmprfx Dsc");
    substituteCareProvider.setSsNo("Ss No");
    substituteCareProvider.setStateC((short)-4);
    substituteCareProvider.setStreetNm("Street Nm");
    substituteCareProvider.setStreetNo("Street No");
    substituteCareProvider.setSufxTldsc("Sufx Tldsc");
    substituteCareProvider.setZipNo("Zip No");
    substituteCareProvider.setLstUpdId("Lst Upd Id");
    substituteCareProvider.setLstUpdTs(LocalDateTime.now());
    substituteCareProvider.setZipSfxNo("Zip Sfx No");
    substituteCareProvider.setEducation((short)-5);
    substituteCareProvider.setEmplStat((short)-6);
    substituteCareProvider.setPrimInc((short)-7);
    substituteCareProvider.setSecInc((short)-8);
    substituteCareProvider.setYrIncAmt(new BigDecimal("100000"));
    substituteCareProvider.setHispCd("Hisp Cd");
    substituteCareProvider.setMrtlStc((short)-9);
    substituteCareProvider.setLisownind("Lisownind");
    substituteCareProvider.setLisPerId("Lis Per Id");
    substituteCareProvider.setEmailAddr("Email Addr");
    substituteCareProvider.setEthUdCd("Eth Ud Cd");
    substituteCareProvider.setHispUdCd("Hisp Ud Cd");
    substituteCareProvider.setResostInd("Resost Ind");
    substituteCareProvider.setPassbcCd("Passbc Cd");

    substituteCareProvider.setBackgroundChecks(createBackgroundChecks());

    substituteCareProvider.setOutOfStateChecks(createOutOfStateChecks());

    return substituteCareProvider;
  }

  private OutOfStateCheck createOutOfStateCheck() {
    OutOfStateCheck outOfStateCheck = new OutOfStateCheck();
    outOfStateCheck.setIdentifier("Identifier");
    outOfStateCheck.setRcpntId("Rcpnt Id");
    outOfStateCheck.setRcpntCd("Rcpnt Cd");
    outOfStateCheck.setStateC((short)-1);
    outOfStateCheck.setRegmntInd("Regmnt Ind");
    outOfStateCheck.setRequestDt(LocalDate.now().minusMonths(1));
    outOfStateCheck.setReceiveDt(LocalDate.now().minusMonths(2));
    outOfStateCheck.setStatusDt(LocalDate.now().minusMonths(3));
    outOfStateCheck.setStatusCd("Status Cd");
    outOfStateCheck.setLstUpdId("Lst Upd Id");
    outOfStateCheck.setLstUpdTs(LocalDateTime.now());
    outOfStateCheck.setFkcoltrlT("Fkcoltrl T");

    return outOfStateCheck;
  }

  private BackgroundCheck createBackgroundCheck() {
    BackgroundCheck backgroundCheck = new BackgroundCheck();
    backgroundCheck.setIdentifier("Identifier");
    backgroundCheck.setRcpntCd("Rcpnt Cd");
    backgroundCheck.setRcpntId("Rcpnt Id");
    backgroundCheck.setBkgrchkc((short)-1);
    backgroundCheck.setBkgrchkDt(LocalDate.now().minusYears(1));
    backgroundCheck.setLstUpdId("Lst Upd Id");
    backgroundCheck.setLstUpdTs(LocalDateTime.now());
    backgroundCheck.setFkcoltrlT("Fkcoltrl T");
    backgroundCheck.setReceivedDate(LocalDate.now().minusYears(2));

    return backgroundCheck;
  }

  private CountyLicenseCase createCountyLicenseCase() {
    CountyLicenseCase countyLicenseCase = new CountyLicenseCase();
    countyLicenseCase.setAnnCmpDt(LocalDate.now().minusMonths(1));
    countyLicenseCase.setAnnDueDt(LocalDate.now().minusMonths(2));
    countyLicenseCase.setFireInd("Fire Ind");
    countyLicenseCase.setFireRDt(LocalDate.now().minusMonths(3));
    countyLicenseCase.setFfhType((short)-1);
    countyLicenseCase.setIdentifier("Identifier");
    countyLicenseCase.setLstUpdId("Lst Upd Id");
    countyLicenseCase.setLstUpdTs(LocalDateTime.now());
    countyLicenseCase.setLicAgeFr((short)-2);
    countyLicenseCase.setLicAgeTo((short)-3);
    countyLicenseCase.setLicGndr("Lic Gndr");
    countyLicenseCase.setTrngPlan("Trng Plan");
    countyLicenseCase.setPrtyInfo("Prty Info");
    countyLicenseCase.setTrngCmplt("Trng Cmplt");
    countyLicenseCase.setTrngDt(LocalDate.now().minusMonths(4));
    countyLicenseCase.setCntySpfcd("Cnty Spfcd");
    countyLicenseCase.setClcApvc(-4);

    countyLicenseCase.setLicensingVisits(createLicensingVisits());

    countyLicenseCase.setStaffPerson(createStaffPerson());

    countyLicenseCase.setLicensingIssues(createLicensingIssues());

    return countyLicenseCase;
  }

  private List<LicensingIssue> createLicensingIssues() {
    List<LicensingIssue> licensingIssues = new ArrayList<>();
    licensingIssues.add(createLicensingIssue());

    return licensingIssues;
  }

  private List<LicensingVisit> createLicensingVisits() {
    List<LicensingVisit> licensingVisits = new ArrayList<>();
    licensingVisits.add(createLicensingVisit());

    return licensingVisits;
  }

  private LicensingIssue createLicensingIssue() {
    LicensingIssue licensingIssue = new LicensingIssue();
    licensingIssue.setIdentifier("Identifier");
    licensingIssue.setIssueDate(LocalDate.now().minusMonths(1));
    licensingIssue.setComplaintFindingCode("Complaint Finding Code");
    licensingIssue.setComplaintDeficiencyCode("Complaint Deficiency Code");
    licensingIssue.setIssueNotes("Issue Notes");
    licensingIssue.setLastUpdateTime(LocalDateTime.now());
    licensingIssue.setLastUpdateId("Last Update Id");

    licensingIssue.setIssueType(createSystemCode());

    return licensingIssue;
  }

  private SystemCode createSystemCode() {
    SystemCode systemCode = new SystemCode();
    systemCode.setSystemId((short)-1);
    systemCode.setCategoryId((short)-2);
    systemCode.setInactiveIndicator("Inactive Indicator");
    systemCode.setOtherCode("Other Code");
    systemCode.setShortDescription("Short Description");
    systemCode.setLogicalId("Logical Id");
    systemCode.setThirdId("Third Id");
    systemCode.setFkMeta("Fk Meta");
    systemCode.setLongDescription("Long Description");
    systemCode.setLastUpdateTime(LocalDateTime.now());
    systemCode.setLastUpdateId("Last Update Id");

    return systemCode;
  }

  private StaffPerson createStaffPerson() {
    StaffPerson staffPerson = new StaffPerson();
    staffPerson.setCntySpfcd("Cnty Spfcd");
    staffPerson.setIdentifier("Identifier");
    staffPerson.setEndDt(LocalDate.now().plusMonths(1));
    staffPerson.setFirstName("First Name");
    staffPerson.setJobTlDsc("Job Tl Dsc");
    staffPerson.setLastName("Last Name");
    staffPerson.setMidIniNm("Mid Ini Nm");
    staffPerson.setNmprfxDsc("Nmprfx Dsc");
    staffPerson.setPhoneNo(-1L);
    staffPerson.setTelExtNo(-2);
    staffPerson.setStartDt(LocalDate.now().minusMonths(1));
    staffPerson.setSufxTldsc("Sufx Tldsc");
    staffPerson.setTlcmtrInd("Tlcmtr Ind");
    staffPerson.setLstUpdId("Lst Upd Id");
    staffPerson.setLstUpdTs(new Timestamp(new Date().getTime()));
    staffPerson.setFkcwsOfft("Fkcws Offt");
    staffPerson.setAvlocDsc("Avloc Dsc");
    staffPerson.setSsrsWkrid("Ssrs Wkrid");
    staffPerson.setDtywkrInd("Dtywkr Ind");
    staffPerson.setFkcwsaddrt("Fkcwsaddrt");
    staffPerson.setEmailAddr("Email Addr");

    staffPerson.setCounty(createCounty());

    return staffPerson;
  }

  private County createCounty() {
    return (County) createSystemCodeTable(new County());
  }

  private LicensingVisit createLicensingVisit() {
    LicensingVisit licensingVisit = new LicensingVisit();
    licensingVisit.setIdentifier("Identifier");
    licensingVisit.setLstUpdId("Lst Upd Id");
    licensingVisit.setLstUpdTs(new Timestamp(new Date().getTime()));
    licensingVisit.setVisitDate(LocalDate.now().minusWeeks(1));
    licensingVisit.setVisitNote("Visit Note");
    licensingVisit.setFkcntyCst("Fkcnty Cst");
    licensingVisit.setCntySpfcd("Cnty Spfcd");

    licensingVisit.setVisitType(createVisitType());

    return licensingVisit;
  }

  private VisitType createVisitType() {
    return (VisitType) createSystemCodeTable(new VisitType());
  }

  private NameType createNameType() {
    return (NameType) createSystemCodeTable(new NameType());
  }

  private SystemCodeTable createSystemCodeTable(SystemCodeTable systemCodeTable) {
    systemCodeTable.setSystemId((short)-1);
    systemCodeTable.setCategoryId((short)-2);
    systemCodeTable.setInactiveIndicator(false);
    systemCodeTable.setOtherCode("Other Code");
    systemCodeTable.setShortDescription("Short Description");
    systemCodeTable.setLogicalId("Logical Id");
    systemCodeTable.setLastUpdatedId("Last Updated Id");
    systemCodeTable.setLastUpdatedTime(new Timestamp(new Date().getTime()));
    systemCodeTable.setThirdId("Third Id");
    systemCodeTable.setFkMeta("Fk Meta");
    systemCodeTable.setLongDescription("Long Description");
    return systemCodeTable;
  }
}
