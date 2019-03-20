package gov.ca.cwds.cares.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import gov.ca.cwds.cares.interfaces.model.Address;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

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

  @InjectMocks
  private ReporterServiceImpl reporterService;

  @Test
  public void shouldCreateSuccess() throws Exception {
    Reporter request = createReporter();

    assertBreServiceCall();

    assertCreateCicsServiceCall();

    Collection<PersonCrossReferenceEntity> referenceEntities = new HashSet<>();
    String personId = "1234567890";
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

  @Test
  public void shouldUpdateSuccess() throws Exception {
    Reporter request = updateReporter();

    assertBreServiceCall();

    assertUpdateCicsServiceCall();

    Collection<PersonCrossReferenceEntity> referenceEntities = new HashSet<>();
    String personId = "1234567890";
    PersonCrossReferenceEntity referenceEntity =
        createPersonCrossReferenceEntity(personId, personId, "R");
    referenceEntities.add(referenceEntity);

    when(personCrossReferenceRepository.findByPersonId(argThat(new ArgumentMatcher<String>() {
      @Override
      public boolean matches(String personId) {
        return personId.matches("1234567890");
      }
    }))).thenReturn(referenceEntities);

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

  @Test(expected = DataIntegrityException.class)
  public void shouldThrowDataIntegrityExceptionOnMissingReporterReference() throws Exception {
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

  private Reporter updateReporter() {
    Reporter reporter = createReporter();
    reporter.setIdentifier("1234567890");
    reporter.setLastUpdateTimestamp(LocalDateTime.now());
    return reporter;
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
