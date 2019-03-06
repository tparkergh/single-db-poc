package gov.ca.cwds.bre.rest;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreRequestData;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cics.model.ReporterData;
import io.restassured.http.ContentType;

/**
 * CWDS J Team
 */
@RunWith(SpringRunner.class)
public class ReporterBusinessRulesTest extends RestFunctionalTestBase {

  private static final String SERVICE_PATH = "/bre/exec";

  @Test
  public void whenCallReporterBusinessRulesWithValidData_thenSuccessResponse() throws Exception {
    ReporterData reporterData = createValidReporterData();
    checkBusinessRulesExecution(reporterData, 0);
  }

  @Test
  public void whenCallReporterBusinessRulesWithInValidData_thenSuccessResponse() throws Exception {
    ReporterData reporterData = createValidReporterData();
    reporterData.setMandatedReporterIndicator("D");
    reporterData.setConfidentialWaiverIndicator("D");
    reporterData.setFeedbackRequiredIndicator("D");
    checkBusinessRulesExecution(reporterData, 3);
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

  private void checkBusinessRulesExecution(ReporterData reporterData, int issuesExpected)
      throws Exception {
    BreRequest breRequest = createBreRequest("ReporterBusinessRules", reporterData);


    String responseJson = given().contentType(ContentType.JSON).body(breRequest).when()
        .post(getRestServiceUrl() + SERVICE_PATH).asString();

    assertNotNull(responseJson);

    ObjectMapper mapper = new ObjectMapper();
    BreResponse breResponse = mapper.readValue(responseJson, BreResponse.class);

    assertNotNull(breResponse);
    assertEquals("ReporterBusinessRules", breResponse.getBusinessRuleSetName());
    assertNotNull(breResponse.getIssues());
    Assert.assertEquals(issuesExpected, breResponse.getIssues().size());
  }

  protected BreRequest createBreRequest(String businessRuleSetName, Object dataObject) {
    BreRequest breRequest = new BreRequest();
    breRequest.setBusinessRuleSetName(businessRuleSetName);

    BreRequestData breRequestData = new BreRequestData();
    breRequestData.setDataObjectClassName(dataObject.getClass().getName());

    ObjectMapper mapper = new ObjectMapper();
    breRequestData.setDataObject(mapper.convertValue(dataObject, JsonNode.class));

    breRequest.addDataObject(breRequestData);

    return breRequest;
  }
}
