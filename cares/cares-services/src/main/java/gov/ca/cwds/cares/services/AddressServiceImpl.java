package gov.ca.cwds.cares.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.api.BusinessRuleService;
import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreRequestData;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.common.exception.CicsUpdateException;
import gov.ca.cwds.cares.geo.api.GeoService;
import gov.ca.cwds.cares.geo.model.GeoAddress;
import gov.ca.cwds.cares.interfaces.api.AddressService;
import gov.ca.cwds.cares.interfaces.api.SystemCodeService;
import gov.ca.cwds.cares.interfaces.model.Address;
import gov.ca.cwds.cares.services.mapping.AddressMapper;
import gov.ca.cwds.cics.model.AddressData;
import gov.ca.cwds.cics.model.CicsAddressRequest;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.DfhCommArea;
import gov.ca.cwds.cics.restclient.CicsAddressUpdaterRestApiClient;
import gov.ca.cwds.rest.exception.IssueDetails;

/**
 * CWDS J Team
 */
@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  protected ObjectMapper jacksonObjectMapper;
  
  @Autowired
  @Qualifier("GeoRestClient")
  private GeoService geoService;
  
  @Autowired
  @Qualifier("BreRestApiClient")
  private BusinessRuleService businessRuleService;

  @Autowired
  @Qualifier("CicsAddressUpdaterRestApiClient")
  private CicsAddressUpdaterRestApiClient cicsAddressUpdaterRestApiClient;

  @Autowired
  SystemCodeService systemCodeService;

  @Override
  @ExecutionTimer
  public Address updateAddress(Address address) {
    enrichGeoLocation(address);

    AddressData addressData = AddressMapper.INSTANCE.mapToAddressData(address);
    
    executeBusinessRules(addressData);
    
    CicsAddressRequest cicsAddressRequest = new CicsAddressRequest();
    cicsAddressRequest.setAddressData(addressData);
    doCicsUpdateClientAddress(cicsAddressRequest, address.getLastUpdateTimestamp());
    return address;
  }
  
  @Override
  public Address enrichGeoLocation(Address address) {
    Address enrichedAddress = null;
    if (address != null) {
      enrichedAddress = address;
      GeoAddress geoAddress = new GeoAddress();
      geoAddress.setStreetAddress(String.format("%s %s", address.getStreetNumber(), address.getStreetName()));
      geoAddress.setCity(address.getCity());
      Optional.ofNullable(systemCodeService.getSystemCodeById(address.getStateCode())).ifPresent(
          s -> geoAddress.setState(s.getUserDefinedLogicalId())
      );
      geoAddress.setZip(String.valueOf(address.getZipCode()));
      List<GeoAddress> geoAddresses = geoService.validateAddress(geoAddress);
      if (geoAddresses.size() != 0) {
        GeoAddress firstGeoAddress = geoAddresses.get(0);
        address.setLongitude(firstGeoAddress.getLongitude());
        address.setLatitude(firstGeoAddress.getLattitude());
      }
    }
    return enrichedAddress;
  }

  private CicsResponse doCicsUpdateClientAddress(CicsAddressRequest cicsAddressRequest, LocalDateTime lastUpdateTimestamp) {
    CicsResponse cicsAddressResponse = cicsAddressUpdaterRestApiClient.updateAddress(cicsAddressRequest, lastUpdateTimestamp);
    DfhCommArea dfhCommArea = cicsAddressResponse.getDfhCommArea();
    if (0 != dfhCommArea.getProgReturnCode()) {
      String message = String.format("Cannot update address. Error code %s. Message: %s%s ",
          dfhCommArea.getErrorMsgCode(), dfhCommArea.getErrorMsgPart1(), dfhCommArea.getErrorMsgPart2());
      throw new CicsUpdateException(message);
    }
    return cicsAddressResponse;
  }
  
  private void executeBusinessRules(AddressData addressData) {
    BreRequestData breRequestData = new BreRequestData();
    breRequestData.setDataObjectClassName(AddressData.class.getName());
    breRequestData.setDataObject(jacksonObjectMapper.convertValue(addressData, JsonNode.class));
    
    BreRequest breRequest = new BreRequest();
    breRequest.setBusinessRuleSetName("AddressBusinessRules");
    breRequest.addDataObject(breRequestData);
    
    BreResponse breResponse = businessRuleService.executeBusinessRules(breRequest);
    Set<IssueDetails> issues = breResponse.getIssues();
    
    if (issues != null && !issues.isEmpty()) {
      BreException breException = new BreException("BRE issues found executing business rule set: " + breResponse.getBusinessRuleSetName());
      breException.setBreResponse(breResponse);
      throw breException;
    }
  }
}
