package gov.ca.cwds.cares.rest.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.interfaces.api.SearchService;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.SearchResults;
import io.swagger.annotations.ApiOperation;

/**
 * @author CWDS Team J
 */
@RestController
@CrossOrigin
public class SearchController {
  
  @Autowired
  @Qualifier(value="JdbcSearchService")
  private SearchService searchService;

  @PostMapping(value = "/searches",
      consumes= MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Perform search")
  @ExecutionTimer
  public SearchResults search(@RequestBody SearchCriteria searchCriteria) {
    return searchService.search(searchCriteria);
  }
  
  @GetMapping(value = "/searches/field_names",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Get all search field names")
  @ExecutionTimer
  public Collection<String> getSearchFieldNames() {
    return searchService.getSearchFieldNames();
  }
  
  @GetMapping(value = "/searches/sources",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Get all search sources")
  @ExecutionTimer
  public Collection<String> getSearchSources() {
    return searchService.getSearchSources();
  }
}
