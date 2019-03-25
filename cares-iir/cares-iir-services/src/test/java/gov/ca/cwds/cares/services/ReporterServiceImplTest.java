package gov.ca.cwds.cares.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import gov.ca.cwds.cares.interfaces.model.Address;
import gov.ca.cwds.cares.persistence.entity.ReporterEntity;
import gov.ca.cwds.cares.persistence.repository.ReporterRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.HashSet;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cares.common.exception.DataIntegrityException;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cares.persistence.entity.PersonCrossReferenceEntity;
import gov.ca.cwds.cares.persistence.repository.PersonCrossReferenceRepository;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.DfhCommArea;
import gov.ca.cwds.cics.model.ReporterData;
import gov.ca.cwds.cics.restclient.CicsReporterRestApiClient;

/**
 * CWDS J Team
 */
@RunWith(MockitoJUnitRunner.class)
public class ReporterServiceImplTest {

  @Mock
  private BusinessRulesExecutor<BreResponse, ReporterData> businessRuleExecutor;

  @Mock
  private CicsReporterRestApiClient cicsReporterRestApiClient;

  @Mock
  private PersonCrossReferenceRepository personCrossReferenceRepository;

  @Mock
  private ReporterRepository reporterRepository;

  @InjectMocks
  private ReporterServiceImpl reporterService;

  @Test
  public void shouldCreateReporterSuccess() throws Exception {
    Reporter request = createReporter();

    assertBreServiceCall();

    assertCreateCicsServiceCall();

    String personId = "1234567890";
    Collection<PersonCrossReferenceEntity> referenceEntities = new HashSet<>();
    PersonCrossReferenceEntity referenceEntity =
        createPersonCrossReferenceEntity(personId, null, "R");
    referenceEntities.add(referenceEntity);

    when(personCrossReferenceRepository.findByXrefId(argThat(new ArgumentMatcher<String>() {
      @Override
      public boolean matches(String reporterLegacyId) {
        return reporterLegacyId.endsWith("0WM");
      }
    }))).thenReturn(referenceEntities);

    Reporter response = reporterService.createReporter(request);

    assertEquals(response.getIdentifier(), personId);
    assertEquals(request.getFirstName(), response.getFirstName());
    assertEquals(request.getLastName(), response.getLastName());
    assertEquals(request.getLastName(), response.getLastName());
    assertEquals(request.getPrimaryPhoneNumber(), response.getPrimaryPhoneNumber());
    assertEquals(request.getPrimaryPhoneExtension(), response.getPrimaryPhoneExtension());
    assertEquals(request.getRelationToChild(), response.getRelationToChild());
    verify(businessRuleExecutor).executeBusinessRules(any(), any());
    verify(cicsReporterRestApiClient).createReporter(any());
    verify(personCrossReferenceRepository).findByXrefId(any());
  }

  @Test(expected = DataIntegrityException.class)
  public void shouldThrowDataIntegrityExceptionOnMissingReporterReferenceWhenCreateReporter() throws Exception {
    Reporter request = createReporter();

    assertBreServiceCall();

    assertCreateCicsServiceCall();

    Collection<PersonCrossReferenceEntity> referenceEntities = new HashSet<>();
    String personId = "1234567890";
    PersonCrossReferenceEntity referenceEntity =
        createPersonCrossReferenceEntity(personId, null, "Z");
    referenceEntities.add(referenceEntity);

    when(personCrossReferenceRepository.findByXrefId(argThat(new ArgumentMatcher<String>() {
      @Override
      public boolean matches(String reporterLegacyId) {
        return reporterLegacyId.endsWith("0WM");
      }
    }))).thenReturn(referenceEntities);

    reporterService.createReporter(request);
    verify(businessRuleExecutor).executeBusinessRules(any(), any());
    verify(cicsReporterRestApiClient).createReporter(any());
    verify(personCrossReferenceRepository).findByXrefId(any());
  }

  @Test
  public void shouldGetReporterSuccess() {
    String id = "test id";

    Collection<PersonCrossReferenceEntity> referenceEntities = new HashSet<>();
    PersonCrossReferenceEntity referenceEntity = createPersonCrossReferenceEntity(id, "test xrefId", "R");
    referenceEntities.add(referenceEntity);

    when(personCrossReferenceRepository.findByPersonId("test id")).thenReturn(referenceEntities);
    when(reporterRepository.findById("test xrefId")).thenReturn(Optional.of(createReporterEntity()));

    Reporter response = reporterService.getReporter(id);

    assertReporterEntity(response);
    verify(personCrossReferenceRepository).findByPersonId(any());
    verify(reporterRepository).findById(any());
  }

