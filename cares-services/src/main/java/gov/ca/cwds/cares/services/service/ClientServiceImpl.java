package gov.ca.cwds.cares.services.service;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.persistence.entity.ClientEntity;
import gov.ca.cwds.cares.persistence.repository.ClientRepository;
import gov.ca.cwds.cares.services.interfaces.api.AddressService;
import gov.ca.cwds.cares.services.interfaces.api.ClientService;
import gov.ca.cwds.cares.services.interfaces.model.Client;
import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;
import gov.ca.cwds.cares.services.mapping.ClientMapper;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private AddressService addressService;

  @Override
  @ExecutionTimer
  public Client getClient(String clientId) {
    ClientEntity clientEntity = clientRepository.findById(clientId).get();
    return ClientMapper.INSTANCE.mapToClient(clientEntity);
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
  @ExecutionTimer
  public Collection<ClientAddress> getClientAddresses(String clientId) {
    return addressService.getClientAddresses(clientId);
  }

  @Override
  @ExecutionTimer
  public ClientAddress updateClientAddress(ClientAddress clientAddress) {
    return addressService.updateClientAddress(clientAddress);
  }
}