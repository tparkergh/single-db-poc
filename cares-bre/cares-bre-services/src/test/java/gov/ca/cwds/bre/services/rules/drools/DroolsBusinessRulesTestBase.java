package gov.ca.cwds.bre.services.rules.drools;

import org.junit.Before;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreRequestData;
import gov.ca.cwds.bre.services.rules.BreDroolsService;
import gov.ca.cwds.bre.services.rules.DroolsBusinessRuleBase;
import gov.ca.cwds.cics.model.ReporterData;

public abstract class DroolsBusinessRulesTestBase {
  
  private DroolsBusinessRuleBase droolsBusinessRules;
  
  @Before
  public void init() {
    droolsBusinessRules = createDroolsBusinessRules();
    BreDroolsService breDroolsService = new BreDroolsService();
    ObjectMapper mapper = new ObjectMapper();
    
    droolsBusinessRules.setBreDroolsService(breDroolsService);
    droolsBusinessRules.setJacksonObjectMapper(mapper);    
  }

  protected abstract DroolsBusinessRuleBase createDroolsBusinessRules();

  protected DroolsBusinessRuleBase getDroolsBusinessRules() {
    return droolsBusinessRules;
  }
  
  protected BreRequest createBreRequest(String businessRuleSetName, Object data) {
    BreRequest breRequest = new BreRequest();
    breRequest.setBusinessRuleSetName(businessRuleSetName);
    
    BreRequestData breRequestData = new BreRequestData();
    breRequestData.setDataObjectClassName(ReporterData.class.getName());
        
    ObjectMapper mapper = new ObjectMapper();
    breRequestData.setDataObject(mapper.convertValue(data, JsonNode.class));
    
    breRequest.addDataObject(breRequestData);
    
    return breRequest;
  }
}
