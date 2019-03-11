package gov.ca.cwds.cares.services;

import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.ReporterData;
import java.util.ArrayList;
import java.util.List;
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
  private BusinessRulesExecutor<BreResponse, ReporterData> businessRuleExecutor;

  @Mock
  private CicsServiceCallExecutor<CicsReporterRequest> cicsServiceCallExecutor;

  @InjectMocks
  private ReporterServiceImpl reporterService;

  @Test
  public void shouldSuccess() throws Exception {
    Reporter request = createReporter();
    List<String> generatedIdBag = new ArrayList<>();

    when(businessRuleExecutor.executeBusinessRules(
        argThat(new ArgumentMatcher<String>() {
          @Override
          public boolean matches(String ruleName) {
            assertEquals("ReporterBusinessRules", ruleName);
            return true;
          }
        }),
        argThat(new ArgumentMatcher<ReporterData>() {
          @Override
          public boolean matches(ReporterData reporterData) {
            assertReporterData(reporterData);
            generatedIdBag.add(reporterData.getIdentifier());
            return true;
          }
        })
    )).thenReturn(new BreResponse());

    when(cicsServiceCallExecutor.executeServiceCall(argThat(new ArgumentMatcher<CicsReporterRequest>() {
        @Override
        public boolean matches(CicsReporterRequest cicsReporterRequest) {
          ReporterData reporterData = cicsReporterRequest.getReporterData();
          assertReporterData(reporterData);
          return true;
        }
      }))).thenReturn(new CicsResponse());

    Reporter response = reporterService.createReporter(request);

    assertEquals(generatedIdBag.get(0), response.getIdentifier());
    assertEquals(request.getFirstName(), response.getFirstName());
    assertEquals(request.getLastName(), response.getLastName());
    assertEquals(request.getLastName(), response.getLastName());
    assertEquals(request.getPhoneNumber(), response.getPhoneNumber());
    assertEquals(request.getPhoneExtension(), response.getPhoneExtension());
    assertEquals(request.getRelationToChild(), response.getRelationToChild());
    verify(businessRuleExecutor).executeBusinessRules(any(), any());
    verify(cicsServiceCallExecutor).executeServiceCall(any());
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
    assertEquals(new Integer(0), reporterData.getState());
    assertNull(reporterData.getFeedbackDocument());
    assertNull(reporterData.getFeedbackDate());
    assertNull(reporterData.getFkLawEnforcement());
    assertNull(reporterData.getFkReferral());
    assertNull(reporterData.getMiddleInitialName());
    assertNull(reporterData.getMessagePhoneExtensionNumber());
    assertNull(reporterData.getMessagePhoneNumber());
    assertNull(reporterData.getNamePrefixDescription());
    assertNull(reporterData.getBadgeNumber());
    assertNull(reporterData.getCityName());
    assertNull(reporterData.getEmployerName());
    assertNull(reporterData.getStreetName());
    assertNull(reporterData.getStreetNumber());
    assertNull(reporterData.getZipNumber());
    assertNull(reporterData.getSuffixTitleDescription());
    assertNull(reporterData.getZipSuffixNumber());
  }

  private Reporter createReporter() {
    Reporter reporter = new Reporter();
    reporter.setFirstName("test first name");
    reporter.setLastName("test last name");
    reporter.setPhoneNumber(-1L);
    reporter.setPhoneExtension(-2);
    reporter.setRelationToChild("test relation to child");
    return reporter;
  }
}