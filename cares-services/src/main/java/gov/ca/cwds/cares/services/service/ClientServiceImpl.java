package gov.ca.cwds.cares.services.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
    Iterator<ClientEntity> clientEntitiesIter = clientRepository.findAll().iterator();
    List<ClientEntity> clientEntitiesList = new ArrayList<>();
    clientEntitiesIter.forEachRemaining(clientEntitiesList::add);        
    return ClientEntityMapper.INSTANCE.mapClientEntities(clientEntitiesList);  
  }
}
