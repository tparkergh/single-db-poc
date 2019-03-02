package gov.ca.cwds.bre.interfaces.model;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.rest.exception.IssueDetails;

public class BreResponseTest {

  @Test
  public void shouldCreateObjectUsingDefaultßConstructor() throws Exception {
    BreResponse rep = new BreResponse();
    assertThat(rep, notNullValue());
  }

  @Test
  public void shouldSetGetRuleSetName() throws Exception {
    BreResponse rep = new BreResponse();
    String ruleSetName = "rule set name";
    rep.setBusinessRuleSetName(ruleSetName);
    assertEquals(rep.getBusinessRuleSetName(), ruleSetName);

  }

  @Test
  public void shouldSetGetExecutionTime() throws Exception {
    Long time = 123455L;
    BreResponse rep = new BreResponse();
    rep.setExecutionTimeMillis(time);
    assertEquals(rep.getExecutionTimeMillis(), time);
  }

  @Test
  public void shouldSetGetIssues() throws Exception {
    Set<IssueDetails> issues = new HashSet<>();
    IssueDetails issue = new IssueDetails();
    issue.setCode("issues code");
    issue.setUserMessage("user message");
    issues.add(issue);
    BreResponse rep = new BreResponse();
    rep.setIssues(issues);
    assertEquals(rep.getIssues(), issues);
  }

  @Test
  public void shouldSetGetDataObjects() throws Exception {
    List<BreResponseData> dataObjects = new ArrayList<>();
    BreResponseData dataObject = new BreResponseData();
    String json = "{ \"f1\" : \"v1\" } ";
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(json);
    dataObject.setDataObject(jsonNode);
    dataObjects.add(dataObject);

    BreResponse rep = new BreResponse();
    rep.setDataObjects(dataObjects);
    assertEquals(rep.getDataObjects(), dataObjects);
  }
}
