package gov.ca.cwds.cares.rest.reporter;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.SearchResults;
import gov.ca.cwds.cares.interfaces.model.search.query.PersonSearchQuery;
import gov.ca.cwds.cares.rest.RestFunctionalTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class ReporterTest extends RestFunctionalTestBase {
  private static final String PATH = "/reporters";
  private String firstName = "";
  private String lastName = "";
  private long phoneNumber;
  private String streetName = "";
  private String streetNumber = "";
  private String city = "";
  private int zip;

  @Before
  public void setup(){
    firstName = generateUniqueName();
    lastName = generateUniqueName();
    phoneNumber = 5551231234L;

    streetName = "Main St";
    streetNumber = "123";
    city = "Sac";
    zip = 12345;
  }

  @Test
  public void shouldCreateNewReporterWhenOnlyFirstNameAndLastNameIsGiven() throws Exception {
    assertFalse(reporterExists(firstName, lastName));

    JSONObject bodyContent = createDefaultBody(firstName, lastName, phoneNumber);

    given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
    .when()
      .post(getRestServiceUrl() + PATH)
    .then()
      .statusCode(201);

    assertTrue(reporterExists(firstName, lastName));
  }

  @Test
  public void shouldNotComplainWhenExtraUnknownFieldsArePresent() throws Exception {
    assertFalse(reporterExists(firstName, lastName));

    JSONObject bodyContent = createDefaultBody(firstName, lastName, phoneNumber);
    bodyContent.put("foo", "foo");
    bodyContent.put("bar", "bar");

    given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
    .when()
      .post(getRestServiceUrl() + PATH)
    .then()
      .statusCode(201);
  }

  @Test
  public void shouldNotCreateNewReporterWhenOnlyFirstNameIsGiven() throws Exception {
    JSONObject bodyContent = createDefaultBody(firstName, "", phoneNumber);

    given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
    .when()
      .post(getRestServiceUrl() + PATH)
    .then()
      .statusCode(400);

    assertFalse(reporterExists(firstName, ""));
  }

  @Test
  public void shouldCreateNewReporterWithAddressWhenAddressIsGiven() throws Exception {
    assertFalse(reporterExists(firstName, lastName));

    JSONObject bodyContent = createDefaultBody(firstName, lastName, phoneNumber);
    addDefaultAddress(bodyContent);

    given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
    .when()
      .post(getRestServiceUrl() + PATH)
    .then()
      .statusCode(201)
    .body("first_name", equalToIgnoringWhiteSpace(firstName))
      .body("last_name", equalToIgnoringWhiteSpace(lastName))
      .body("address.street_name", equalToIgnoringWhiteSpace(streetName))
      .body("address.street_number", equalToIgnoringWhiteSpace(streetNumber))
      .body("address.city", equalToIgnoringWhiteSpace(city))
      .body("address.zip_code", is(zip));

    assertTrue(reporterExists(firstName, lastName));
  }

  @Test
  public void theGetRequestShouldReturnTheSavedReporter() throws Exception {
    assertFalse(reporterExists(firstName, lastName));

    JSONObject bodyContent = createDefaultBody(firstName, lastName, phoneNumber);
    addDefaultAddress(bodyContent);

    //save reporter
    Response response = given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
    .when()
      .post(getRestServiceUrl() + PATH);
    String id = extractIdFromResponse(response);

    //validate reporter was saved
    given()
    .when()
      .get(getRestServiceUrl() + PATH + "/" + id)
    .then()
      .statusCode(200)
      .body("identifier", equalToIgnoringWhiteSpace(id))
      .body("first_name", equalToIgnoringWhiteSpace(firstName))
      .body("last_name", equalToIgnoringWhiteSpace(lastName))
      .body("primary_phone_number", is(phoneNumber))
      .body("address.street_number", equalToIgnoringWhiteSpace(streetNumber))
      .body("address.city", equalToIgnoringWhiteSpace(city))
      .body("address.zip_code", is(zip));
  }

  @Test
  public void shouldNotCreateNewReporterWhenOnlyLastNameIsGiven() throws Exception {
    JSONObject bodyContent = createDefaultBody("", lastName, phoneNumber);

    given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
    .when()
      .post(getRestServiceUrl() + PATH)
    .then()
      .statusCode(400);

    assertFalse(reporterExists("", lastName));
  }

  @Test
  public void shouldNotCreateNewReporterWhenPhoneNumberIsEmptyOrZero() throws Exception {
    long missingPhoneNumber = 0;
    JSONObject bodyContent = createDefaultBody(firstName, lastName, missingPhoneNumber);

    given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
    .when()
      .post(getRestServiceUrl() + PATH)
    .then()
      .statusCode(400);

    assertFalse(reporterExists(firstName, lastName));
  }

  @Test
  public void shouldReportErrorWhenPhoneNumberIsIncorrectLength() throws Exception {
    long shortPhoneNumber = 555L;
    JSONObject bodyContent = createDefaultBody(firstName, lastName, shortPhoneNumber);

    given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
    .when()
      .post(getRestServiceUrl() + PATH)
    .then()
      .statusCode(400);

    assertFalse(reporterExists(firstName, lastName));
  }

  @Test
  public void shouldReportErrorWhenNameIsTooLong() throws Exception {
    String longName = "ChristopherMaximiliano";
    JSONObject bodyContent = createDefaultBody(longName, lastName, phoneNumber);

    given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
    .when()
      .post(getRestServiceUrl() + PATH)
    .then()
      .statusCode(400);

    assertFalse(reporterExists(longName, lastName));
  }

  @Test
  public void shouldUpdateReporter() throws Exception {
    String oldName =  "Fred" + generateUniqueName();
    String newName = "Bob" + generateUniqueName();
    JSONObject bodyContent = createDefaultBody(oldName, lastName, phoneNumber);

    //save reporter
    Response response = given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
    .when()
      .post(getRestServiceUrl() + PATH);

    String id = extractIdFromResponse(response);
    String timestamp = extractTimestampFromResponse(response);

    bodyContent.put("identifier", id);
    bodyContent.put("first_name", newName);
    bodyContent.put("last_update_timestamp", timestamp);

    //verify reporter was updated
    given()
      .contentType(ContentType.JSON)
      .body(bodyContent.toJSONString())
    .when()
      .put(getRestServiceUrl() + PATH)
    .then()
      .body("first_name", equalToIgnoringWhiteSpace(newName));

    assertTrue(reporterExists(newName, lastName));
  }

  private void addDefaultAddress(JSONObject bodyContent) {
    JSONObject address = new JSONObject();
    address.put("street_name", streetName);
    address.put("street_number", streetNumber);
    address.put("city", city);
    address.put("zip_code", zip);
    bodyContent.put("address", address);
  }

  private JSONObject createDefaultBody(String firstName, String lastName, long phoneNumber) {
    JSONObject bodyContent = new JSONObject();
    bodyContent.put("identifier", "");
    bodyContent.put("first_name", firstName);
    bodyContent.put("last_name", lastName);
    bodyContent.put("primary_phone_number", phoneNumber);
    bodyContent.put("phone_extension", "");
    bodyContent.put("relation_to_child", "");

    return bodyContent;
  }


  private String extractIdFromResponse(Response response) throws IOException {
    String responseJson  = response.asString();
    ObjectMapper mapper = new ObjectMapper();
    HashMap <String, String> reporter = mapper.readValue(responseJson, HashMap.class);
    String id = reporter.get("identifier");
    return id;
  }

  private String extractTimestampFromResponse(Response response) throws IOException {
    String responseJson  = response.asString();
    ObjectMapper mapper = new ObjectMapper();
    HashMap <String, String> reporter = mapper.readValue(responseJson, HashMap.class);
    String timestamp = reporter.get("last_update_timestamp");
    return timestamp;
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