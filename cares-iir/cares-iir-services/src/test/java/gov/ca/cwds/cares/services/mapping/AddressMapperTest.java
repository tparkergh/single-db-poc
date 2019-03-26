package gov.ca.cwds.cares.services.mapping;

import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cares.interfaces.model.Address;
import gov.ca.cwds.cics.model.AddressData;
import org.junit.Before;
import org.junit.Test;

public class AddressMapperTest {

  private Address address;

  @Before
  public void setup(){
    address = new Address();
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
  }

  @Test
  public void shouldMapAddress(){
    AddressData data = AddressMapper.INSTANCE.mapToAddressData(address);

    assertEquals(address.getIdentifier(), data.getIdentifier());
    assertEquals(address.getCity(), data.getCity());
    assertEquals(address.getEmergencyPhoneNumber(), data.getEmergencyPhoneNumber());
    assertEquals(address.getEmergencyPhoneExtension().longValue(), data.getEmergencyPhoneExtension().longValue());
    assertEquals(address.getForeignAddressIndicator(), data.getForeignAddressIndicator());
    assertEquals(address.getGovernmentEntityCode(), data.getGovernmentEntityCode());
    assertEquals(address.getMessagePhoneNumber(), data.getMessagePhoneNumber());
    assertEquals(address.getMessagePhoneExtension().longValue(), data.getMessagePhoneExtension().longValue());
    assertEquals(address.getAddressHeader(), data.getAddressHeader());
    assertEquals(address.getPrimaryPhoneNumber(), data.getPrimaryPhoneNumber());
    assertEquals(address.getPrimaryPhoneExtension().longValue(), data.getPrimaryPhoneExtension().longValue());
    assertEquals(address.getStateCode(), data.getStateCode());
    assertEquals(address.getStreetName(), data.getStreetName());
    assertEquals(address.getStreetNumber(), data.getStreetNumber());
    assertEquals(address.getZipCode(), data.getZipCode());
    assertEquals(address.getAddressDescription(), data.getAddressDescription());
    assertEquals(address.getZipSuffix(), data.getZipSuffix());
    assertEquals(address.getPostStreetDirection(), data.getPostStreetDirection());
    assertEquals(address.getPreStreetDirection(), data.getPreStreetDirection());
    assertEquals(address.getStreetType(), data.getStreetType());
    assertEquals(address.getUnitType(), data.getUnitType());
    assertEquals(address.getUnitNumber(), data.getUnitNumber());
    assertEquals(address.getLatitude(), data.getLatitude());
    assertEquals(address.getLongitude(), data.getLongitude());
  }

}
