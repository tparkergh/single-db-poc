package gov.ca.cwds.cares.rest.reporter;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.SearchResults;
import gov.ca.cwds.cares.interfaces.model.search.query.PersonSearchQuery;
import gov.ca.cwds.cares.rest.RestFunctionalTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.UUID;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class ReporterTest extends RestFunctionalTestBase {
  private static final String SEARCH_PATH = "/reporters";
  private String firstName = "";
  private String lastName = "";
  private String phoneNumber = "";

  @Before
  public void setup(){
    firstName = generateUniqueName();
    lastName = generateUniqueName();
    phoneNumber = "5551231234";
  }

  @Test
  public void shouldCreateNewReporterWhenOnlyFirstNameAndLastNameIsGiven() throws Exception {
    assertFalse(reporterExists(firstName, lastName));

    JSONObject bodyContent = createDefaultBody(firstName, lastName, phoneNumber);

    Response response = given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
      .when()
      .post(getRestServiceUrl() + SEARCH_PATH);
    String responseJson  = response.asString();

    assertEquals(201, response.getStatusCode());
    assertTrue(reporterExists(firstName, lastName));
  }

  @Test
  public void shouldNotCreateNewReporterWhenOnlyFirstNameIsGiven() throws Exception {
    JSONObject bodyContent = createDefaultBody(firstName, "", phoneNumber);

    Response response = given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
      .when()
      .post(getRestServiceUrl() + SEARCH_PATH);
    String responseJson  = response.asString();

    assertEquals(400, response.getStatusCode());
    assertFalse(reporterExists(firstName, ""));
  }

  @Test
  public void shouldNotCreateNewReporterWhenOnlyLastNameIsGiven() throws Exception {
    JSONObject bodyContent = createDefaultBody("", lastName, phoneNumber);

    Response response = given()
        .contentType(ContentType.JSON)
        .body(bodyContent.toJSONString())
        .when()
        .post(getRestServiceUrl() + SEARCH_PATH);
    String responseJson  = response.asString();

    assertEquals(400, response.getStatusCode());
    assertFalse(reporterExists("", lastName));
  }

  @Test
  public void shouldNotCreateNewReporterWhenMissingPhoneNumber() throws Exception {
    String missingPhoneNumber = "";
    JSONObject bodyContent = createDefaultBody(firstName, lastName, missingPhoneNumber);

    Response response = given()
        .contentType(ContentType.JSON)
        .body(bodyContent.toJSONString())
        .when()
        .post(getRestServiceUrl() + SEARCH_PATH);
    String responseJson  = response.asString();

    assertEquals(400, response.getStatusCode());
    assertFalse(reporterExists(firstName, lastName));
  }

  @Test
  public void shouldReportErrorWhenPhoneNumberIsIncorrectLength() throws Exception {
    String shortPhoneNumber = "555";
    JSONObject bodyContent = createDefaultBody(firstName, lastName, shortPhoneNumber);

    Response response = given()
        .contentType(ContentType.JSON)
        .body(bodyContent.toJSONString())
        .when()
        .post(getRestServiceUrl() + SEARCH_PATH);
    String responseJson  = response.asString();

    assertEquals(400, response.getStatusCode());
    assertFalse(reporterExists(firstName, lastName));
  }

  @Test
  public void shouldReportErrorWhenNameIsTooLong() throws Exception {
    String longName = "ChristopherMaximiliano";
    JSONObject bodyContent = createDefaultBody(longName, lastName, phoneNumber);

    Response response = given()
        .contentType(ContentType.JSON)
        .body(bodyContent.toJSONString())
        .when()
        .post(getRestServiceUrl() + SEARCH_PATH);
    String responseJson  = response.asString();

    assertEquals(400, response.getStatusCode());
    assertFalse(reporterExists(longName, lastName));
  }

  private JSONObject createDefaultBody(String firstName, String lastName, String phoneNumber) {
    JSONObject bodyContent = new JSONObject();
    bodyContent.put("identifier", "");
    bodyContent.put("first_name", firstName);
    bodyContent.put("last_name", lastName);
    bodyContent.put("phone_number", phoneNumber);
    bodyContent.put("phone_extension", "");
    bodyContent.put("relation_to_child", "");
    return bodyContent;
  }


  private String generateUniqueName(){
    return UUID.randomUUID().toString().substring(0,8);
  }

  private boolean reporterExists(String firstName, String lastName) throws Exception {
    SearchCriteria searchCriteria = new SearchCriteria();
    searchCriteria.setLimit(10);
    searchCriteria.addSource("reporter");
    searchCriteria.addSource("victim");
    searchCriteria.addSource("perpetrator");
    PersonSearchQuery searchQuery = new PersonSearchQuery();
    searchQuery.setFirstName(firstName);
    searchQuery.setLastName(lastName);

    searchCriteria.setQuery(searchQuery);

     Response response = given()
        .contentType(ContentType.JSON)
        .body(searchCriteria)
        .when()
        .post(getRestServiceUrl() + "/searches");
     if (response.getStatusCode() == 500){
       throw new Exception("Searching Server blew up\n" + response.asString());
     }
    String responseJson = response.asString();

    ObjectMapper mapper = new ObjectMapper();
    SearchResults searchResults = mapper.readValue(responseJson, SearchResults.class);
    boolean foundResults = false;
    try{
      foundResults = searchResults.getTotalHitCount() > 0;
    } catch(Exception e){
      //probably got a 404. It's ok
      foundResults = false;
    }
    return foundResults;
  }
}