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
  public void reporter_di_badge_number() {
    ReporterData reporterData = createValidReporterData();

    reporterData.setBadgeNumber("1234567");
    checkBusinessRulesExecution(reporterData, 1);

    reporterData.setBadgeNumber("123456");
    checkBusinessRulesExecution(reporterData, 0);

  }

  @Test
  public void reporter_di_city_name() {
    ReporterData reporterData = createValidReporterData();

    reporterData.setCityName("123456789012345678901");
    checkBusinessRulesExecution(reporterData, 1);

    reporterData.setCityName("12345678901234567890");
    checkBusinessRulesExecution(reporterData, 0);

  }


  @Test
  public void reporter_di_confidential_wavier_ind() {
    ReporterData reporterData = createValidReporterData();
    
    // Only Y or N allowed
    reporterData.setConfidentialWaiverIndicator("D");        
    checkBusinessRulesExecution(reporterData, 1);
    
    reporterData.setConfidentialWaiverIndicator("Yes");
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
    
    reporterData.setMandatedReporterIndicator("Y");
    reporterData.setFkLawEnforcement("1234567ABC");
    reporterData.setEmployerName("12345678901234567890123456789012345");
    checkBusinessRulesExecution(reporterData, 1);

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
  public void reporter_di_first_name() {
    ReporterData reporterData = createValidReporterData();

    reporterData.setFirstName("123456789012345678901");
    checkBusinessRulesExecution(reporterData, 1);
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
  public void reporter_di_last_name() {
    ReporterData reporterData = createValidReporterData();

    reporterData.setLastName("12345678901234567890123456");
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
  public void reporter_di_primary_phone_number() {
    ReporterData reporterData = createValidReporterData();

    reporterData.setPrimaryPhoneNumber(12345678L);
    checkBusinessRulesExecution(reporterData, 1);

    reporterData.setPrimaryPhoneNumber(12345678901L);
    checkBusinessRulesExecution(reporterData, 1);

    reporterData.setPrimaryPhoneNumber(1234567890L);
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
  public void reporter_di_required_fields() {
    ReporterData reporterData = createValidReporterData();

    reporterData.setFirstName("");
    checkBusinessRulesExecution(reporterData, 1);

    reporterData.setFirstName("firstname");
    reporterData.setLastName("");
    checkBusinessRulesExecution(reporterData, 1);

    reporterData.setLastName("lastname");
    reporterData.setPrimaryPhoneNumber(0L);
    checkBusinessRulesExecution(reporterData, 1);

    reporterData.setPrimaryPhoneNumber(1234567890L);
    checkBusinessRulesExecution(reporterData, 0);
  }

  @Test
  public void reporter_di_street_name() {
    ReporterData reporterData = createValidReporterData();

    reporterData.setStreetName("12345678901234567890123456789012345678901");
    checkBusinessRulesExecution(reporterData, 1);

  }

  @Test
  public void reporter_di_street_number() {
    ReporterData reporterData = createValidReporterData();

    reporterData.setStreetNumber("12345678901");
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
    reporterData.setTxnHeaderStaffId("0X5");
    reporterData.setFirstName("test FirstName");
    reporterData.setLastName("test LastName");
    reporterData.setPrimaryPhoneNumber(1234567890L);
    reporterData.setPrimaryPhoneExtensionNumber(123);
    reporterData.setCommunicationMethod(0);
    reporterData.setConfidentialWaiverIndicator("Y");
    reporterData.setCountySpecificCode("99");
    reporterData.setCollateralClientReporterRelationship(0);
    reporterData.setFeedbackRequiredIndicator("N");
    reporterData.setFeedbackDocument("FdbkDocume");
    reporterData.setFeedbackDate(LocalDate.now());
    reporterData.setFkLawEnforcement("FkLawEnfor");
    reporterData.setFkReferral("FkReferral");
    reporterData.setMiddleInitialName("");
    reporterData.setMandatedReporterIndicator("Y");
    reporterData.setMessagePhoneExtensionNumber(234);
    reporterData.setMessagePhoneNumber(2345678901L);
    reporterData.setNamePrefixDescription("test NamePrefixDescription");
    reporterData.setBadgeNumber("123456");
    reporterData.setCityName("test CityName");
    reporterData.setEmployerName("");
    reporterData.setStreetName("test StreetName");
    reporterData.setStreetNumber("1234567890");
    reporterData.setZipNumber(0);
    reporterData.setState(0);
    reporterData.setSuffixTitleDescription("test SuffixTitleDescription");
    reporterData.setZipSuffixNumber(0);

    return reporterData;
  }
  
  private void checkBusinessRulesExecution(ReporterData reporterData, int issuesExpected) {
    BreRequest breRequest = createBreRequest(ReporterBusinessRules.class.getSimpleName(), reporterData);    
    BreResponse breResponse = getDroolsBusinessRules().execute(breRequest);
    Assert.assertEquals(issuesExpected, breResponse.getIssues().size());
  }
}
