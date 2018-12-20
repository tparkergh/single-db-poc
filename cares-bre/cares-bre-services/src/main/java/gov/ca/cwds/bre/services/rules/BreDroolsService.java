package gov.ca.cwds.bre.services.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.springframework.stereotype.Component;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition.Rule.Type;
import gov.ca.cwds.drools.DroolsConfiguration;
import gov.ca.cwds.drools.DroolsException;
import gov.ca.cwds.drools.DroolsService;

@Component("BreDroolsService")
public class BreDroolsService extends DroolsService {
  
  @SuppressWarnings("rawtypes")
  public List<BusinessRuleDefinition.Rule> getRules(DroolsConfiguration config, String kbase) throws DroolsException {
    List<BusinessRuleDefinition.Rule> ruleDefs = new ArrayList<>();    
    KieContainer container = config.getKieContainer();  
    Collection<KiePackage> packages = container.getKieBase(kbase).getKiePackages();
    
    for (KiePackage pkg : packages) {
      Collection<Rule> rules = pkg.getRules();
      
      for (Rule rule : rules) {
        Map<String, Object> ruleMeta = rule.getMetaData();
        Object typeObj = ruleMeta.get("rule_type");        
        Object descriptionObj = ruleMeta.get("rule_description");
        String name = rule.getName();
        
        String type = typeObj != null ? (String) typeObj : "";        
        String description = descriptionObj != null ? (String) descriptionObj : "";
        
        ruleDefs.add(new BusinessRuleDefinition.Rule(name, Type.fromName(type), description));
      }      
    }
    
    return ruleDefs;
  }
}
