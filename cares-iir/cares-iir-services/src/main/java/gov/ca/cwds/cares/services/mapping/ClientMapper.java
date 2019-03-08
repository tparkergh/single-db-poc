package gov.ca.cwds.cares.services.mapping;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import gov.ca.cwds.cares.interfaces.model.people.Client;
import gov.ca.cwds.cares.persistence.entity.ClientEntity;

/**
 * CWDS J Team
 */
@Mapper
public interface ClientMapper {
  
  ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
  
  Client mapToClient(ClientEntity clientEntity);
  
  Collection<Client> mapToClients(Collection<ClientEntity> clientEntities);
}
