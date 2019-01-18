package gov.ca.cwds.cares.services.service;

import java.util.Arrays;
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
import gov.ca.cwds.cares.persistence.entity.ClientEntity;
import gov.ca.cwds.cares.persistence.repository.ClientAddressRepository;
import gov.ca.cwds.cares.persistence.repository.ClientRepository;
import gov.ca.cwds.cares.services.interfaces.api.ClientService;
import gov.ca.cwds.cares.services.interfaces.model.Address;
import gov.ca.cwds.cares.services.interfaces.model.Client;
import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;
import gov.ca.cwds.cares.services.mapping.ClientAddressMapper;
import gov.ca.cwds.cares.services.mapping.ClientMapper;
import gov.ca.cwds.cics.address.CicsAddressUpdaterRestApiClient;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.DfhCommArea;
import gov.ca.cwds.cics.model.address.CicsAddressRequest;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

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
  public Client getClient(String clientId) {
    ClientEntity clientEntity = clientRepository.findById(clientId).get();
    return ClientMapper.INSTANCE.mapToClient(clientEntity);
  }

  @Override
  @ExecutionTimer
  public Collection<ClientAddress> getClientAddresses(String clientId) {
    Collection<ClientAddressEntity> clientAddressEntities = clientAddressRepository.findByClientId(clientId);
    Collection<ClientAddress> clientAddresses = ClientAddressMapper.INSTANCE.mapToClientAddresses(clientAddressEntities);

    for (ClientAddress clientAddress : clientAddresses) {
      enrichLocation(clientAddress);
    }
    return clientAddresses;
  }

  @Override
  @ExecutionTimer
  public Collection<Client> getAllClients() {
    // White listed client ids
    String[] clientIds = {"DDTDeJW05u", "FvdNhQH00T", "FMVyucz00d", "FZLOarC057", "GNCmGiP00d",
        "GT0fTeb00T", "HuZx7NS057", "HDJGm9a00T", "H8c2Qcr00T", "I6WYpiz00T", "MdgDbf700T",
        "RhrNhul00R", "RtpdA9q00T", "B83W9kg057", "SMHmoJl00d", "2D3pOpl00d", "33DlLkn00d",
        "43RKPsw057", "82tFIkz00T", "9d54R1e05u", "9RcYKrB00T", "Ajjgwny057"};
    Collection<ClientEntity> clientEntities =
        clientRepository.findAllById(Arrays.asList(clientIds));
    return ClientMapper.INSTANCE.mapToClients(clientEntities);
  }

  @Override
  public ClientAddress updateClientAddress(ClientAddress clientAddress) {
    CicsAddressRequest cicsAddressRequest = ClientAddressMapper.INSTANCE.mapToCicsAddressRequest(clientAddress);
    CicsResponse cicsAddressResponse = cicsAddressUpdaterRestApiClient.updateAddress(cicsAddressRequest);
    DfhCommArea dfhCommArea = cicsAddressResponse.getDfhCommArea();
    if (0 != dfhCommArea.getProgReturnCode()) {
      String message = String.format("Cannot update address. Error code %s. Message: %s%s ",
          dfhCommArea.getErrorMsgCode(), dfhCommArea.getErrorMsgPart1(), dfhCommArea.getErrorMsgPart2());
      throw new CicsUpdateException(message);
    }
    return clientAddress;
  }

  private void enrichLocation(ClientAddress clientAddress) {
    Address address = clientAddress.getAddress();
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
