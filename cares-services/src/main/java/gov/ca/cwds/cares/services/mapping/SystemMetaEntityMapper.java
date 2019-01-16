package gov.ca.cwds.cares.services.mapping;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import gov.ca.cwds.cares.persistence.entity.SystemMetaEntity;
import gov.ca.cwds.cares.services.interfaces.model.SystemMeta;

/**
 * CWDS J Team
 */
@Mapper
public interface SystemMetaEntityMapper {

  public SystemMetaEntityMapper INSTANCE = Mappers.getMapper(SystemMetaEntityMapper.class);

  SystemMeta mapSystemMetaEntity(SystemMetaEntity systemMetaEntity);

  Collection<SystemMeta> mapSystemMetaEntities(Collection<SystemMetaEntity> systemMetaEntities);
}
