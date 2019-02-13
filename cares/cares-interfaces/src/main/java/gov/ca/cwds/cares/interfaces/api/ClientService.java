package gov.ca.cwds.cares.interfaces.api;

import java.util.Collection;
import gov.ca.cwds.cares.interfaces.model.people.Client;
import gov.ca.cwds.cares.interfaces.model.people.ClientAddress;

public interface ClientService {
  
  Client getClient(String clientId);
  
  Collection<Client> getAllClients();

  Collection<ClientAddress> getClientAddresses(String clientId);

  ClientAddress updateClientAddress(ClientAddress clientAddress);
}
