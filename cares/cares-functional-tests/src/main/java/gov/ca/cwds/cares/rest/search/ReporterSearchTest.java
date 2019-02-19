package gov.ca.cwds.cares.rest.search;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.SearchResults;
import gov.ca.cwds.cares.interfaces.model.search.query.PersonSearchQuery;

/**
 * CWDS J Team
 */
@RunWith(SpringRunner.class)
@TestPropertySource
public class ReporterSearchTest {

  @Value("${cares-service.base-url}")
  private String restServiceUrl;

  @Value("${reporter-search.first-name}")
  private String reporterFirstName;

  @Value("${reporter-search.last-name}")
  private String reporterLastName;

  @Test
  public void whenCallReporterSearch_thenSuccessResponse() throws Exception {
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
        .contentType("application/json")
        .body(searchCriteria)
        .when()
        .post(restServiceUrl + "/searches")
        .asString();

    assertNotNull(responseJson);

    ObjectMapper mapper = new ObjectMapper();
    SearchResults searchResults = mapper.readValue(responseJson, SearchResults.class);
    assertTrue(searchResults.getTotalHitCount() > 0);
  }
}
