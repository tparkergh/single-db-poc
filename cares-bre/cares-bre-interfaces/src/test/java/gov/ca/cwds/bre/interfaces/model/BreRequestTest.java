package gov.ca.cwds.bre.interfaces.model;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BreRequestTest {

  @Test
  public void shouldCreateObjectUsingDefaultConstructor() throws Exception {
    BreRequest def = new BreRequest();
    assertThat("BreRequest is not null", def, notNullValue());
  }

  @Test
  public void shouldSetGetRuleSetName() throws Exception {
    String ruleSetName = "rule set name";
    BreRequest def = new BreRequest();
    def.setBusinessRuleSetName(ruleSetName);
    assertEquals(def.getBusinessRuleSetName(), ruleSetName);
  }

  @Test
  public void shouldSetGetDataObjects() throws Exception {
    List<BreRequestData> dataObjects = new ArrayList<>();

    String name = "data object class name";
    String json = "{ \"f1\" : \"v1\" } ";
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(json);
    BreRequestData responseData = new BreRequestData();
    responseData.setDataObject(jsonNode);
    responseData.setDataObjectClassName(name);

    dataObjects.add(responseData);

    BreRequest def = new BreRequest();
    def.setDataObjects(dataObjects);
    assertEquals(def.getDataObjects(), dataObjects);

  }

}
