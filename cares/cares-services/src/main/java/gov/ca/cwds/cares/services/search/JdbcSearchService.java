package gov.ca.cwds.cares.services.search;

import java.util.Collection;
import org.springframework.stereotype.Service;
import gov.ca.cwds.cares.interfaces.api.SearchService;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.SearchResults;
import gov.ca.cwds.cares.interfaces.model.search.hit.PersonSearchHit;
import gov.ca.cwds.cares.interfaces.model.search.query.SearchQuery;

/**
 * @author CWDS J Team
 */
@Service("JdbcSearchService")
public class JdbcSearchService implements SearchService {

  public JdbcSearchService() {
    // Default no-argument constructor
  }

  @Override
  public SearchResults search(SearchCriteria searchCriteria) {    
    int limit = searchCriteria.getLimit();
    if (limit > 10) {
      // error
    }
    
    Collection<String> sources = searchCriteria.getSources();
    if (sources == null || sources.size() > 1 || !sources.contains("reporter")) {
      // error
    }
    
    SearchQuery query = searchCriteria.getQuery();
    
    // This is canned response
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
    
    return searchResults;
  }
}
