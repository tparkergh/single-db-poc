package gov.ca.cwds.bre.services.rules.presentation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDefinition;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDocumentation;
import gov.ca.cwds.bre.interfaces.model.RuleDefinition;
import gov.ca.cwds.bre.interfaces.model.RuleDocumentation;
import gov.ca.cwds.bre.services.api.BusinessRule;

/**
 * @author CWDS J Team
 */
public class JsonLogicBusinessRuleBase implements BusinessRule {

  private static final String AUTO_DISCOVERY_FILENAME = "auto-discovery";

  @Autowired
  protected ObjectMapper jacksonObjectMapper;

  protected JsonLogicBusinessRuleBase() {
    // Default no-argument constructor
  }

  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }

  @Override
  public BusinessRuleSetDefinition getDefinition() {
    BusinessRuleSetDefinition def = new BusinessRuleSetDefinition();
    def.setBusinessRuleSetName(getName());
    def.setRules(getJsonLogicRuleDefinitions());
    return def;
  }

  @Override
  public BusinessRuleSetDocumentation getDocumentation() {
    BusinessRuleSetDocumentation docs = new BusinessRuleSetDocumentation();
    docs.setBusinessRuleSetName(getName());
    docs.setRules(getJsonLogicRuleDocumenttaion());
    return docs;
  }

  @Override
  public boolean isDefinitionAvailable() {
    return true;
  }

  @Override
  public boolean isExecutionSupported() {
    return false;
  }

  private List<RuleDefinition> getJsonLogicRuleDefinitions() {
    List<RuleDefinition> ruleDefs = new ArrayList<>();
    try {
      for (String resourceName : IOUtils.readLines(getResourceStream(AUTO_DISCOVERY_FILENAME),
          "UTF-8")) {
        if (resourceName.endsWith(".json")) {
          byte[] bytes = IOUtils.toByteArray(getResourceStream(resourceName));
          RuleDefinition ruleDef = this.jacksonObjectMapper.readValue(bytes, RuleDefinition.class);          
          ruleDefs.add(ruleDef);
        }
      }
    } catch (IOException e) {
      throw new BreException(e.getMessage(),e);
    }
    return ruleDefs;
  }
  
  private List<RuleDocumentation> getJsonLogicRuleDocumenttaion() {
    List<RuleDocumentation> docs = new ArrayList<>();
    try {
      for (String resourceName : IOUtils.readLines(getResourceStream(AUTO_DISCOVERY_FILENAME),
          "UTF-8")) {
        if (resourceName.endsWith(".json")) {
          byte[] bytes = IOUtils.toByteArray(getResourceStream(resourceName));
          RuleDocumentation doc = this.jacksonObjectMapper.readValue(bytes, RuleDocumentation.class);          
          docs.add(doc);
        }
      }
    } catch (IOException e) {
      throw new BreException(e.getMessage(),e);
    }
    return docs;
  }

  private InputStream getResourceStream(String resourceName) {
    return Thread.currentThread().getContextClassLoader()
        .getResourceAsStream("rules/jsonlogic/" + getName() + "/" + resourceName);
  }
}
