package gov.ca.cwds.cares.services.interfaces.api;

import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;
import java.util.Collection;
import gov.ca.cwds.cares.services.interfaces.model.Client;

public interface ClientService {
  
  Client getClient(String clientId);
  
  Collection<Client> getAllClients();

  Collection<ClientAddress> getClientAddresses(String clientId);

  ClientAddress updateClientAddress(ClientAddress clientAddress);
}
