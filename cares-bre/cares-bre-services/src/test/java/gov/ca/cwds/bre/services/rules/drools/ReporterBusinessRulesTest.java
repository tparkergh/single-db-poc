package gov.ca.cwds.bre.services.rules.drools;

import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.services.rules.DroolsBusinessRuleBase;
import gov.ca.cwds.bre.services.rules.ReporterBusinessRules;
import gov.ca.cwds.cics.model.ReporterData;

public class ReporterBusinessRulesTest extends DroolsBusinessRulesTestBase {
  
  protected DroolsBusinessRuleBase createDroolsBusinessRules() {
    return new ReporterBusinessRules();
  }

  @Test
  public void whenValidReporter_thenNoIssues() {
    ReporterData reporterData = createValidReporterData();
    BreRequest breRequest = createBreRequest(ReporterBusinessRules.class.getSimpleName(), 
        reporterData);
    
    BreResponse breResponse = getDroolsBusinessRules().execute(breRequest);
    Assert.assertNotNull(breResponse);
    Assert.assertNotNull(ReporterBusinessRules.class.getSimpleName(), breResponse.getBusinessRuleSetName());
    Assert.assertNotNull(breResponse.getIssues());
    Assert.assertTrue(breResponse.getIssues().isEmpty());
  }
  
  @Test
  public void reporter_di_confidential_wavier_ind() {
    ReporterData reporterData = createValidReporterData();
    
    // Only Y or N allowed
    reporterData.setConfidentialWaiverIndicator("D");        
    checkBusinessRulesExecution(reporterData, 1);
    
    reporterData.setConfidentialWaiverIndicator("Y");
    checkBusinessRulesExecution(reporterData, 0);
    
    reporterData.setConfidentialWaiverIndicator("N");
    checkBusinessRulesExecution(reporterData, 0);    
  }
  
  @Test
  public void reporter_di_drms_mandated_reporter_feedback() {
    ReporterData reporterData = createValidReporterData();
        
    // Set to null
    reporterData.setFeedbackDocument(null);    
    checkBusinessRulesExecution(reporterData, 0);
    
    // Set to less than 10 characters
    reporterData.setFeedbackDocument("hgu");
    checkBusinessRulesExecution(reporterData, 1);
    
    // Set to more than 10 characters
    reporterData.setFeedbackDocument("hgujkhgjhgjjghjjh");
    checkBusinessRulesExecution(reporterData, 1);        
  }
  
  @Test
  public void reporter_di_employer_name() {
    ReporterData reporterData = createValidReporterData();
    
    // Set to N and no employer name
    reporterData.setMandatedReporterIndicator("N");  
    reporterData.setEmployerName(null);    
    checkBusinessRulesExecution(reporterData, 0);
    
    // Set to Y and no employer name
    reporterData.setMandatedReporterIndicator("Y");  
    reporterData.setEmployerName(null);
    reporterData.setFkLawEnforcement(null);
    reporterData.setBadgeNumber(null);
    checkBusinessRulesExecution(reporterData, 1);
  }
  
  @Test
  public void reporter_di_feedback_required_ind() {
    ReporterData reporterData = createValidReporterData();
    
    // Only Y or N allowed
    reporterData.setFeedbackRequiredIndicator("D");    
    checkBusinessRulesExecution(reporterData, 1);
    
    reporterData.setFeedbackRequiredIndicator("Y");
    checkBusinessRulesExecution(reporterData, 0);
    
    reporterData.setFeedbackRequiredIndicator("N");
    checkBusinessRulesExecution(reporterData, 0);
  }
  
  @Test
  public void reporter_di_fklaw_enft() {
    ReporterData reporterData = createValidReporterData();
    
    // Set to null
    reporterData.setFkLawEnforcement(null);   
    reporterData.setMandatedReporterIndicator("N");
    reporterData.setBadgeNumber(null);
    checkBusinessRulesExecution(reporterData, 0);

    // Set to less than 10 characters
    reporterData.setFkLawEnforcement("hgu");
    checkBusinessRulesExecution(reporterData, 1);
    
    // Set to more than 10 characters
    reporterData.setFkLawEnforcement("hgujkhgjhgjjghjjh");
    checkBusinessRulesExecution(reporterData, 1);    
  }
  
