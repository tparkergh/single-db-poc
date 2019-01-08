package gov.ca.cwds.cares.rest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import gov.ca.cwds.cares.services.interfaces.api.ClientService;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {
  
  @Autowired
  private MockMvc mvc;

  @MockBean
  private ClientService clientService;
  
  @Test
  public void test() throws Exception {
    
  }
}
