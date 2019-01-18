package gov.ca.cwds.cares.rest.controller;

import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.services.interfaces.api.AddressService;
import gov.ca.cwds.cares.services.interfaces.model.Address;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
