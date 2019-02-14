package gov.ca.cwds.cares.services.search.jdbc;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.google.common.base.Preconditions;
import gov.ca.cwds.cares.interfaces.api.SearchService;
import gov.ca.cwds.cares.interfaces.model.search.SearchCriteria;
import gov.ca.cwds.cares.interfaces.model.search.SearchResults;
import gov.ca.cwds.cares.interfaces.model.search.hit.PersonSearchHit;
import gov.ca.cwds.cares.interfaces.model.search.query.PersonSearchQuery;
import gov.ca.cwds.cares.persistence.entity.ReporterEntity;
import gov.ca.cwds.cares.persistence.repository.ReporterRepository;
import gov.ca.cwds.cares.services.mapping.search.PersonSearchHitMapper;

/**
 * @author CWDS J Team
 */
@Service("JdbcSearchService")
public class JdbcSearchService implements SearchService {
  
  private static final String SUPPORTED_SOURCE = "reporter";
  private static final int MAX_HITS = 10;

  @Autowired
  private ReporterRepository reporterRepository;
  
  public JdbcSearchService() {
    // Default no-argument constructor
  }

  @Override
  public SearchResults search(SearchCriteria searchCriteria) {    
    int limit = searchCriteria.getLimit();
    Preconditions.checkArgument(limit > -1 && limit <= MAX_HITS, 
        "Max limit can not exceed " + MAX_HITS + ", provided: " + limit);
    
    Collection<String> sources = searchCriteria.getSources();
    Preconditions.checkArgument(sources != null && sources.contains(SUPPORTED_SOURCE), 
        "Sources must contain '" + SUPPORTED_SOURCE + "', provided: " + sources);
        
    PersonSearchQuery query = (PersonSearchQuery) searchCriteria.getQuery();
    
    Specification<ReporterEntity> reporterSearchSpecs = new ReporterSearchSpecification(query);
    Pageable pageable = PageRequest.of(0, limit);
    
    @SuppressWarnings("unchecked")
    Page<ReporterEntity> reporterEntityPage = reporterRepository.findAll(reporterSearchSpecs, pageable);
    
    List<ReporterEntity> reporterEntityList = reporterEntityPage.getContent();    
    Collection<PersonSearchHit> hits = PersonSearchHitMapper.INSTANCE.mapToPersonSearchHits(reporterEntityList);
    
    for (PersonSearchHit hit : hits) {
      hit.setScore(0d);
      hit.setSource(SUPPORTED_SOURCE);
    }
    
    SearchResults searchResults = new SearchResults();
    searchResults.setHits(hits);
    
    return searchResults;
  }
}
