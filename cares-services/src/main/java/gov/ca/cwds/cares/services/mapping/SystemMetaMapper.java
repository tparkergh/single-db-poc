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
public interface SystemMetaMapper {

  public SystemMetaMapper INSTANCE = Mappers.getMapper(SystemMetaMapper.class);

  SystemMeta mapToSystemMeta(SystemMetaEntity systemMetaEntity);

  Collection<SystemMeta> mapToSystemMetas(Collection<SystemMetaEntity> systemMetaEntities);
}
