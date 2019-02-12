package gov.ca.cwds.cares.interfaces.model.search;

import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.interfaces.model.search.field.SearchField;
import gov.ca.cwds.cares.interfaces.model.search.field.SearchFieldName;
import gov.ca.cwds.cares.interfaces.model.search.match.ExactMatch;
import gov.ca.cwds.cares.interfaces.model.search.match.RangeMatch;

public class SearchCriteriaTest {

  @Test
  public void testSerde() throws Exception {
    SearchCriteria searchCriteria = new SearchCriteria();
    searchCriteria.setLimit(10);
    searchCriteria.addSource("reporter");
    searchCriteria.addSource("victim");
    searchCriteria.addSource("perpetrator");
    
    SearchQuery searchQuery = new SearchQuery();    
    searchQuery.addMustMatch(new ExactMatch(
        new SearchField(SearchFieldName.PERSON_FIRST_NAME.getValue(), "Shahid")));
    searchQuery.addMustMatch(
        new ExactMatch(new SearchField(SearchFieldName.PERSON_LAST_NAME.getValue(), "Saleemi")));
    searchQuery.addShouldMatch(
        new ExactMatch(new SearchField(SearchFieldName.PERSON_PRIMARY_PHONR.getValue(), "1111111111")));
    
    searchQuery.addShouldMatch(new RangeMatch(
        new SearchField("age", "1"), new SearchField("age", "18")));
    
    searchQuery.addShouldMatch(new RangeMatch(
        new SearchField("dob", LocalDate.now().toString()), null));
    
    searchCriteria.setQuery(searchQuery);
    
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(searchCriteria);
    
    SearchCriteria actual = mapper.readValue(json, SearchCriteria.class);
    Assert.assertEquals(searchCriteria, actual);
  }
}
