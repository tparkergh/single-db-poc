package gov.ca.cwds.cares.services.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import gov.ca.cwds.cares.persistence.entity.ClientAddressEntity;
import gov.ca.cwds.cares.persistence.entity.ClientEntity;
import gov.ca.cwds.cares.persistence.repository.ClientAddressRepository;
import gov.ca.cwds.cares.persistence.repository.ClientRepository;
import gov.ca.cwds.cares.services.interfaces.model.Client;
import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

  @Mock
  private ClientRepository clientRepository;
  
  @Mock
  private ClientAddressRepository clientAddressRepository;
  
  @InjectMocks
  private ClientServiceImpl clientService;
  
  @Test
  public void testGetClient() {
    String id = "testClientId";
    ClientEntity clientEntity = new ClientEntity();
    clientEntity.setIdentifier(id);
    
    Client expected = new Client();
    expected.setIdentifier(id);
    
    when(clientRepository.findById(id)).thenReturn(Optional.of(clientEntity));
    
    Client actual = clientService.getClient(id);
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetAllClient() {
    String id_1 = "testClientId_1";
    String id_2 = "testClientId_2";
    
    ClientEntity clientEntity_1 = new ClientEntity();
    clientEntity_1.setIdentifier(id_1);
    
    ClientEntity clientEntity_2 = new ClientEntity();
    clientEntity_2.setIdentifier(id_2);
    
    List<ClientEntity> clientEntities = Arrays.asList(clientEntity_1, clientEntity_2);
    
    Client client_1 = new Client();
    client_1.setIdentifier(id_1);
    
    Client client_2 = new Client();
    client_2.setIdentifier(id_2);
    
    Collection<Client> expected = Arrays.asList(client_1, client_2);
    
    when(clientRepository.findAllById(any())).thenReturn(clientEntities);
    
    Collection<Client> actual = clientService.getAllClients();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetClientAddresses() {
    String id_1 = "testClientAddressId_1";
    String id_2 = "testClientAddressId_2";
    
    ClientAddressEntity clientAddressEntity_1 = new ClientAddressEntity();
    clientAddressEntity_1.setIdentifier(id_1);
    
    ClientAddressEntity clientAddressEntity_2 = new ClientAddressEntity();
    clientAddressEntity_2.setIdentifier(id_2);
    
    List<ClientAddressEntity> clientAddressEntities = Arrays.asList(clientAddressEntity_1, clientAddressEntity_2);
    
    ClientAddress clientAddress_1 = new ClientAddress();
    clientAddress_1.setIdentifier(id_1);
    
    ClientAddress clientAddress_2 = new ClientAddress();
    clientAddress_2.setIdentifier(id_2);
    
    Collection<ClientAddress> expected = Arrays.asList(clientAddress_1, clientAddress_2);
    
    when(clientAddressRepository.findByClientId(any())).thenReturn(clientAddressEntities);
    
    Collection<ClientAddress> actual = clientService.getClientAddresses("clientId");
    assertEquals(expected, actual);
  }
}
