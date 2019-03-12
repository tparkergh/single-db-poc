package gov.ca.cwds.cares.services.mapping;

import gov.ca.cwds.cares.interfaces.model.Hearing;
import gov.ca.cwds.cares.persistence.entity.HearingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

/**
 * CWDS J Team
 */
@Mapper
public interface HearingMapper {
  
  HearingMapper INSTANCE = Mappers.getMapper(HearingMapper.class);
  
  Hearing mapToHearing(HearingEntity hearingEntity);
  
  Collection<Hearing> mapToHearings(Collection<HearingEntity> hearingEntities);
}
