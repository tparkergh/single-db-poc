package gov.ca.cwds.cares.rest.search;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.query.PersonSearchQuery;
import gov.ca.cwds.cares.rest.RestFunctionalTestBase;
import io.restassured.http.ContentType;

/**
 * CWDS J Team
 */
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

    verifySuccessCall(searchCriteria);
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

    verifySuccessCall(searchCriteria);
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

  private void verifySuccessCall(SearchCriteria searchCriteria) {
    given()
        .contentType(ContentType.JSON)
        .body(searchCriteria)
        .when()
        .post(getRestServiceUrl() + SEARCH_PATH)
        .then()
        .assertThat()
        .statusCode(200)
        .body(
            "total_hit_count", greaterThan(0),
            "hits.person[0].source", equalTo("reporter"),
            "hits.person[0].identifier", notNullValue(),
            "hits.person[0].expanded_identifier", notNullValue(),
            "hits.person[0].first_name", equalToIgnoringWhiteSpace("bobby"),
            "hits.person[0].last_name", equalToIgnoringWhiteSpace("Smith"),
            "hits.person[0].primary_phone_number", notNullValue()
        );
  }
}
