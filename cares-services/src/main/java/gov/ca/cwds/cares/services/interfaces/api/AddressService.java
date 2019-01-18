package gov.ca.cwds.cares.services.interfaces.api;

import gov.ca.cwds.cares.services.interfaces.model.Address;
import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;
import java.util.Collection;

/**
 * CWDS J Team
 */
public interface AddressService {
  Collection<ClientAddress> getClientAddresses(String clientId);

  ClientAddress updateClientAddress(ClientAddress clientAddress);

  Address updateAddress(Address address);
}
