package gov.ca.cwds.cares.rest.controller;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@CrossOrigin
public class ClientController {

  @Autowired
  private ClientService clientService;

  @GetMapping("/clients")
  @ApiOperation(value = "Get all clients")
  @ExecutionTimer
  public Collection<Client>  getAllClients() {
    return clientService.getAllClients();
  }  
  
  @GetMapping("/clients/{client_id}")
  @ApiOperation(value = "Get client identified by given ID")
  @ExecutionTimer
  public Client getClient(@ApiParam("Client ID") @PathVariable("client_id") String id) {
    return clientService.getClient(id);
  }  
  
  @GetMapping("/clients/{client_id}/addresses")
  @ApiOperation(value = "Get client addresses for client identified by given ID")
  @ExecutionTimer
  public Collection<ClientAddress> getClientAddresses(@ApiParam("Client ID") @PathVariable("client_id") String id) {
    return clientService.getClientAddresses(id);
  }

  @PutMapping(value = "/clients/{client_id}/addresses/{address_id}",
      consumes= MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Update client address by client id and address id")
  @ExecutionTimer
  public ClientAddress updateClientAddress(@RequestBody ClientAddress clientAddress,
      @ApiParam("Client ID") @PathVariable("client_id") String clientId,
      @ApiParam("Address ID") @PathVariable("address_id") String addressId) {
    clientAddress.setClientId(clientId);
    clientAddress.setAddressId(addressId);
    Optional.ofNullable(clientAddress.getAddress()).ifPresent(a -> a.setIdentifier(addressId));
    return clientService.updateClientAddress(clientAddress);
  }
}
