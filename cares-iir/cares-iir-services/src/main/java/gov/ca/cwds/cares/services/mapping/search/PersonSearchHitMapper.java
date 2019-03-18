package gov.ca.cwds.cares.services.mapping.search;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import gov.ca.cwds.cares.interfaces.model.search.hit.PersonSearchHit;
import gov.ca.cwds.cares.persistence.entity.PersonCrossReferenceEntity;
import gov.ca.cwds.cares.persistence.entity.ReporterEntity;

/**
 * CWDS J Team
 */
@Mapper
public abstract class PersonSearchHitMapper {
  
  public static final PersonSearchHitMapper INSTANCE = Mappers.getMapper(PersonSearchHitMapper.class);
  
  private static final String REPORTER_PERSON_SOURCE = "reporter";
  private static final double DEFAULT_SCORE = 0d;
  
  public abstract Collection<PersonSearchHit> mapReporterEntitiesToPersonSearchHits(
      Collection<ReporterEntity> reporterEntities);
  
  @AfterMapping
  public void afterMappingReporterEntitiesToPersonSearchHits(
      @MappingTarget Collection<PersonSearchHit> personSearchHits, 
      Collection<ReporterEntity> reporterEntities) {
    Map<String, ReporterEntity> reporterEntityMap = getReporterEntityMapByReporterId(reporterEntities);
    
    for (PersonSearchHit hit : personSearchHits) {
      hit.setScore(DEFAULT_SCORE);
      hit.setSource(REPORTER_PERSON_SOURCE);
      
      // Get person ID for reporter
      String reporterId = hit.getIdentifier();
      String personId = getPersonIdForReporter(reporterId, reporterEntityMap);
      hit.setIdentifier(personId);      
    }
  }
  
  private Map<String, ReporterEntity> getReporterEntityMapByReporterId(Collection<ReporterEntity> reporterEntityList) {
    Map<String, ReporterEntity> map = new HashMap<>();
    
    for (ReporterEntity reporterEntity : reporterEntityList) {
      map.put(reporterEntity.getIdentifier(), reporterEntity);
    }
    return map;
  }
  
  private String getPersonIdForReporter(String reporterId, Map<String, ReporterEntity> reporterEntityMap) {
    String personId = null;
    ReporterEntity reporterEntity = reporterEntityMap.get(reporterId);
    Collection<PersonCrossReferenceEntity> personCrossReferences = reporterEntity.getPersonCrossReferences();
    
    if (personCrossReferences != null && !personCrossReferences.isEmpty()) {
      personId = personCrossReferences.iterator().next().getPersonId();
    }
    
    return personId;
  }
}
