package gov.ca.cwds.cares.services.service;

import gov.ca.cwds.cares.services.interfaces.api.SystemCodeService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.common.exception.CicsUpdateException;
import gov.ca.cwds.cares.geo.api.GeoService;
import gov.ca.cwds.cares.geo.model.GeoAddress;
import gov.ca.cwds.cares.services.interfaces.api.AddressService;
import gov.ca.cwds.cares.services.interfaces.model.Address;
import gov.ca.cwds.cares.services.mapping.AddressMapper;
import gov.ca.cwds.cics.address.CicsAddressUpdaterRestApiClient;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.DfhCommArea;
import gov.ca.cwds.cics.model.address.AddressData;
import gov.ca.cwds.cics.model.address.CicsAddressRequest;

/**
 * CWDS J Team
 */
@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  @Qualifier("GeoRestClient")
  private GeoService geoService;

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
}