  @Test
  public void reporter_di_fkreferl_t() {
    ReporterData reporterData = createValidReporterData();
    
    // Set to null
    reporterData.setFkReferral(null); 
    checkBusinessRulesExecution(reporterData, 0);

    // Set to less than 10 characters
    reporterData.setFkReferral("hgu");
    checkBusinessRulesExecution(reporterData, 1);
    
    // Set to more than 10 characters
    reporterData.setFkReferral("hgujkhgjhgjjghjjh");
    checkBusinessRulesExecution(reporterData, 1); 
  }

  @Test
  public void reporter_di_mandated_reporter_ind() {
    ReporterData reporterData = createValidReporterData();
    
    // Only Y or N allowed
    reporterData.setMandatedReporterIndicator("D");
    checkBusinessRulesExecution(reporterData, 1);
    
    reporterData.setMandatedReporterIndicator("Y");
    checkBusinessRulesExecution(reporterData, 0);
    
    reporterData.setMandatedReporterIndicator("N");
    checkBusinessRulesExecution(reporterData, 0);
  }
  
  @Test
  public void reporter_di_reporter_id() {
    ReporterData reporterData = createValidReporterData();
    
    // Set to null
    reporterData.setIdentifier(null); 
    checkBusinessRulesExecution(reporterData, 1);

    // Set to less than 10 characters
    reporterData.setIdentifier("hgu");
    checkBusinessRulesExecution(reporterData, 1);
    
    // Set to more than 10 characters
    reporterData.setIdentifier("hgujkhgjhgjjghjjh");
    checkBusinessRulesExecution(reporterData, 1); 
  }
  
  @Test
  public void reporter_R_00725() {
    ReporterData reporterData = createValidReporterData();
    
    reporterData.setStreetName(null);
    checkBusinessRulesExecution(reporterData, 1);             
  }
  
  @Test
  public void reporter_R_00846() {
    ReporterData reporterData = createValidReporterData();
    
    reporterData.setEmployerName("lkjkl");
    reporterData.setFkLawEnforcement(null);
    checkBusinessRulesExecution(reporterData, 1);             
  }
  
  @Test
  public void reporter_R_00849() {
    ReporterData reporterData = createValidReporterData();
    
    reporterData.setEmployerName("lkjkl");    
    checkBusinessRulesExecution(reporterData, 1);             
  }
  
  private ReporterData createValidReporterData() {
    ReporterData reporterData = new ReporterData();

    reporterData.setIdentifier("yr7ytiu7yg");
    reporterData.setTxnHeaderStaffId("test TxnHeaderStaffId");
    reporterData.setFirstName("test FirstName");
    reporterData.setLastName("test LastName");
    reporterData.setPrimaryPhoneNumber(-1L);
    reporterData.setPrimaryPhoneExtensionNumber(-2);
    reporterData.setCommunicationMethod(-3);
    reporterData.setConfidentialWaiverIndicator("Y");
    reporterData.setCountySpecificCode("test CountySpecificCode");
    reporterData.setCollateralClientReporterRelationship(-4);
    reporterData.setFeedbackRequiredIndicator("Y");
    reporterData.setFeedbackDocument("FdbkDocume");
    reporterData.setFeedbackDate(LocalDate.now());
    reporterData.setFkLawEnforcement("FkLawEnfor");
    reporterData.setFkReferral("FkReferral");
    reporterData.setMiddleInitialName("test MiddleInitialName");
    reporterData.setMandatedReporterIndicator("Y");
    reporterData.setMessagePhoneExtensionNumber(-5);
    reporterData.setMessagePhoneNumber(-6L);
    reporterData.setNamePrefixDescription("test NamePrefixDescription");
    reporterData.setBadgeNumber("test BadgeNumber");
    reporterData.setCityName("test CityName");
    reporterData.setEmployerName("");
    reporterData.setStreetName("test StreetName");
    reporterData.setStreetNumber("test StreetNumber");
    reporterData.setZipNumber(-7);
    reporterData.setState(-8);
    reporterData.setSuffixTitleDescription("test SuffixTitleDescription");
    reporterData.setZipSuffixNumber(-9);

    return reporterData;
  }
  
  private void checkBusinessRulesExecution(ReporterData reporterData, int issuesExpected) {
    BreRequest breRequest = createBreRequest(ReporterBusinessRules.class.getSimpleName(), reporterData);    
    BreResponse breResponse = getDroolsBusinessRules().execute(breRequest);
    Assert.assertEquals(issuesExpected, breResponse.getIssues().size());
  }
}
