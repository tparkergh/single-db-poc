package gov.ca.cwds.cares.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.api.BusinessRuleService;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreRequestData;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cares.common.Constants;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.DfhCommArea;
import gov.ca.cwds.cics.model.ReporterData;
import gov.ca.cwds.cics.restclient.CicsReporterRestApiClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * CWDS J Team
 */
@RunWith(MockitoJUnitRunner.class)
public class ReporterServiceImplTest {

  @Mock
  private BusinessRuleService businessRuleService;

  @Mock
  private CicsReporterRestApiClient cicsReporterRestApiClient;

  @InjectMocks
  private ReporterServiceImpl reporterService;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Before
  public void setUp() {
    reporterService.setJacksonObjectMapper(objectMapper);
  }

  @Test
  public void testCreateReporter_Success() throws Exception {
    Reporter reporter = createReporter();

    BreResponse breResponse = new BreResponse();

    when(businessRuleService.executeBusinessRules(argThat(new ArgumentMatcher<BreRequest>() {
      @Override
      public boolean matches(BreRequest breRequest) {
        assertEquals("ReporterCreateScreenBusinessRules", breRequest.getBusinessRuleSetName());
        BreRequestData breRequestData = breRequest.getDataObjects().get(0);
        ReporterData breReporter = null;
        try {
          breReporter = (ReporterData) objectMapper.readValue(
              objectMapper.writeValueAsString(breRequestData.getDataObject()),
              Class.forName(breRequestData.getDataObjectClassName())
          );
        } catch (Exception e) {
          fail("BreRequest matcher exception");
        }
        assertNotNull(breReporter.getIdentifier());
        assertEquals("test first name", breReporter.getFirstName());
        assertEquals("test last name", breReporter.getLastName());
        assertEquals(new Long(-1), breReporter.getPrimaryPhoneNumber());
        assertEquals(new Integer(-2), breReporter.getPrimaryPhoneExtensionNumber());

        return true;
      }
    }))).thenReturn(breResponse);


    DfhCommArea dfhCommArea = new DfhCommArea();
    dfhCommArea.setProgReturnCode(0);
    CicsResponse cicsResponse = new CicsResponse();
    cicsResponse.setDfhCommArea(dfhCommArea);

    when(cicsReporterRestApiClient.createReporter(argThat(new ArgumentMatcher<CicsReporterRequest>() {

      @Override
      public boolean matches(CicsReporterRequest cicsReporterRequest) {
        ReporterData reporterData = cicsReporterRequest.getReporterData();
        assertNotNull(reporterData);

        assertNotNull(reporterData.getIdentifier());
        assertEquals(Constants.LOGGED_USER_STAFF_ID, reporterData.getTxnHeaderStaffId());
        assertEquals("test first name", reporterData.getFirstName());
        assertEquals("test last name", reporterData.getLastName());
        assertEquals(new Long(-1L), reporterData.getPrimaryPhoneNumber());
        assertEquals(new Integer(-2), reporterData.getPrimaryPhoneExtensionNumber());
        assertNull(reporterData.getCommunicationMethod());
        assertNull(reporterData.getConfidentialWaiverIndicator());
        assertNull(reporterData.getCountySpecificCode());
        assertNull(reporterData.getCollateralClientReporterRelationship());
        assertNull(reporterData.getFeedbackRequiredIndicator());
        assertNull(reporterData.getFeedbackDocument());
        assertNull(reporterData.getFeedbackDate());
        assertNull(reporterData.getFkLawEnforcement());
        assertNull(reporterData.getFkReferral());
        assertNull(reporterData.getMiddleInitialName());
        assertNull(reporterData.getMandatedReporterIndicator());
        assertNull(reporterData.getMessagePhoneExtensionNumber());
        assertNull(reporterData.getMessagePhoneNumber());
        assertNull(reporterData.getNamePrefixDescription());
        assertNull(reporterData.getBadgeNumber());
        assertNull(reporterData.getCityName());
        assertNull(reporterData.getEmployerName());
        assertNull(reporterData.getStreetName());
        assertNull(reporterData.getStreetNumber());
        assertNull(reporterData.getZipNumber());
        assertNull(reporterData.getState());
        assertNull(reporterData.getSuffixTitleDescription());
        assertNull(reporterData.getZipSuffixNumber());

        return true;
      }
    }))).thenReturn(cicsResponse);

    Reporter result = reporterService.createReporter(reporter);
    assertEquals(reporter, result);

    verify(businessRuleService).executeBusinessRules(any());
    verify(cicsReporterRestApiClient).createReporter(any());
  }

  private Reporter createReporter() {
    Reporter reporter = new Reporter();
    reporter.setFirstName("test first name");
    reporter.setLastName("test last name");
    reporter.setPhoneNumber(-1);
    reporter.setPhoneExtension(-2);
    reporter.setRelationToChild("test relation to child");
    return reporter;
  }
}