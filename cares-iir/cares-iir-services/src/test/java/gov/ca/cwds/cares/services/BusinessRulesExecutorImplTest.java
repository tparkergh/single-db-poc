package gov.ca.cwds.cares.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.api.BusinessRuleService;
import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreRequestData;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cics.model.ReporterData;
import gov.ca.cwds.rest.exception.IssueDetails;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * CWDS J Team
 */
@RunWith(MockitoJUnitRunner.class)
public class BusinessRulesExecutorImplTest {
  @Mock
  private BusinessRuleService businessRuleService;

  @InjectMocks
  private BusinessRulesExecutorImpl businessRulesExecutor;

  private ObjectMapper objectMapper = new ObjectMapper();

  private LocalDate someDate = LocalDate.now();

  @Before
  public void setUp() {
    businessRulesExecutor.setJacksonObjectMapper(objectMapper);
  }

  @Test
  public void shouldSuccess() {
    BreResponse breResponse = new BreResponse();

    when(businessRuleService.executeBusinessRules(argThat(new ArgumentMatcher<BreRequest>() {
      @Override
      public boolean matches(BreRequest breRequest) {
        assertEquals("test BusinessRuleName", breRequest.getBusinessRuleSetName());
        BreRequestData breRequestData = breRequest.getDataObjects().get(0);
        ReporterData breReporterData = null;
        try {
          breReporterData = (ReporterData) objectMapper.readValue(
              objectMapper.writeValueAsString(breRequestData.getDataObject()),
              Class.forName(breRequestData.getDataObjectClassName())
          );
        } catch (Exception e) {
          fail("BreRequest matcher exception\n" + e.getMessage());
        }
        assertEquals("test Identifier", breReporterData.getIdentifier());
        assertEquals("test TxnHeaderStaffId", breReporterData.getTxnHeaderStaffId());
        assertEquals("test FirstName", breReporterData.getFirstName());
        assertEquals("test LastName", breReporterData.getLastName());
        assertEquals(new Long(-1L), breReporterData.getPrimaryPhoneNumber());
        assertEquals(new Integer(-2), breReporterData.getPrimaryPhoneExtensionNumber());
        assertEquals(new Integer(-3), breReporterData.getCommunicationMethod());
        assertEquals("test ConfidentialWaiverIndicator", breReporterData.getConfidentialWaiverIndicator());
        assertEquals("test CountySpecificCode", breReporterData.getCountySpecificCode());
        assertEquals(new Integer(-4), breReporterData.getCollateralClientReporterRelationship());
        assertEquals("test FeedbackRequiredIndicator", breReporterData.getFeedbackRequiredIndicator());
        assertEquals("test FeedbackDocument", breReporterData.getFeedbackDocument());
        assertEquals(someDate, breReporterData.getFeedbackDate());
        assertEquals("test FkLawEnforcement", breReporterData.getFkLawEnforcement());
        assertEquals("test FkReferral", breReporterData.getFkReferral());
        assertEquals("test MiddleInitialName", breReporterData.getMiddleInitialName());
        assertEquals("test MandatedReporterIndicator", breReporterData.getMandatedReporterIndicator());
        assertEquals(new Integer(-5), breReporterData.getMessagePhoneExtensionNumber());
        assertEquals(new Long(-6), breReporterData.getMessagePhoneNumber());
        assertEquals("test NamePrefixDescription", breReporterData.getNamePrefixDescription());
        assertEquals("test BadgeNumber", breReporterData.getBadgeNumber());
        assertEquals("test CityName", breReporterData.getCityName());
        assertEquals("test EmployerName", breReporterData.getEmployerName());
        assertEquals("test StreetName", breReporterData.getStreetName());
        assertEquals("test StreetNumber", breReporterData.getStreetNumber());
        assertEquals(new Integer(-7), breReporterData.getZipNumber());
        assertEquals(new Integer(-8), breReporterData.getState());
        assertEquals("test SuffixTitleDescription", breReporterData.getSuffixTitleDescription());
        assertEquals(new Integer(-9), breReporterData.getZipSuffixNumber());

        return true;
      }
    }))).thenReturn(breResponse);

    ReporterData reporterData = createReporterData();
    assertSame(breResponse, businessRulesExecutor.executeBusinessRules("test BusinessRuleName", reporterData));
    verify(businessRuleService).executeBusinessRules(any());
  }

  @Test(expected = BreException.class)
  public void shouldThrowBreExceptionWhenHasValidationIssuesInBreResponse() {
    BreResponse breResponse = new BreResponse();
    Set<IssueDetails> issues = new HashSet<>();
    issues.add(new IssueDetails());
    breResponse.setIssues(issues);

    when(businessRuleService.executeBusinessRules(any())).thenReturn(breResponse);

    ReporterData reporterData = createReporterData();
    businessRulesExecutor.executeBusinessRules("test BusinessRuleName", reporterData);
    verify(businessRuleService).executeBusinessRules(any());
  }

  @Test(expected = BreException.class)
  public void shouldThrowBreExceptionWhenHasExceptionFromBreServiceCall() {
    when(businessRuleService.executeBusinessRules(any())).thenThrow(new RuntimeException());

    ReporterData reporterData = createReporterData();
    businessRulesExecutor.executeBusinessRules("test BusinessRuleName", reporterData);
    verify(businessRuleService).executeBusinessRules(any());
  }


  private ReporterData createReporterData() {
    ReporterData reporterData = new ReporterData();

    reporterData.setIdentifier("test Identifier");
    reporterData.setTxnHeaderStaffId("test TxnHeaderStaffId");
    reporterData.setFirstName("test FirstName");
    reporterData.setLastName("test LastName");
    reporterData.setPrimaryPhoneNumber(-1L);
    reporterData.setPrimaryPhoneExtensionNumber(-2);
    reporterData.setCommunicationMethod(-3);
    reporterData.setConfidentialWaiverIndicator("test ConfidentialWaiverIndicator");
    reporterData.setCountySpecificCode("test CountySpecificCode");
    reporterData.setCollateralClientReporterRelationship(-4);
    reporterData.setFeedbackRequiredIndicator("test FeedbackRequiredIndicator");
    reporterData.setFeedbackDocument("test FeedbackDocument");
    reporterData.setFeedbackDate(someDate);
    reporterData.setFkLawEnforcement("test FkLawEnforcement");
    reporterData.setFkReferral("test FkReferral");
    reporterData.setMiddleInitialName("test MiddleInitialName");
    reporterData.setMandatedReporterIndicator("test MandatedReporterIndicator");
    reporterData.setMessagePhoneExtensionNumber(-5);
    reporterData.setMessagePhoneNumber(-6L);
    reporterData.setNamePrefixDescription("test NamePrefixDescription");
    reporterData.setBadgeNumber("test BadgeNumber");
    reporterData.setCityName("test CityName");
    reporterData.setEmployerName("test EmployerName");
    reporterData.setStreetName("test StreetName");
    reporterData.setStreetNumber("test StreetNumber");
    reporterData.setZipNumber(-7);
    reporterData.setState(-8);
    reporterData.setSuffixTitleDescription("test SuffixTitleDescription");
    reporterData.setZipSuffixNumber(-9);

    return reporterData;
  }
}