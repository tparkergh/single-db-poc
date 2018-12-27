package gov.ca.cwds.cares.services.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gov.ca.cwds.cares.persistence.entity.ClientEntity;
import gov.ca.cwds.cares.persistence.repository.ClientRepository;
import gov.ca.cwds.cares.services.interfaces.api.ClientService;
import gov.ca.cwds.cares.services.interfaces.model.Client;
import gov.ca.cwds.cares.services.mapping.ClientEntityMapper;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  ClientRepository clientRepository; 
  
  @Override
  public Client getClient(String id) {
    ClientEntity clientEntity = clientRepository.findById(id).get();
    return ClientEntityMapper.INSTANCE.mapClientEntity(clientEntity);
  }

  @Override
  public Collection<Client> getAllClients() {
    Collection<ClientEntity> clientEntities = clientRepository.findAll();
    return ClientEntityMapper.INSTANCE.mapClientEntities(clientEntities);  
  }
}
