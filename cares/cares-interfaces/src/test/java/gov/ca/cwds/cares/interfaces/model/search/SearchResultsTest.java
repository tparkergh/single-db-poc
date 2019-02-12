package gov.ca.cwds.cares.interfaces.model.search;

import org.junit.Assert;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.interfaces.model.search.hit.PersonSearchHit;

public class SearchResultsTest {
  
  @Test
  public void testSerde() throws Exception {
    SearchResults searchResults = new SearchResults();
    searchResults.setExecutionTimeMillis(20L);
    
    PersonSearchHit personSearchHit_1 = new PersonSearchHit();
    personSearchHit_1.setFirstName("Shahid");
    personSearchHit_1.setLastName("Saleemi");
    personSearchHit_1.setId("ss_id");
    personSearchHit_1.setScore(0d);
    personSearchHit_1.setSource("reporter");
    
    PersonSearchHit personSearchHit_2 = new PersonSearchHit();
    personSearchHit_2.setFirstName("Bob");
    personSearchHit_2.setLastName("Smith");
    personSearchHit_2.setId("bb_id");
    personSearchHit_2.setScore(0d);
    personSearchHit_2.setSource("reporter");
    
    searchResults.addHit(personSearchHit_1);
    searchResults.addHit(personSearchHit_2);
    
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(searchResults);
    
    SearchResults actual = mapper.readValue(json, SearchResults.class);
    Assert.assertEquals(searchResults, actual);
  }
}
