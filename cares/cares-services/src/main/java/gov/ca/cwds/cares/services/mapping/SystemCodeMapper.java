package gov.ca.cwds.cares.services.mapping;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import gov.ca.cwds.cares.persistence.entity.SystemCodeEntity;
import gov.ca.cwds.cares.interfaces.model.SystemCode;

/**
 * CWDS J Team
 */
@Mapper
public interface SystemCodeMapper {

  SystemCodeMapper INSTANCE = Mappers.getMapper(SystemCodeMapper.class);

  SystemCode mapToSystemCode(SystemCodeEntity systemCodeEntity);

  Collection<SystemCode> mapToSystemCodes(Collection<SystemCodeEntity> systemCodeEntities);
}
