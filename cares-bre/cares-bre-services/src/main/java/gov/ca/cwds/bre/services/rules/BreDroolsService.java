package gov.ca.cwds.bre.services.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.springframework.stereotype.Component;
import gov.ca.cwds.bre.interfaces.model.RuleDocumentation;
import gov.ca.cwds.drools.DroolsConfiguration;
import gov.ca.cwds.drools.DroolsException;
import gov.ca.cwds.drools.DroolsService;

@Component("BreDroolsService")
public class BreDroolsService extends DroolsService {
  
  @SuppressWarnings("rawtypes")
  public List<RuleDocumentation> getRuleDocuments(
      DroolsConfiguration config, 
      String kbase) throws DroolsException {
    List<RuleDocumentation> ruleDocs = new ArrayList<>();
    KieContainer container = config.getKieContainer();  
    Collection<KiePackage> packages = container.getKieBase(kbase).getKiePackages();
    
    for (KiePackage pkg : packages) {
      Collection<Rule> rules = pkg.getRules();
      
      for (Rule rule :rules) {
        Map<String, Object> ruleMeta = rule.getMetaData();
        RuleDocumentation ruleDoc = new RuleDocumentation();
        ruleDoc.setName(rule.getName());
                        
        for (String ruleMetaKey : ruleMeta.keySet()) {
          Object ruleMetaObj = ruleMeta.get(ruleMetaKey);
          
          if (ruleMetaObj != null 
              && ruleMetaObj instanceof String 
              && StringUtils.isNotBlank(ruleMetaObj.toString())) {            
            String ruleMetaValue = (String) ruleMetaObj;
            
            if (ruleMetaKey.startsWith("doc_")) {
              ruleDoc.addDocumentation(ruleMetaKey, ruleMetaValue);
            }
          }
        }
                  
        ruleDocs.add(ruleDoc);
      }
    }
    return ruleDocs;    
  }
}
