package gov.ca.cwds.bre.rest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.api.BusinessRuleService;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BreResponseData;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDefinition;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDocumentation;
import gov.ca.cwds.bre.interfaces.model.RuleDefinition;
import gov.ca.cwds.bre.interfaces.model.RuleDocumentation;
import gov.ca.cwds.rest.exception.IssueDetails;

@RunWith(MockitoJUnitRunner.class)
public class BreRestControllerTest {

  private MockMvc mockMvc;
  private ObjectMapper MAPPER;

  @Mock
  private BusinessRuleService businessRuleService;

  @InjectMocks
  private BreRestController breRestController;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(breRestController).build();
    MAPPER = new ObjectMapper();
  }

  @Test
  public void should_call_getBusinessRuleSetNames() throws Exception {

    Collection<String> expected = new ArrayList<>();
    expected.add("ReporterBusinessRules");
    expected.add("AddressBusinessRules");
    String json = MAPPER.writeValueAsString(expected);

    when(businessRuleService.getAllBusinessRuleSetNames()).thenReturn(expected);

    mockMvc.perform(get("/bre/docs").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().json(json));

  }

  @Test
  public void should_call_getBusinessRuleSetDocumentation() throws Exception {
    String businessRuleSetName = "ReporterBusinessRules";
    String dataClassName = "ReporterData";
    String ruleName = "testRule";
    List<RuleDocumentation> rules = new ArrayList<>();

    TreeMap<String, String> documentation = new TreeMap<>();
    documentation.put("rule1", "test rule");

    RuleDocumentation ruleDocumentation = new RuleDocumentation();
    ruleDocumentation.setName(ruleName);
    ruleDocumentation.setDocumentation(documentation);
    rules.add(ruleDocumentation);

    BusinessRuleSetDocumentation businessRuleSetDocumentation = new BusinessRuleSetDocumentation();
    businessRuleSetDocumentation.setBusinessRuleSetName(businessRuleSetName);
    businessRuleSetDocumentation.setDataClassName(dataClassName);

    businessRuleSetDocumentation.setRules(rules);


    when(businessRuleService.getBusinessRuleSetDocumentation(any()))
        .thenReturn(businessRuleSetDocumentation);

    mockMvc.perform(get("/bre/docs/" + businessRuleSetName).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void should_call_getBusinessRuleDefinition() throws Exception {
    String businessRuleSetName = "ReporterSearchScreenBusinessRules";
    List<RuleDefinition> rules = new ArrayList<>();

    RuleDefinition ruleDefinition = new RuleDefinition();
    ruleDefinition.setName("rule 1");
    ruleDefinition.setLogic(null);
    rules.add(ruleDefinition);

    BusinessRuleSetDefinition setDefinition = new BusinessRuleSetDefinition();
    setDefinition.setBusinessRuleSetName(businessRuleSetName);
    setDefinition.setRules(rules);
    String json = MAPPER.writeValueAsString(setDefinition);

    when(businessRuleService.getBusinessRuleSetDefinition(any())).thenReturn(setDefinition);

    mockMvc.perform(get("/bre/defs/" + businessRuleSetName).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().json(json));
  }

  @Test
  public void should_call_executeBusinessRules() throws Exception {
    BreResponse response = new BreResponse();
    String businessRuleSetName = "ReporterSearchScreenBusinessRules";
    Long executionTime = 1010L;
    Set<IssueDetails> issueDetails = new HashSet<>();
    IssueDetails issue = new IssueDetails();
    issue.setUserMessage("test user message");
    issueDetails.add(issue);
    List<BreResponseData> responseData = new ArrayList<>();
    BreResponseData rs = new BreResponseData();
    rs.setDataObjectClassName("ReporterData");
    String json = "{ \"f1\" : \"v1\" } ";
    JsonNode jsonNode = MAPPER.readTree(json);
    rs.setDataObject(jsonNode);
    responseData.add(rs);

    response.setBusinessRuleSetName(businessRuleSetName);
    response.setDataObjects(responseData);
    response.setExecutionTimeMillis(executionTime);

    when(businessRuleService.executeBusinessRules(any())).thenReturn(response);

    String request = IOUtils.toString(
        getClass().getResource("/fixtures/rules/ReporterBusinessRulesRequest.json"),
        StandardCharsets.UTF_8);

    mockMvc
        .perform(post("/bre/exec").content(request).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
