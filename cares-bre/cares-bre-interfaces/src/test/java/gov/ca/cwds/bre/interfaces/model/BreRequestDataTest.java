package gov.ca.cwds.bre.interfaces.model;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BreRequestDataTest {

  @Test
  public void shouldConstructUsingDefaultConstructor() throws Exception {
    BreRequestData req = new BreRequestData();
    assertThat(req, notNullValue());
  }

  @Test
  public void shouldSetGetDataObjectClassName() throws Exception {
    BreRequestData req = new BreRequestData();
    String objectName = "data object name";
    req.setDataObjectClassName(objectName);
    assertEquals(req.getDataObjectClassName(), objectName);
  }

  @Test
  public void shouldSetGetDataObject() throws Exception {
    String json = "{ \"f1\" : \"v1\" } ";
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(json);
    BreRequestData req = new BreRequestData();
    req.setDataObject(jsonNode);
    assertEquals(req.getDataObject(), jsonNode);
  }
}
