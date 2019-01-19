package gov.ca.cwds.cares.services.interfaces.api;

import java.util.Collection;

import gov.ca.cwds.cares.services.interfaces.model.SystemCode;
import gov.ca.cwds.cares.services.interfaces.model.SystemMeta;

public interface SystemCodeService {

  SystemCode getSystemCodeById(Integer id);

  Collection<SystemMeta> getAllMetas();

  Collection<SystemCode> getSystemCodes(String metaName);

}
