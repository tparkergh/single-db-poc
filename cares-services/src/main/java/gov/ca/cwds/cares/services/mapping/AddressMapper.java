package gov.ca.cwds.cares.services.mapping;

import gov.ca.cwds.cares.services.interfaces.model.Address;
import gov.ca.cwds.cics.model.address.AddressData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * CWDS J Team
 */
@Mapper
public interface AddressMapper {
  AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

  @Mapping(target = "txnHeaderStaffId", constant = "0WM")
  AddressData mapToAddressData(Address address);

}
