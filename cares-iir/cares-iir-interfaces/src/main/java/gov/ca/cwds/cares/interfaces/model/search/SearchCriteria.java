package gov.ca.cwds.cares.interfaces.model.search;

import gov.ca.cwds.cares.interfaces.model.BaseModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.interfaces.model.search.query.SearchQuery;

/**
 * @author CWDS J Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchCriteria extends BaseModel implements Serializable {
  
  private static final long serialVersionUID = 8839424187694570310L;
  
  private Integer limit;
  private Collection<String> sources = new ArrayList<>();
  private SearchQuery query;

  public SearchCriteria() {
    // Default no-argument constructor
  }
  
  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer size) {
    this.limit = size;
  }

  public SearchQuery getQuery() {
    return query;
  }

  public void setQuery(SearchQuery query) {
    this.query = query;
  }

  public Collection<String> getSources() {
    return sources;
  }

  public void setSources(Collection<String> sources) {
    this.sources = sources;
  }
  
  public void addSource(String source) {
    this.sources.add(source);
  }
}