  @Test(expected = DataIntegrityException.class)
  public void shouldThrowDataIntegrityExceptionOnMissingReporterReferenceWhenGetReporter() {
    String id = "test id";

    Collection<PersonCrossReferenceEntity> referenceEntities = new HashSet<>();
    PersonCrossReferenceEntity referenceEntity = createPersonCrossReferenceEntity(id, "test xrefId", "Z");
    referenceEntities.add(referenceEntity);

    when(personCrossReferenceRepository.findByPersonId("test id")).thenReturn(referenceEntities);

    reporterService.getReporter(id);
    verify(personCrossReferenceRepository).findByPersonId(any());
  }

  @Test
  public void shouldReturnNullWhenNoReporterFoundOnGetReporter() {
    String id = "test id";

    Collection<PersonCrossReferenceEntity> referenceEntities = new HashSet<>();
    PersonCrossReferenceEntity referenceEntity = createPersonCrossReferenceEntity(id, "test xrefId", "R");
    referenceEntities.add(referenceEntity);

    when(personCrossReferenceRepository.findByPersonId("test id")).thenReturn(referenceEntities);
    when(reporterRepository.findById("test xrefId")).thenReturn(Optional.ofNullable(null));

    Reporter response = reporterService.getReporter(id);

    assertNull(response);
    verify(personCrossReferenceRepository).findByPersonId(any());
    verify(reporterRepository).findById(any());
  }

  @Test
  public void shouldUpdateReporterSuccess() throws Exception {
    Reporter request = createReporter();
    request.setIdentifier("1234567890");
    request.setLastUpdateTimestamp(LocalDateTime.now());

    assertBreServiceCall();

    assertUpdateCicsServiceCall();

    Collection<PersonCrossReferenceEntity> referenceEntities = new HashSet<>();
    String personId = "1234567890";
    PersonCrossReferenceEntity referenceEntity =
        createPersonCrossReferenceEntity(personId, personId, "R");
    referenceEntities.add(referenceEntity);

    when(personCrossReferenceRepository.findByPersonId("1234567890")).thenReturn(referenceEntities);

    Reporter response = reporterService.updateReporter(request);

    assertEquals(response.getIdentifier(), personId);
    assertEquals(request.getFirstName(), response.getFirstName());
    assertEquals(request.getLastName(), response.getLastName());
    assertEquals(request.getLastName(), response.getLastName());
    assertEquals(request.getPrimaryPhoneNumber(), response.getPrimaryPhoneNumber());
    assertEquals(request.getPrimaryPhoneExtension(), response.getPrimaryPhoneExtension());
    assertEquals(request.getRelationToChild(), response.getRelationToChild());
    verify(businessRuleExecutor).executeBusinessRules(any(), any());
    verify(cicsReporterRestApiClient).updateReporter(any(), any());
    verify(personCrossReferenceRepository).findByPersonId(any());
  }

  @Test
  public void shouldReturnNullWhenUpdateReporterWithNullParameter() throws Exception {
    assertNull(reporterService.createReporter(null));
  }

  private void assertCreateCicsServiceCall() {
    CicsResponse cicsResponse = new CicsResponse();
    cicsResponse.setDfhCommArea(new DfhCommArea());

    when(cicsReporterRestApiClient
        .createReporter(argThat(new ArgumentMatcher<CicsReporterRequest>() {
          @Override
          public boolean matches(CicsReporterRequest cicsReporterRequest) {
            ReporterData reporterData = cicsReporterRequest.getReporterData();
            assertReporterData(reporterData);
            return true;
          }
        }))).thenReturn(cicsResponse);
  }

  private void assertUpdateCicsServiceCall() {
    CicsResponse cicsResponse = new CicsResponse();
    cicsResponse.setDfhCommArea(new DfhCommArea());

    when(cicsReporterRestApiClient
        .updateReporter(argThat(new ArgumentMatcher<CicsReporterRequest>() {
          @Override
          public boolean matches(CicsReporterRequest cicsReporterRequest) {
            ReporterData reporterData = cicsReporterRequest.getReporterData();
            assertReporterData(reporterData);
            return true;
          }
        }), argThat(new ArgumentMatcher<LocalDateTime>() {
          @Override
          public boolean matches(LocalDateTime localDateTime) {
            return localDateTime != null;
          }
        }))).thenReturn(cicsResponse);
  }

