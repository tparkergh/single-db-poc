package gov.ca.cwds.cares.interfaces.api;

import gov.ca.cwds.cares.interfaces.model.Address;

/**
 * CWDS J Team
 */
public interface AddressService {
  
  Address updateAddress(Address address);
  
  Address enrichGeoLocation(Address address);
}
