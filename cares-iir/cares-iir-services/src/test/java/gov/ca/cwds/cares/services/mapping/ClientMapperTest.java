package gov.ca.cwds.cares.services.mapping;

import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cares.interfaces.model.people.Client;
import gov.ca.cwds.cares.persistence.entity.ClientEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class ClientMapperTest {

  @Test
  public void shouldMapClientEntityToClient(){
    ClientEntity clientEntity = new ClientEntity();
    clientEntity.setIdentifier("zzSS2134");
    clientEntity.setBirthDate(LocalDate.of(1990,10,25));
    clientEntity.setChildClientIndicator("02");
    clientEntity.setClientIndexNumber("03");
    clientEntity.setGenderIdentityType(04);
    clientEntity.setGenderExpressionType(05);
    clientEntity.setSexualOrientationType(06);
    clientEntity.setCommonFirstName("George");
    clientEntity.setCommonLastName("Jackson");
    clientEntity.setCommonMiddleName("");
    clientEntity.setCreationDate(LocalDate.now());
    clientEntity.setDriverLicenseNumber("A12345");
    clientEntity.setEmailAddress("gjackson@isp.com");
    clientEntity.setEstimatedDobCode("07");
    clientEntity.setGenderCode("08");
    clientEntity.setNameType(9);
    clientEntity.setSocialSeccurityNumber("234432345");
    clientEntity.setSuffixTitleDescription("title");
    clientEntity.setSoUnableToDetermineCode("10");

    Client client = ClientMapper.INSTANCE.mapToClient(clientEntity);
    assertEquals(clientEntity.getIdentifier(), client.getIdentifier());
    assertEquals(clientEntity.getBirthDate(), client.getBirthDate());
    assertEquals(clientEntity.getChildClientIndicator(), client.getChildClientIndicator());
    assertEquals(clientEntity.getClientIndexNumber(), client.getClientIndexNumber());
    assertEquals(clientEntity.getGenderIdentityType(), client.getGenderIdentityType());
    assertEquals(clientEntity.getGenderExpressionType(), client.getGenderExpressionType());
    assertEquals(clientEntity.getSexualOrientationType(), client.getSexualOrientationType());
    assertEquals(clientEntity.getCommonFirstName(), client.getCommonFirstName());
    assertEquals(clientEntity.getCommonLastName(), client.getCommonLastName());
    assertEquals(clientEntity.getCommonMiddleName(), client.getCommonMiddleName());
    assertEquals(clientEntity.getCreationDate(), client.getCreationDate());
    assertEquals(clientEntity.getDriverLicenseNumber(), client.getDriverLicenseNumber());
    assertEquals(clientEntity.getEmailAddress(), client.getEmailAddress());
    assertEquals(clientEntity.getEstimatedDobCode(), client.getEstimatedDobCode());
    assertEquals(clientEntity.getGenderCode(), client.getGenderCode());
    assertEquals(clientEntity.getNameType(), client.getNameType());
    assertEquals(clientEntity.getSocialSeccurityNumber(), client.getSocialSeccurityNumber());
    assertEquals(clientEntity.getSuffixTitleDescription(), client.getSuffixTitleDescription());
    assertEquals(clientEntity.getSoUnableToDetermineCode(), client.getSoUnableToDetermineCode());
  }

  @Test
  public void shouldMapCollectionOfClientEntitiesToCollectionOfClients(){
    ClientEntity clientEntity1 = buildClientEntity("zzSS2134", 1990, 10, 25, "George", "Jackson");
    ClientEntity clientEntity2 = buildClientEntity("as2343", 1989, 9, 15, "Larry", "Cash");
    List clientEntities = new ArrayList();
    clientEntities.add(clientEntity1);
    clientEntities.add(clientEntity2);
    Collection<Client> clients = ClientMapper.INSTANCE.mapToClients(clientEntities);

    Map<String, Client> clientsById = new HashMap<String, Client>();
    clients.forEach((n) -> clientsById.put(n.getIdentifier(), n));

    Client client1 = clientsById.get(clientEntity1.getIdentifier());
    assertEquals(clientEntity1.getIdentifier(), client1.getIdentifier());
    assertEquals(clientEntity1.getBirthDate(), client1.getBirthDate());
    assertEquals(clientEntity1.getCommonFirstName(), client1.getCommonFirstName());
    assertEquals(clientEntity1.getCommonLastName(), client1.getCommonLastName());

    Client client2 = clientsById.get(clientEntity2.getIdentifier());
    assertEquals(clientEntity2.getIdentifier(), client2.getIdentifier());
    assertEquals(clientEntity2.getBirthDate(), client2.getBirthDate());
    assertEquals(clientEntity2.getCommonFirstName(), client2.getCommonFirstName());
    assertEquals(clientEntity2.getCommonLastName(), client2.getCommonLastName());
  }

  private ClientEntity buildClientEntity(String zzSS2134, int i, int i2, int i3, String george,
      String jackson) {
    ClientEntity clientEntity = new ClientEntity();
    clientEntity.setIdentifier(zzSS2134);
    clientEntity.setBirthDate(LocalDate.of(i, i2, i3));
    clientEntity.setCommonFirstName(george);
    clientEntity.setCommonLastName(jackson);
    return clientEntity;
  }
}
