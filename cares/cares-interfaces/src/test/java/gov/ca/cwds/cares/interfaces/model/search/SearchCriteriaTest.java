package gov.ca.cwds.cares.interfaces.model.search;

import java.time.LocalDate;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.interfaces.model.search.field.DateSearchField;
import gov.ca.cwds.cares.interfaces.model.search.field.NumberSearchField;
import gov.ca.cwds.cares.interfaces.model.search.field.StringSearchField;
import gov.ca.cwds.cares.interfaces.model.search.match.FieldMatch;
import gov.ca.cwds.cares.interfaces.model.search.match.RangeMatch;
import org.junit.Assert;

public class SearchCriteriaTest {

  @Test
  public void testCreation() throws Exception {
    SearchCriteria searchCriteria = new SearchCriteria();
    searchCriteria.setLimit(10);
    searchCriteria.addSource("reporter");
    searchCriteria.addSource("victim");
    searchCriteria.addSource("perpetrator");
    
    SearchQuery searchQuery = new SearchQuery();    
    searchQuery.addMust(new FieldMatch(new StringSearchField("first_name", "Shahid")));
    searchQuery.addMust(new FieldMatch(new StringSearchField("last_name", "Saleemi")));
    searchQuery.addShould(new FieldMatch(new NumberSearchField("primary_phone", 1111111111)));
    
    searchQuery.addShould(new RangeMatch(
        new NumberSearchField("age", 1), new NumberSearchField("age", 18)));
    
    searchQuery.addShould(new RangeMatch(
        new DateSearchField("dob", LocalDate.now()), null));
    
    searchCriteria.setQuery(searchQuery);
    
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(searchCriteria);
    
    SearchCriteria actual = mapper.readValue(json, SearchCriteria.class);
    Assert.assertEquals(searchCriteria, actual);
  }
}
