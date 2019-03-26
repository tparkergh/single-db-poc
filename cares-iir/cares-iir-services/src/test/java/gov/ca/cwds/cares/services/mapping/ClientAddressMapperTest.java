package gov.ca.cwds.cares.services.mapping;

import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cares.interfaces.model.Address;
import gov.ca.cwds.cares.interfaces.model.people.ClientAddress;
import gov.ca.cwds.cares.persistence.entity.AddressEntity;
import gov.ca.cwds.cares.persistence.entity.ClientAddressEntity;
import gov.ca.cwds.cics.model.CicsAddressRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class ClientAddressMapperTest {
  Address address;
  ClientAddress clientAddress;

  @Before
  public void setup(){
    address = new Address();
    address.setStreetName("Main st");
    address.setIdentifier("ZXCQWE");
    address.setCity("Sacramento");
    address.setEmergencyPhoneNumber(5551234321L);
    address.setEmergencyPhoneExtension(6543);
    address.setForeignAddressIndicator("01");
    address.setGovernmentEntityCode(02);
    address.setMessagePhoneNumber(5556789876L);
    address.setMessagePhoneExtension(8765);
    address.setAddressHeader("Header");
    address.setPrimaryPhoneNumber(5552341234L);
    address.setPrimaryPhoneExtension(9987);
    address.setStateCode(03);
    address.setStreetName("Oak St");
    address.setStreetNumber("342");
    address.setZipCode(12334);
    address.setAddressDescription("home");
    address.setZipSuffix(8796);
    address.setPostStreetDirection("postStreet");
    address.setPreStreetDirection("PreStreet");
    address.setStreetType(04);
    address.setUnitType(05);
    address.setUnitNumber("65432");
    address.setLatitude(2345456789D);
    address.setLongitude(6543267865D);

    clientAddress = new ClientAddress();
    clientAddress.setAddress(address);
  }

  @Test
  public void shouldMapAddressInClientAddresstoAddressInCicsAddressRequest(){
    CicsAddressRequest cicsAddress = ClientAddressMapper.INSTANCE.mapToCicsAddressRequest(clientAddress);

    assertEquals(address.getIdentifier(), cicsAddress.getAddressData().getIdentifier());
    assertEquals(address.getStateCode(), cicsAddress.getAddressData().getStateCode());
    assertEquals(address.getZipCode(), cicsAddress.getAddressData().getZipCode());
    assertEquals(address.getZipSuffix(), cicsAddress.getAddressData().getZipSuffix());
    assertEquals(address.getCity(), cicsAddress.getAddressData().getCity());
    assertEquals(address.getStreetName(), cicsAddress.getAddressData().getStreetName());
    assertEquals(address.getStreetType(), cicsAddress.getAddressData().getStreetType());
    assertEquals(address.getStreetNumber(), cicsAddress.getAddressData().getStreetNumber());
    assertEquals(address.getUnitNumber(), cicsAddress.getAddressData().getUnitNumber());
    assertEquals(address.getUnitType(), cicsAddress.getAddressData().getUnitType());
    assertEquals(address.getAddressDescription(), cicsAddress.getAddressData().getAddressDescription());
    assertEquals(address.getPostStreetDirection(), cicsAddress.getAddressData().getPostStreetDirection());
    assertEquals(address.getPreStreetDirection(), cicsAddress.getAddressData().getPreStreetDirection());
    assertEquals(address.getEmergencyPhoneNumber(), cicsAddress.getAddressData().getEmergencyPhoneNumber());
    assertEquals(address.getEmergencyPhoneExtension().longValue(), cicsAddress.getAddressData().getEmergencyPhoneExtension().longValue());
    assertEquals(address.getMessagePhoneNumber(), cicsAddress.getAddressData().getMessagePhoneNumber());
    assertEquals(address.getMessagePhoneExtension().longValue(), cicsAddress.getAddressData().getMessagePhoneExtension().longValue());
    assertEquals(address.getPrimaryPhoneNumber(), cicsAddress.getAddressData().getPrimaryPhoneNumber());
    assertEquals(address.getPrimaryPhoneExtension().longValue(), cicsAddress.getAddressData().getPrimaryPhoneExtension().longValue());
    assertEquals(address.getGovernmentEntityCode(), cicsAddress.getAddressData().getGovernmentEntityCode());
    assertEquals(address.getForeignAddressIndicator(), cicsAddress.getAddressData().getForeignAddressIndicator());
    assertEquals(address.getLongitude(), cicsAddress.getAddressData().getLongitude());
    assertEquals(address.getLatitude(), cicsAddress.getAddressData().getLatitude());
  }

  @Test
  public void shouldMapCollectionOfSavedClientAddressesToACollectionOfClientAddresses(){
    AddressEntity address1 = buildAddressEntity("13", "Elm", "Los Angeles", 65432);
    List<ClientAddressEntity> savedClientAddresses = buildClientAddress(address1);

    AddressEntity address2 = buildAddressEntity("24", "Oak", "San Francisco", 87675);
    savedClientAddresses.addAll(buildClientAddress(address2));


    Collection<ClientAddress> clientAddress = ClientAddressMapper.INSTANCE.mapToClientAddresses(savedClientAddresses);

    Map<Integer, ClientAddress> addresses = mapClientAddressEntityByZipCode(clientAddress);

    ClientAddress clientAddress1 = addresses.get(address1.getZipCode());
    assertEquals(address1.getStreetNumber(), clientAddress1.getAddress().getStreetNumber());
    assertEquals(address1.getStreetName(), clientAddress1.getAddress().getStreetName());
    assertEquals(address1.getCity(), clientAddress1.getAddress().getCity());
    assertEquals(address1.getZipCode(), clientAddress1.getAddress().getZipCode());

    ClientAddress clientAddress2 = addresses.get(address2.getZipCode());
    assertEquals(address2.getStreetNumber(), clientAddress2.getAddress().getStreetNumber());
    assertEquals(address2.getStreetName(), clientAddress2.getAddress().getStreetName());
    assertEquals(address2.getCity(), clientAddress2.getAddress().getCity());
    assertEquals(address2.getZipCode(), clientAddress2.getAddress().getZipCode());
  }

  private AddressEntity buildAddressEntity(String s, String elm, String s2, int i) {
    AddressEntity address1 = new AddressEntity();
    address1.setStreetNumber(s);
    address1.setStreetName(elm);
    address1.setCity(s2);
    address1.setZipCode(i);
    return address1;
  }

  private List<ClientAddressEntity> buildClientAddress(AddressEntity address1) {
    List<ClientAddressEntity> savedClientAddresses = new ArrayList();
    ClientAddressEntity clientAddressEntity = new ClientAddressEntity();
    clientAddressEntity.setAddress(address1);
    savedClientAddresses.add(clientAddressEntity);
    return savedClientAddresses;
  }

  private Map<Integer,ClientAddress > mapClientAddressEntityByZipCode(Collection<ClientAddress> address){
    Map addresses = new HashMap();
    address.forEach((n) -> addresses.put(n.getAddress().getZipCode(), n) );
    return addresses;
  }
}
