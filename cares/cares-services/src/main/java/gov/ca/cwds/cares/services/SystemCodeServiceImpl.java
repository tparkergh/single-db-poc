package gov.ca.cwds.cares.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.persistence.entity.SystemCodeEntity;
import gov.ca.cwds.cares.persistence.entity.SystemMetaEntity;
import gov.ca.cwds.cares.persistence.repository.SystemCodeRepository;
import gov.ca.cwds.cares.persistence.repository.SystemMetaRepository;
import gov.ca.cwds.cares.interfaces.api.SystemCodeService;
import gov.ca.cwds.cares.interfaces.model.SystemCode;
import gov.ca.cwds.cares.interfaces.model.SystemMeta;
import gov.ca.cwds.cares.services.mapping.SystemCodeMapper;
import gov.ca.cwds.cares.services.mapping.SystemMetaMapper;

@Service
public class SystemCodeServiceImpl implements SystemCodeService {

  @Autowired
  private SystemCodeRepository systemCodeRepository;

  @Autowired
  private SystemMetaRepository systemMetaRepository;

  @Override
  @ExecutionTimer
  public SystemCode getSystemCodeById(Integer id) {
    SystemCodeEntity entity = systemCodeRepository.findById(id).get();
    return SystemCodeMapper.INSTANCE.mapToSystemCode(entity);
  }

  @Override
  @ExecutionTimer
  public Collection<SystemCode> getSystemCodes(String metaName) {
    Collection<SystemCodeEntity> systemCodeEntities = systemCodeRepository.findByMetaName(metaName);
    return SystemCodeMapper.INSTANCE.mapToSystemCodes(systemCodeEntities);
  }

  @Override
  @ExecutionTimer
  public Collection<SystemMeta> getAllMetas() {
    Collection<SystemMetaEntity> systemMetaEntities = systemMetaRepository.findAll();
    return SystemMetaMapper.INSTANCE.mapToSystemMetas(systemMetaEntities);
  }
}
