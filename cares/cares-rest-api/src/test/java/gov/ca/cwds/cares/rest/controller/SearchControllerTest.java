package gov.ca.cwds.cares.rest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import gov.ca.cwds.cares.interfaces.api.SearchService;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.SearchResults;
import gov.ca.cwds.cares.interfaces.model.search.hit.PersonSearchHit;
import gov.ca.cwds.cares.interfaces.model.search.query.PersonSearchQuery;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {

  private MockMvc mockMvc;

  @Mock
  private SearchService searchService;

  @InjectMocks
  private SearchController searchController;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
  }

  @Test
  public void testSearch() throws Exception {
    SearchCriteria searchCriteria = new SearchCriteria();
    searchCriteria.setLimit(10);
    searchCriteria.addSource("reporter");
    searchCriteria.addSource("victim");
    searchCriteria.addSource("perpetrator");
    
    PersonSearchQuery searchQuery = new PersonSearchQuery();
    searchQuery.setFirstName("Shahid");
    searchQuery.setLastName("Saleemi");
    searchQuery.setPrimaryPhoneNumber(1111111111);
    
    searchCriteria.setQuery(searchQuery);
    
    SearchResults searchResults = new SearchResults();
    searchResults.setExecutionTimeMillis(20L);
    
    PersonSearchHit personSearchHit_1 = new PersonSearchHit();
    personSearchHit_1.setFirstName("Shahid");
    personSearchHit_1.setLastName("Saleemi");
    personSearchHit_1.setIdentifier("ss_id");
    personSearchHit_1.setScore(0d);
    personSearchHit_1.setSource("reporter");
    
    PersonSearchHit personSearchHit_2 = new PersonSearchHit();
    personSearchHit_2.setFirstName("Bob");
    personSearchHit_2.setLastName("Smith");
    personSearchHit_2.setIdentifier("bb_id");
    personSearchHit_2.setScore(0d);
    personSearchHit_2.setSource("reporter");
    
    searchResults.setHits(Lists.newArrayList(personSearchHit_1, personSearchHit_2));
    
    when(searchService.search(any())).thenReturn(searchResults);

    ObjectMapper objMapper = new ObjectMapper();
    String request = objMapper.writeValueAsString(searchCriteria);
    String response = objMapper.writeValueAsString(searchResults);

    mockMvc.perform(post("/searches")
        .content(request)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(response));
  }
}
