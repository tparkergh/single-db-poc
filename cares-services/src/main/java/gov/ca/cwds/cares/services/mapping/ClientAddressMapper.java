package gov.ca.cwds.cares.services.mapping;

import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;
import gov.ca.cwds.cics.model.address.CicsAddressRequest;
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
}
