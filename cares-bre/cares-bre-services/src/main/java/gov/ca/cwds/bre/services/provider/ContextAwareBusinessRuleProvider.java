package gov.ca.cwds.bre.services.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDocumentation;
import gov.ca.cwds.bre.services.api.BusinessRule;
import gov.ca.cwds.bre.services.api.BusinessRuleProvider;

/**
 * @author CWDS J Team
 */
@Component("ContextAwareBusinessRuleProvider")
public class ContextAwareBusinessRuleProvider implements BusinessRuleProvider, ApplicationContextAware {

  private static ApplicationContext APPLICATION_CONTEXT;
  
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    APPLICATION_CONTEXT = applicationContext;
  }
  
  @Override
  public BusinessRule getBusinessRule(String name) {
    return APPLICATION_CONTEXT.getBean(name, BusinessRule.class);        
  }

  @Override
  public List<BusinessRuleDefinition> getBusinessRuleDefinitions() {
    List<BusinessRuleDefinition> businessRuleDescriptions = new ArrayList<>();
    Map<String, BusinessRule> businessRules = APPLICATION_CONTEXT.getBeansOfType(BusinessRule.class);
    
    if (businessRules != null) {
      for (BusinessRule br : businessRules.values()) {
        businessRuleDescriptions.add(br.getDefinition());
      }
    }
    
    return businessRuleDescriptions;
  }

  @Override
  public BusinessRuleDefinition getBusinessRuleDefinition(String name) {
    return getBusinessRule(name).getDefinition();
  }
  
}
