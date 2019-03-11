package gov.ca.cwds.cares.services.mapping;

import gov.ca.cwds.cares.common.Constants;
import gov.ca.cwds.cares.interfaces.model.Address;
import gov.ca.cwds.cics.model.AddressData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * CWDS J Team
 */
@Mapper
public interface AddressMapper {
  AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

  @Mapping(target = "txnHeaderStaffId", constant = Constants.LOGGED_USER_STAFF_ID)
  AddressData mapToAddressData(Address address);

}
