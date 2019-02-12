package gov.ca.cwds.cares.interfaces.api;

import gov.ca.cwds.cares.iterfaces.model.people.Client;
import gov.ca.cwds.cares.iterfaces.model.people.ClientAddress;
import java.util.Collection;

public interface ClientService {
  
  Client getClient(String clientId);
  
  Collection<Client> getAllClients();

  Collection<ClientAddress> getClientAddresses(String clientId);

  ClientAddress updateClientAddress(ClientAddress clientAddress);
}
