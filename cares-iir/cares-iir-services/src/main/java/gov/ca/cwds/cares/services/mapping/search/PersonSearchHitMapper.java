package gov.ca.cwds.cares.services.mapping.search;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import gov.ca.cwds.cares.interfaces.model.search.hit.PersonSearchHit;
import gov.ca.cwds.cares.persistence.entity.ReporterEntity;

/**
 * CWDS J Team
 */
@Mapper
public interface PersonSearchHitMapper {
  
  PersonSearchHitMapper INSTANCE = Mappers.getMapper(PersonSearchHitMapper.class);
  
  PersonSearchHit mapToPersonSearchHit(ReporterEntity reporterEntity);
  
  Collection<PersonSearchHit> mapToPersonSearchHits(Collection<ReporterEntity> reporterEntities);
}