  private void assertBreServiceCall() {
    when(businessRuleExecutor.executeBusinessRules(argThat(new ArgumentMatcher<String>() {
      @Override
      public boolean matches(String ruleName) {
        assertEquals("ReporterBusinessRules", ruleName);
        return true;
      }
    }), argThat(new ArgumentMatcher<ReporterData>() {
      @Override
      public boolean matches(ReporterData reporterData) {
        assertReporterData(reporterData);
        return true;
      }
    }))).thenReturn(new BreResponse());
  }

  private void assertReporterEntity(Reporter response) {
    assertEquals("test Identifier", response.getIdentifier());
    assertEquals("test FirstName", response.getFirstName());
    assertEquals("test LastName", response.getLastName());
    assertEquals(new Long(-9), response.getPrimaryPhoneNumber());
    assertEquals(new Integer(-8), response.getPrimaryPhoneExtension());
    assertEquals(LocalDate.of(2000, Month.JANUARY, 1), response.getBirthDate());
    assertEquals("test EmployerName", response.getEmployerName());
    assertEquals("test TitleDescription", response.getTitleDescription());
    assertEquals(LocalDateTime.of(2000, Month.JANUARY, 3, 0, 1), response.getLastUpdateTimestamp());
    assertNotNull(response.getAddress());
    assertEquals(new Integer(-3), response.getAddress().getStateCode());
    assertEquals(new Integer(-2), response.getAddress().getZipCode());
    assertEquals("test CityName", response.getAddress().getCity());
    assertEquals("test StreetName", response.getAddress().getStreetName());
    assertEquals("test StreetNumber", response.getAddress().getStreetNumber());
    assertNull(response.getRelationToChild());
    assertNull(response.getAddress().getIdentifier());
    assertNull(response.getAddress().getZipSuffix());
    assertNull(response.getAddress().getStreetType());
    assertNull(response.getAddress().getUnitNumber());
    assertNull(response.getAddress().getUnitType());
    assertNull(response.getAddress().getAddressDescription());
    assertNull(response.getAddress().getPostStreetDirection());
    assertNull(response.getAddress().getPreStreetDirection());
    assertNull(response.getAddress().getEmergencyPhoneNumber());
    assertNull(response.getAddress().getEmergencyPhoneExtension());
    assertNull(response.getAddress().getMessagePhoneNumber());
    assertNull(response.getAddress().getMessagePhoneExtension());
    assertNull(response.getAddress().getPrimaryPhoneNumber());
    assertNull(response.getAddress().getMessagePhoneExtension());
    assertNull(response.getAddress().getGovernmentEntityCode());
    assertNull(response.getAddress().getLastUpdateId());
    assertNull(response.getAddress().getLastUpdateTimestamp());
    assertNull(response.getAddress().getForeignAddressIndicator());
    assertNull(response.getAddress().getAddressHeader());
    assertNull(response.getAddress().getLatitude());
    assertNull(response.getAddress().getLongitude());
  }

  private void assertReporterData(ReporterData reporterData) {
    assertNotNull(reporterData);

    assertNotNull(reporterData.getIdentifier());
    assertEquals("0WM", reporterData.getTxnHeaderStaffId());
    assertEquals("test first name", reporterData.getFirstName());
    assertEquals("test last name", reporterData.getLastName());
    assertEquals(new Long(-1L), reporterData.getPrimaryPhoneNumber());
    assertEquals(new Integer(-2), reporterData.getPrimaryPhoneExtensionNumber());
    assertEquals(new Integer(0), reporterData.getCommunicationMethod());
    assertEquals(new Integer(0), reporterData.getCollateralClientReporterRelationship());
    assertEquals("N", reporterData.getConfidentialWaiverIndicator());
    assertEquals("99", reporterData.getCountySpecificCode());
    assertEquals("N", reporterData.getFeedbackRequiredIndicator());
    assertEquals("N", reporterData.getMandatedReporterIndicator());
    assertEquals("test street number", reporterData.getStreetNumber());
    assertEquals("test street name", reporterData.getStreetName());
    assertEquals("test city", reporterData.getCityName());
    assertEquals(new Integer(-3), reporterData.getState());
    assertEquals(new Integer(-4), reporterData.getZipNumber());
    assertEquals(new Integer(-5), reporterData.getZipSuffixNumber());
    assertNull(reporterData.getFeedbackDocument());
    assertNull(reporterData.getFeedbackDate());
    assertNull(reporterData.getFkLawEnforcement());
    assertNull(reporterData.getFkReferral());
    assertNull(reporterData.getMiddleInitialName());
    assertNull(reporterData.getMessagePhoneExtensionNumber());
    assertNull(reporterData.getMessagePhoneNumber());
    assertNull(reporterData.getNamePrefixDescription());
    assertNull(reporterData.getBadgeNumber());
    assertNull(reporterData.getEmployerName());
    assertNull(reporterData.getSuffixTitleDescription());
  }

