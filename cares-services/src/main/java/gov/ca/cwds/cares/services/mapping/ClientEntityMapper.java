package gov.ca.cwds.cares.services.mapping;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import gov.ca.cwds.cares.persistence.entity.ClientEntity;
import gov.ca.cwds.cares.services.interfaces.model.Client;

/**
 * CWDS J Team
 */
@Mapper
public interface ClientEntityMapper {
  
  public ClientEntityMapper INSTANCE = Mappers.getMapper(ClientEntityMapper.class);
  
  Client mapClientEntity(ClientEntity clientEntity);
  
  Collection<Client> mapClientEntities(Collection<ClientEntity> clientEntities);
}
