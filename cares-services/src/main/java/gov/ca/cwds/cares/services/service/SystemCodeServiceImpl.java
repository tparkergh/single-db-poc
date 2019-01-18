package gov.ca.cwds.cares.services.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.persistence.entity.SystemCodeEntity;
import gov.ca.cwds.cares.persistence.entity.SystemMetaEntity;
import gov.ca.cwds.cares.persistence.repository.SystemCodeRepository;
import gov.ca.cwds.cares.persistence.repository.SystemMetaRepository;
import gov.ca.cwds.cares.services.interfaces.api.SystemCodeService;
import gov.ca.cwds.cares.services.interfaces.model.SystemCode;
import gov.ca.cwds.cares.services.interfaces.model.SystemMeta;
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
