package gov.ca.cwds.cares.services.service;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.common.exception.CicsUpdateException;
import gov.ca.cwds.cares.geo.api.GeoService;
import gov.ca.cwds.cares.geo.model.GeoAddress;
import gov.ca.cwds.cares.persistence.entity.ClientAddressEntity;
import gov.ca.cwds.cares.persistence.repository.ClientAddressRepository;
import gov.ca.cwds.cares.services.interfaces.api.AddressService;
import gov.ca.cwds.cares.services.interfaces.model.Address;
import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;
import gov.ca.cwds.cares.services.mapping.AddressMapper;
import gov.ca.cwds.cares.services.mapping.ClientAddressMapper;
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
  private ClientAddressRepository clientAddressRepository;

  @Autowired
  @Qualifier("GeoRestClient")
  private GeoService geoService;

  @Autowired
  @Qualifier("CicsAddressUpdaterRestApiClient")
  private CicsAddressUpdaterRestApiClient cicsAddressUpdaterRestApiClient;

  @Override
  @ExecutionTimer
  public Collection<ClientAddress> getClientAddresses(String clientId) {
    Collection<ClientAddressEntity> clientAddressEntities = clientAddressRepository.findByClientId(clientId);
    Collection<ClientAddress> clientAddresses = ClientAddressMapper.INSTANCE.mapToClientAddresses(clientAddressEntities);

    for (ClientAddress clientAddress : clientAddresses) {
      enrichLocation(clientAddress.getAddress());
    }
    return clientAddresses;
  }

  @Override
  public ClientAddress updateClientAddress(ClientAddress clientAddress) {
    enrichLocation(clientAddress.getAddress());

    CicsAddressRequest cicsAddressRequest = ClientAddressMapper.INSTANCE.mapToCicsAddressRequest(clientAddress);
    doCicsUpdateClientAddress(cicsAddressRequest);
    return clientAddress;
  }

  @Override
  public Address updateAddress(Address address) {
    enrichLocation(address);

    AddressData addressData = AddressMapper.INSTANCE.mapToAddressData(address);
    CicsAddressRequest cicsAddressRequest = new CicsAddressRequest();
    cicsAddressRequest.setAddressData(addressData);
    doCicsUpdateClientAddress(cicsAddressRequest);
    return address;
  }

  private CicsResponse doCicsUpdateClientAddress(CicsAddressRequest cicsAddressRequest) {
    CicsResponse cicsAddressResponse = cicsAddressUpdaterRestApiClient.updateAddress(cicsAddressRequest);
    DfhCommArea dfhCommArea = cicsAddressResponse.getDfhCommArea();
    if (0 != dfhCommArea.getProgReturnCode()) {
      String message = String.format("Cannot update address. Error code %s. Message: %s%s ",
          dfhCommArea.getErrorMsgCode(), dfhCommArea.getErrorMsgPart1(), dfhCommArea.getErrorMsgPart2());
      throw new CicsUpdateException(message);
    }
    return cicsAddressResponse;
  }

  private void enrichLocation(Address address) {
    GeoAddress geoAddress = new GeoAddress();
    geoAddress.setStreetAddress(String.format("%s %s", address.getStreetNumber(), address.getStreetName()));
    geoAddress.setCity(address.getCity());
    geoAddress.setState("CA"); //hardcoded for now. system code service is coming
    geoAddress.setZip(String.valueOf(address.getZipCode()));
    List<GeoAddress> geoAddresses = geoService.validateAddress(geoAddress);
    if (geoAddresses.size() != 0) {
      GeoAddress firstGeoAddress = geoAddresses.get(0);
      address.setLongitude(firstGeoAddress.getLongitude());
      address.setLatitude(firstGeoAddress.getLattitude());
    }
  }

}
