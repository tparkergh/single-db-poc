package gov.ca.cwds.cares.rest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import gov.ca.cwds.cares.services.interfaces.api.AddressService;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.services.interfaces.api.ClientService;
import gov.ca.cwds.cares.services.interfaces.model.Client;
import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {
  
  private MockMvc mockMvc;
  
  @Mock
  private ClientService clientService;
  
  @Mock
  private AddressService addressService;

  @InjectMocks
  private ClientController clientController; 

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
  }
  
  @Test
  public void testGetAllClients() throws Exception {
    String id_1 = "testClientId_1";
    String id_2 = "testClientId_2";
    
    Client client_1 = new Client();
    client_1.setIdentifier(id_1);
    
    Client client_2 = new Client();
    client_2.setIdentifier(id_2);
    
    Collection<Client> clients = Arrays.asList(client_1, client_2);
    
    when(clientService.getAllClients()).thenReturn(clients);
    
    ObjectMapper objMapper = new ObjectMapper();
    String json = objMapper.writeValueAsString(clients);
    
    mockMvc.perform(get("/clients").accept(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk()).andExpect(content().json(json));
  }
  
  @Test
  public void testGetClient() throws Exception {
    String id = "testClientId";
    
    Client client = new Client();
    client.setIdentifier(id);
    
    when(clientService.getClient(any())).thenReturn(client);
    
    ObjectMapper objMapper = new ObjectMapper();
    String json = objMapper.writeValueAsString(client);
    
    mockMvc.perform(get("/clients/" + id).accept(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk()).andExpect(content().json(json));
  }
  
  @Test
  public void testGetClientAddresses() throws Exception {
    String id_1 = "testClientAddressId_1";
    String id_2 = "testClientAddressId_2";
    
    ClientAddress clientAddress_1 = new ClientAddress();
    clientAddress_1.setIdentifier(id_1);
    
    ClientAddress clientAddress_2 = new ClientAddress();
    clientAddress_2.setIdentifier(id_2);
    
    Collection<ClientAddress> clientAddresses = Arrays.asList(clientAddress_1, clientAddress_2);
    
    when(addressService.getClientAddresses(any())).thenReturn(clientAddresses);
    
    ObjectMapper objMapper = new ObjectMapper();
    String json = objMapper.writeValueAsString(clientAddresses);
    
    mockMvc.perform(get("/clients/clientId/addresses").accept(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk()).andExpect(content().json(json));
  }
}
