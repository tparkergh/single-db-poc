package gov.ca.cwds.cares.rest.controller;

import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.services.interfaces.api.AddressService;
import gov.ca.cwds.cares.services.interfaces.model.Address;
import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

/**
 * CWDS J Team
 */
@RestController
@CrossOrigin
public class AddressController {
  @Autowired
  private AddressService addressService;

  @GetMapping("/clients/{client_id}/addresses")
  @ApiOperation(value = "Get client addresses for client identified by given ID")
  @ExecutionTimer
  public Collection<ClientAddress> getClientAddresses(@ApiParam("Client ID") @PathVariable("client_id") String id) {
    return addressService.getClientAddresses(id);
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
    return addressService.updateClientAddress(clientAddress);
  }

  @PutMapping(value = "/addresses/{address_id}",
      consumes= MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Update address by address id")
  @ExecutionTimer
  public Address updateAddress(@RequestBody Address address,
      @ApiParam("Address ID") @PathVariable("address_id") String id) {
    address.setIdentifier(id);
    return addressService.updateAddress(address);
  }

}
