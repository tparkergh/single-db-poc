package gov.ca.cwds.cares.services.search;

import java.util.Collection;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import gov.ca.cwds.cares.interfaces.api.SearchService;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.SearchResults;
import gov.ca.cwds.cares.interfaces.model.search.field.SearchFieldName;

/**
 * @author CWDS J Team
 */
@Service("JdbcSearchService")
public class JdbcSearchService implements SearchService {

  public JdbcSearchService() {
    // Default no-argument constructor
  }

  @Override
  public SearchResults search(SearchCriteria searchRequest) {
    return new SearchResults();
  }

  @Override
  public Collection<String> getSearchFieldNames() {
    return SearchFieldName.getAllFieldNames();
  }

  @Override
  public Collection<String> getSearchSources() {
    return Lists.newArrayList("reporter", "victim", "perpetrator");
  }
}
