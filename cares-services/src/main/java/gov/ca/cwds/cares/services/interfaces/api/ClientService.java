package gov.ca.cwds.cares.services.interfaces.api;

import java.util.Collection;
import gov.ca.cwds.cares.services.interfaces.model.Client;
import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;

public interface ClientService {
  
  Client getClient(String clientId);
  
  Collection<ClientAddress> getClientAddresses(String clientId);
  
  Collection<Client> getAllClients();
}