  private ReporterEntity createReporterEntity() {
    ReporterEntity reporterEntity = new ReporterEntity();
    reporterEntity.setBadgeNumber("test BadgeNumber");
    reporterEntity.setBirthDate(LocalDate.of(2000, Month.JANUARY, 1));
    reporterEntity.setCityName("test CityName");
    reporterEntity.setColltrClientRptrReltnshpType(-4);
    reporterEntity.setCommunicationMethodCode(-5);
    reporterEntity.setConfidentialWaiverIndicator("test ConfidentialWaiverIndicator");
    reporterEntity.setCountySpecificCode("test CountySpecificCode");
    reporterEntity.setDrmsMandatedRprtrFeedback("test DrmsMandatedRprtrFeedback");
    reporterEntity.setEmployerName("test EmployerName");
    reporterEntity.setFeedbackDate(LocalDate.of(2000, Month.JANUARY, 2));
    reporterEntity.setFirstName("test FirstName");
    reporterEntity.setIdentifier("test Identifier");
    reporterEntity.setLastName("test LastName");
    reporterEntity.setLastUpdateId("test LastUpdateId");
    reporterEntity.setLastUpdateTimestamp(LocalDateTime.of(2000, Month.JANUARY, 3, 0, 1));
    reporterEntity.setLawEnforcementId("test LawEnforcementId");
    reporterEntity.setMandatedReporterIndicator("test MandatedReporterIndicator");
    reporterEntity.setMessagePhoneExtension(-6);
    reporterEntity.setMessagePhoneNumber(-7L);
    reporterEntity.setMiddleInitialName("test MiddleInitialName");
    reporterEntity.setNamePrefixDescription("test NamePrefixDescription");
    reporterEntity.setPrimaryPhoneExtension(-8);
    reporterEntity.setPrimaryPhoneNumber(-9L);
    reporterEntity.setReferralId("test ReferralId");
    reporterEntity.setStateCode(-3);
    reporterEntity.setStreetName("test StreetName");
    reporterEntity.setStreetNumber("test StreetNumber");
    reporterEntity.setTitleDescription("test TitleDescription");
    reporterEntity.setZipCode(-2);
    reporterEntity.setZipSuffix(-1);

    reporterEntity.setPersonCrossReferences(new HashSet<>());
    return reporterEntity;
  }

  private Reporter createReporter() {
    Reporter reporter = new Reporter();
    reporter.setFirstName("test first name");
    reporter.setLastName("test last name");
    reporter.setPrimaryPhoneNumber(-1L);
    reporter.setPrimaryPhoneExtension(-2);
    reporter.setRelationToChild("test relation to child");
    reporter.setAddress(createAddress());
    return reporter;
  }

  private Address createAddress() {
    Address address = new Address();
    address.setStreetNumber("test street number");
    address.setStreetName("test street name");
    address.setCity("test city");
    address.setStateCode(-3);
    address.setZipCode(-4);
    address.setZipSuffix(-5);
    return address;
  }

  private PersonCrossReferenceEntity createPersonCrossReferenceEntity(String personId,
      String xrefId, String xrefCode) {
    PersonCrossReferenceEntity referenceEntity = new PersonCrossReferenceEntity();
    referenceEntity.setPersonId(personId);
    referenceEntity.setXrefId(xrefId);
    referenceEntity.setXrefCode(xrefCode);
    return referenceEntity;
  }
}
