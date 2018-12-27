package gov.ca.cwds.cares.services.interfaces.api;

import java.util.Collection;
import gov.ca.cwds.cares.services.interfaces.model.Client;

public interface ClientService {
  
  Client getClient(String id);
  
  Collection<Client> getAllClients();
}
