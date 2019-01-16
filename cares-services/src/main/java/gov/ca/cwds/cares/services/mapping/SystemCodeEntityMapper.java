package gov.ca.cwds.cares.services.mapping;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import gov.ca.cwds.cares.persistence.entity.SystemCodeEntity;
import gov.ca.cwds.cares.services.interfaces.model.SystemCode;

/**
 * CWDS J Team
 */
@Mapper
public interface SystemCodeEntityMapper {

  public SystemCodeEntityMapper INSTANCE = Mappers.getMapper(SystemCodeEntityMapper.class);

  SystemCode mapSystemCodeEntity(SystemCodeEntity systemCodeEntity);

  Collection<SystemCode> mapSystemCodeEntities(Collection<SystemCodeEntity> systemCodeEntities);
}
