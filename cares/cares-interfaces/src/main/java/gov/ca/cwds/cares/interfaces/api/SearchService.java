package gov.ca.cwds.cares.interfaces.api;

import java.util.Collection;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.SearchResults;

/**
 * @author CWDS J Team
 */
public interface SearchService {
  
  SearchResults search(SearchCriteria searchCriteria);
  
  Collection<String> getSearchFieldNames();
  
  Collection<String> getSearchSources();
  
  Collection<String> getSearchMatchTypes();
}
