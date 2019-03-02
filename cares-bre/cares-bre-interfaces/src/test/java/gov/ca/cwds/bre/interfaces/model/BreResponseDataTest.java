package gov.ca.cwds.bre.interfaces.model;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.io.IOException;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BreResponseDataTest {

  @Test
  public void shouldCreateObjectUsingDefaultConstructor() throws Exception {
    BreResponseData rep = new BreResponseData();
    assertThat(rep, notNullValue());
  }

  @Test
  public void shouldSetGetDataObjectClassName() throws Exception {
    String name = "data object class name";
    BreResponseData rep = new BreResponseData();
    rep.setDataObjectClassName(name);
    assertEquals(rep.getDataObjectClassName(), name);
  }

  @Test
  public void shouldSetGetDataObject() throws JsonProcessingException, IOException {
    String json = "{ \"f1\" : \"v1\" } ";
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(json);
    BreResponseData rep = new BreResponseData();
    rep.setDataObject(jsonNode);
    assertEquals(rep.getDataObject(), jsonNode);
  }

}
