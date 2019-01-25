package gov.ca.cwds.cares.interfaces.api;

import gov.ca.cwds.cares.interfaces.model.ClientAddress;
import java.util.Collection;
import gov.ca.cwds.cares.interfaces.model.Client;

public interface ClientService {
  
  Client getClient(String clientId);
  
  Collection<Client> getAllClients();

  Collection<ClientAddress> getClientAddresses(String clientId);

  ClientAddress updateClientAddress(ClientAddress clientAddress);
}
