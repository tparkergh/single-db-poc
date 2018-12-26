package gov.ca.cwds.cares.services.service;

import java.util.Collection;
import org.springframework.stereotype.Service;
import gov.ca.cwds.cares.services.interfaces.api.ClientService;
import gov.ca.cwds.cares.services.interfaces.model.Client;

@Service
public class ClientServiceImpl implements ClientService {

  @Override
  public Client getClient(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Collection<Client> getAllClients() {
    // TODO Auto-generated method stub
    return null;
  }
}
