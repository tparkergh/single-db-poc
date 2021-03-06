package gov.ca.cwds.cares.services.mapping;

import gov.ca.cwds.cares.interfaces.model.people.ClientAddress;
import gov.ca.cwds.cares.persistence.entity.ClientAddressEntity;
import gov.ca.cwds.cics.model.CicsAddressRequest;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * CWDS J Team
 */
@Mapper(uses = AddressMapper.class)
public interface ClientAddressMapper {
  ClientAddressMapper INSTANCE = Mappers.getMapper(ClientAddressMapper.class);

  @Mapping(target = "addressData", source = "address")
  CicsAddressRequest mapToCicsAddressRequest(ClientAddress clientAddress);
  
  Collection<ClientAddress> mapToClientAddresses(Collection<ClientAddressEntity> clientAddressEntities);
}
