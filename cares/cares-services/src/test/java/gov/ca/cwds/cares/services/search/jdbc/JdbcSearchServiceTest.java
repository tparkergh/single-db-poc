package gov.ca.cwds.cares.services.search.jdbc;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.google.common.collect.Lists;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.SearchResults;
import gov.ca.cwds.cares.interfaces.model.search.hit.PersonSearchHit;
import gov.ca.cwds.cares.interfaces.model.search.query.PersonSearchQuery;
import gov.ca.cwds.cares.persistence.entity.ReporterEntity;
import gov.ca.cwds.cares.persistence.repository.ReporterRepository;

@RunWith(MockitoJUnitRunner.class)
public class JdbcSearchServiceTest {

  @Mock
  private ReporterRepository reporterRepository;

  @InjectMocks
  private JdbcSearchService searchService;
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  @Test
  public void testSearch() {
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
    
    ReporterEntity reporterEntity_1 = new ReporterEntity();
    reporterEntity_1.setIdentifier("reporter_1");
    reporterEntity_1.setFirstName("Shahid");
    reporterEntity_1.setLastName("Saleemi");
    
    ReporterEntity reporterEntity_2 = new ReporterEntity();
    reporterEntity_2.setIdentifier("reporter_2");
    reporterEntity_2.setFirstName("Bob");
    reporterEntity_2.setLastName("Smith");
    
    when(reporterRepository.findAll(any(ReporterSearchSpecification.class), any(Pageable.class)))
    .thenReturn(new PageImpl(Lists.newArrayList(reporterEntity_1, reporterEntity_2)));
    
    SearchResults actual = searchService.search(searchCriteria);
    
    SearchResults expected = new SearchResults();
    
    PersonSearchHit personSearchHit_1 = new PersonSearchHit();
    personSearchHit_1.setFirstName("Shahid");
    personSearchHit_1.setLastName("Saleemi");
    personSearchHit_1.setIdentifier("reporter_1");
    personSearchHit_1.setScore(0d);
    personSearchHit_1.setSource("reporter");
    
    PersonSearchHit personSearchHit_2 = new PersonSearchHit();
    personSearchHit_2.setFirstName("Bob");
    personSearchHit_2.setLastName("Smith");
    personSearchHit_2.setIdentifier("reporter_2");
    personSearchHit_2.setScore(0d);
    personSearchHit_2.setSource("reporter");
    
    expected.setHits(Lists.newArrayList(personSearchHit_1, personSearchHit_2));
    
    assertEquals(expected, actual);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSearch_InvalidLimit() {
    SearchCriteria searchCriteria = new SearchCriteria();
    searchCriteria.setLimit(12);
    searchCriteria.addSource("reporter");
    searchCriteria.addSource("victim");
    searchCriteria.addSource("perpetrator");
    
    PersonSearchQuery searchQuery = new PersonSearchQuery();
    searchQuery.setFirstName("Shahid");
    searchQuery.setLastName("Saleemi");
    searchQuery.setPrimaryPhoneNumber(1111111111);
    
    searchCriteria.setQuery(searchQuery);
    
    searchService.search(searchCriteria);        
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSearch_InvalidSource() {
    SearchCriteria searchCriteria = new SearchCriteria();
    searchCriteria.setLimit(10);
    searchCriteria.addSource("victim");
    searchCriteria.addSource("perpetrator");
    
    PersonSearchQuery searchQuery = new PersonSearchQuery();
    searchQuery.setFirstName("Shahid");
    searchQuery.setLastName("Saleemi");
    searchQuery.setPrimaryPhoneNumber(1111111111);
    
    searchCriteria.setQuery(searchQuery);
    
    searchService.search(searchCriteria);        
  }
}
