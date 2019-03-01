package gov.ca.cwds.bre.interfaces.model;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BusinessRuleSetDefinitionTest {

  @Test
  public void shouldCreateObjectUsingDefaultßConstructor() throws Exception {
    BusinessRuleSetDefinition rep = new BusinessRuleSetDefinition();
    assertThat(rep, notNullValue());
  }

  @Test
  public void shouldSetGetRuleSetName() throws Exception {
    String ruleSetName = "rule set name";
    BusinessRuleSetDefinition rep = new BusinessRuleSetDefinition();
    rep.setBusinessRuleSetName(ruleSetName);
    assertEquals(rep.getBusinessRuleSetName(), ruleSetName);
  }

  @Test
  public void shouldSetGetRules() throws Exception {
    List<RuleDefinition> rules = new ArrayList<>();
    RuleDefinition rule = new RuleDefinition();
    String ruleName = "rule name";
    String json = "{ \"f1\" : \"v1\" } ";
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(json);
    rule.setName(ruleName);
    rule.setLogic(jsonNode);
    rules.add(rule);

    BusinessRuleSetDefinition rep = new BusinessRuleSetDefinition();
    rep.setRules(rules);
    assertEquals(rep.getRules(), rules);

  }
}
