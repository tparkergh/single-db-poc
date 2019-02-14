package gov.ca.cwds.cares.interfaces.model.search;

import org.junit.Assert;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.interfaces.model.search.query.PersonSearchQuery;

public class SearchCriteriaTest {

  @Test
  public void testSerde() throws Exception {
    SearchCriteria searchCriteria = new SearchCriteria();
    searchCriteria.setLimit(10);
    searchCriteria.addSource("reporter");
    searchCriteria.addSource("victim");
    searchCriteria.addSource("perpetrator");
    
    PersonSearchQuery searchQuery = new PersonSearchQuery();
    searchQuery.setFirstName("Shahid");
    searchQuery.setLastName("Saleemi");
    searchQuery.setPrimaryPhoneNumber(1111111111L);
    
    searchCriteria.setQuery(searchQuery);
    
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(searchCriteria);
    
    SearchCriteria actual = mapper.readValue(json, SearchCriteria.class);
    Assert.assertEquals(searchCriteria, actual);
  }
}
