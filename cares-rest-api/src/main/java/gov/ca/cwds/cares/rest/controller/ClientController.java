package gov.ca.cwds.cares.rest.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.services.interfaces.api.ClientService;
import gov.ca.cwds.cares.services.interfaces.model.Client;
import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author CWDS Team J
 */
@RestController
public class ClientController {

  @Autowired
  private ClientService clientService;

  @GetMapping("/clients")
  @ApiOperation(value = "Get all clients")
  @ExecutionTimer
  public Collection<Client>  getAllClients() {
    return clientService.getAllClients();
  }  
  
  @GetMapping("/clients/{id}")
  @ApiOperation(value = "Get client identified by given ID")
  @ExecutionTimer
  public Client getClient(@ApiParam("Client ID") @PathVariable("id") String id) {
    return clientService.getClient(id);
  }  
  
  @GetMapping("/clients/{id}/addresses")
  @ApiOperation(value = "Get client addresses for client identified by given ID")
  @ExecutionTimer
  public Collection<ClientAddress> getClientAddresses(@ApiParam("Client ID") @PathVariable("id") String id) {
    return clientService.getClientAddresses(id);
  }  
}
