package gov.ca.cwds.cares.services.search;

import java.util.Collection;
import org.springframework.stereotype.Service;
import gov.ca.cwds.cares.interfaces.api.SearchService;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.SearchResults;
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
    
    return new SearchResults();
  }
}
