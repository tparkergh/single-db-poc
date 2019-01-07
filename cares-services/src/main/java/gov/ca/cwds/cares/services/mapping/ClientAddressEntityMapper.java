package gov.ca.cwds.cares.services.mapping;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import gov.ca.cwds.cares.persistence.entity.ClientAddressEntity;
import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;

/**
 * CWDS J Team
 */
@Mapper
public interface ClientAddressEntityMapper {
  
  public ClientAddressEntityMapper INSTANCE = Mappers.getMapper(ClientAddressEntityMapper.class);
  
  Collection<ClientAddress> mapClientAddressEntities(Collection<ClientAddressEntity> clientAddressEntities);  
}
