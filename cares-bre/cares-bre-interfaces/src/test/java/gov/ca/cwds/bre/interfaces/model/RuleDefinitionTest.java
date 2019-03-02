package gov.ca.cwds.bre.interfaces.model;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.io.IOException;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RuleDefinitionTest {

  @Test
  public void shouldCreateObjectUsingDefaultContructor() throws Exception {
    RuleDefinition def = new RuleDefinition();
    assertThat("RuleDefinition is not null", def, notNullValue());
  }

  @Test
  public void shouldSetGetName() {
    String ruleName = "rule name";
    RuleDefinition def = new RuleDefinition();
    def.setName(ruleName);
    assertEquals(def.getName(), ruleName);
  }

  @Test
  public void shouldSetGetLogic() throws JsonProcessingException, IOException {
    String json = "{ \"f1\" : \"v1\" } ";
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(json);
    RuleDefinition def = new RuleDefinition();
    def.setLogic(jsonNode);
    assertEquals(def.getLogic(), jsonNode);
  }

}
