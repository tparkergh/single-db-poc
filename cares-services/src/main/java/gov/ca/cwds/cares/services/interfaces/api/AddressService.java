package gov.ca.cwds.cares.services.interfaces.api;

import gov.ca.cwds.cares.services.interfaces.model.Address;

/**
 * CWDS J Team
 */
public interface AddressService {
  
  Address updateAddress(Address address);
  
  Address enrichGeoLocation(Address address);
}
