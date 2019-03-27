package gov.ca.cwds.cares.services.mapping;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import gov.ca.cwds.cares.interfaces.model.Address;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cares.persistence.entity.PersonCrossReferenceEntity;
import gov.ca.cwds.cares.persistence.entity.ReporterEntity;
import gov.ca.cwds.cics.model.ReporterData;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ReporterMapperTest {
  Reporter reporter;
  Address address;
  ReporterEntity savedReporter;
  String personId;

  @Before
  public void setup(){
    reporter = new Reporter();
    reporter.setFirstName("Fred");
    reporter.setLastName("Johnson");
    reporter.setPrimaryPhoneNumber(1234567890L);
    reporter.setPrimaryPhoneExtension(433);
    reporter.setRelationToChild("Mother");
    reporter.setBirthDate(LocalDate.of(2000,12,30));
    reporter.setEmployerName("Acme Inc");
    reporter.setTitleDescription("My Title");
    reporter.setLastUpdateTimestamp(LocalDateTime.now());
    reporter.setIdentifier("abc987");

    address = new Address();
    address.setStreetNumber("123");
    address.setStreetName("Main st");
    address.setCity("Sacramento");
    address.setStateCode(1);
    address.setZipCode(99999);
    address.setZipSuffix(9876);

    reporter.setAddress(address);

    savedReporter = new ReporterEntity();
    savedReporter.setIdentifier("XZ123Gz32");
    savedReporter.setCommunicationMethodCode(123);
    savedReporter.setConfidentialWaiverIndicator("N");
    savedReporter.setCountySpecificCode("02");
    savedReporter.setColltrClientRptrReltnshpType(345);
    savedReporter.setFeedbackRequiredIndicator("Y");
    savedReporter.setDrmsMandatedRprtrFeedback("01");
    savedReporter.setFeedbackDate(LocalDate.now());
    savedReporter.setLawEnforcementId("03");
    savedReporter.setReferralId("04");
    savedReporter.setLastUpdateId("oxa");
    savedReporter.setLastUpdateTimestamp(LocalDateTime.now());
    savedReporter.setMiddleInitialName("W");
    savedReporter.setMandatedReporterIndicator("Z");
    savedReporter.setMessagePhoneExtension(1111);
    savedReporter.setMessagePhoneNumber(9165551234L);
    savedReporter.setNamePrefixDescription("");
    savedReporter.setPrimaryPhoneNumber(9165559876L);
    savedReporter.setPrimaryPhoneExtension(9999);
    savedReporter.setBadgeNumber("999876");
    savedReporter.setCityName("Redding");
    savedReporter.setEmployerName("Johnson, Inc");
    savedReporter.setFirstName("John");
    savedReporter.setLastName("Baker");
    savedReporter.setStreetName("Elm St");
    savedReporter.setStreetNumber("453");
    savedReporter.setZipCode(99987);
    savedReporter.setStateCode(07);
    savedReporter.setSuffixTitleDescription("Mr");
    savedReporter.setZipSuffix(9871);
    savedReporter.setBirthDate(LocalDate.of(1968,11,20));
    savedReporter.setTitleDescription("Dr");
    List crossReferences = new ArrayList();
    PersonCrossReferenceEntity crossreference = new PersonCrossReferenceEntity();
    personId = "ABC999";
    crossreference.setPersonId(personId);
    crossReferences.add(crossreference);
    savedReporter.setPersonCrossReferences(crossReferences);
  }

  @Test
  public void shouldMapReporterFieldsToReporterData(){
    ReporterData data =  ReporterMapper.INSTANCE.mapReporterToReporterData(reporter);

    assertEquals(reporter.getFirstName(), data.getFirstName());
    assertEquals(reporter.getLastName(), data.getLastName());
    assertEquals(reporter.getPrimaryPhoneNumber(), data.getPrimaryPhoneNumber());
    assertEquals(reporter.getPrimaryPhoneExtension(), data.getPrimaryPhoneExtensionNumber());
    assertEquals(reporter.getEmployerName(), data.getEmployerName());
    assertEquals(reporter.getTitleDescription(), data.getTitleDescription());
    assertEquals(reporter.getBirthDate(), data.getBirthDate());
  }

  @Test
  public void shouldMapReportAddressFields(){
    ReporterData data =  ReporterMapper.INSTANCE.mapReporterToReporterData(reporter);
    assertEquals(reporter.getAddress().getStreetName(), data.getStreetName());
    assertEquals(reporter.getAddress().getStreetNumber(), data.getStreetNumber());
    assertEquals(reporter.getAddress().getCity(), data.getCityName());
    assertEquals(reporter.getAddress().getStateCode(), data.getState());
    assertEquals(reporter.getAddress().getZipCode(), data.getZipNumber());
    assertEquals(reporter.getAddress().getZipSuffix(), data.getZipSuffixNumber());
  }

  @Test
  public void shouldNotMapReporterIdentifierToReporterData(){
    reporter.setIdentifier("ABC123");
    ReporterData data =  ReporterMapper.INSTANCE.mapReporterToReporterData(reporter);

    assertNull( data.getIdentifier());
  }

  @Test
  public void shouldSetReporterDefaultValues(){
    ReporterData data =  ReporterMapper.INSTANCE.mapReporterToReporterData(reporter);

    assertEquals(data.getTxnHeaderStaffId(), "0WM");
    assertTrue("Expected communication method to be defaulted to 0",data.getCommunicationMethod() == 0);
    assertTrue("Expected Collateral Cient Report Relationship to be defaulted to 0",data.getCollateralClientReporterRelationship() == 0);
    assertEquals(data.getConfidentialWaiverIndicator(), "N");
    assertEquals(data.getCountySpecificCode(), "99");
    assertEquals(data.getFeedbackRequiredIndicator(), "N");
    assertEquals(data.getMandatedReporterIndicator(), "N");
  }

  @Test
  public void shouldMapReporterDataToReporterField(){
    Reporter reporter =  ReporterMapper.INSTANCE.mapReporterEntityToReporter(savedReporter);

    assertEquals(savedReporter.getFirstName(), reporter.getFirstName());
    assertEquals(savedReporter.getLastName(), reporter.getLastName());
    assertEquals(savedReporter.getPrimaryPhoneNumber(), reporter.getPrimaryPhoneNumber());
    assertEquals(savedReporter.getPrimaryPhoneExtension(), reporter.getPrimaryPhoneExtension());
    assertEquals(savedReporter.getBirthDate(), reporter.getBirthDate());
    assertEquals(savedReporter.getEmployerName(), reporter.getEmployerName());
    assertEquals(savedReporter.getTitleDescription(), reporter.getTitleDescription());
    assertEquals(savedReporter.getLastUpdateTimestamp(), reporter.getLastUpdateTimestamp());
  }

  @Test
  public void shouldMapCrossReporterIdToReporter(){
    savedReporter.setIdentifier(personId);
    Reporter reporter =  ReporterMapper.INSTANCE.mapReporterEntityToReporter(savedReporter);
    assertEquals(personId, reporter.getIdentifier());
  }

  @Test
  public void shouldMapReportEntityAddressFields(){
    Reporter reporter =  ReporterMapper.INSTANCE.mapReporterEntityToReporter(savedReporter);

    assertEquals(savedReporter.getStreetName(), reporter.getAddress().getStreetName());
    assertEquals(savedReporter.getStreetNumber(), reporter.getAddress().getStreetNumber());
    assertEquals(savedReporter.getCityName(), reporter.getAddress().getCity());
    assertEquals(savedReporter.getStateCode(), reporter.getAddress().getStateCode());
    assertEquals(savedReporter.getZipCode(), reporter.getAddress().getZipCode());
  }

  @Test
  public void shouldNotMapReportEntityZipSuffixFields(){
    savedReporter.setZipSuffix(12345);
    Reporter reporter =  ReporterMapper.INSTANCE.mapReporterEntityToReporter(savedReporter);

    assertNull(reporter.getAddress().getZipSuffix());
  }
}
