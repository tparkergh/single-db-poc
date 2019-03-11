package gov.ca.cwds.cares.rest.search;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.SearchResults;
import gov.ca.cwds.cares.interfaces.model.search.query.PersonSearchQuery;
import gov.ca.cwds.cares.rest.RestFunctionalTestBase;
import io.restassured.http.ContentType;

/**
 * CWDS J Team
 */
@RunWith(SpringRunner.class)
public class ReporterSearchTest extends RestFunctionalTestBase {
  
  private static final String SEARCH_PATH = "/searches"; 

  @Value("${reporter-search.first-name}")
  private String reporterFirstName;

  @Value("${reporter-search.last-name}")
  private String reporterLastName;
  
  @Value("${reporter-search.phone}")
  private Long reporterPhone;

  @Test
  public void whenCallReporterSearchWithNames_thenSuccessResponse() throws Exception {
    SearchCriteria searchCriteria = new SearchCriteria();
    searchCriteria.setLimit(10);
    searchCriteria.addSource("reporter");
    searchCriteria.addSource("victim");
    searchCriteria.addSource("perpetrator");

    PersonSearchQuery searchQuery = new PersonSearchQuery();
    searchQuery.setFirstName(reporterFirstName);
    searchQuery.setLastName(reporterLastName);

    searchCriteria.setQuery(searchQuery);

    String responseJson = given()
        .contentType(ContentType.JSON)
        .body(searchCriteria)
        .when()
        .post(getRestServiceUrl() + SEARCH_PATH)
        .asString();

    assertNotNull(responseJson);

    ObjectMapper mapper = new ObjectMapper();
    SearchResults searchResults = mapper.readValue(responseJson, SearchResults.class);
    assertTrue(searchResults.getTotalHitCount() > 0);
  }
  
  @Test
  public void whenCallReporterSearchWithAllParams_thenSuccessResponse() throws Exception {
    SearchCriteria searchCriteria = new SearchCriteria();
    searchCriteria.setLimit(10);
    searchCriteria.addSource("reporter");
    searchCriteria.addSource("victim");
    searchCriteria.addSource("perpetrator");

    PersonSearchQuery searchQuery = new PersonSearchQuery();
    searchQuery.setFirstName(reporterFirstName);
    searchQuery.setLastName(reporterLastName);
    searchQuery.setPrimaryPhoneNumber(reporterPhone);

    searchCriteria.setQuery(searchQuery);

    String responseJson = given().contentType(ContentType.JSON).body(searchCriteria).when()
        .post(getRestServiceUrl() + SEARCH_PATH).asString();

    assertNotNull(responseJson);

    ObjectMapper mapper = new ObjectMapper();
    SearchResults searchResults = mapper.readValue(responseJson, SearchResults.class);
    assertTrue(searchResults.getTotalHitCount() > 0);
  }
  
  @Test
  public void whenCallReporterSearchWithoutParams_thenErrorResponse500() throws Exception {
    SearchCriteria searchCriteria = new SearchCriteria();
    searchCriteria.setLimit(10);
    searchCriteria.addSource("reporter");
    searchCriteria.addSource("victim");
    searchCriteria.addSource("perpetrator");

    PersonSearchQuery searchQuery = new PersonSearchQuery();
    searchCriteria.setQuery(searchQuery);

    given()
    .contentType(ContentType.JSON)
    .body(searchCriteria)
    .when()
    .post(getRestServiceUrl() + SEARCH_PATH)
    .then()
    .assertThat()
    .statusCode(500);
  }
}