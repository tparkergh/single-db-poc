package gov.ca.cwds.bre.services.provider;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDocumentation;
import gov.ca.cwds.bre.services.api.BusinessRule;
import gov.ca.cwds.bre.services.api.BusinessRuleDocumentationProvider;

/**
 * @author CWDS J Team
 */
@Component("ContextAwareBusinessRuleDocumentationProvider")
public class ContextAwareBusinessRuleDocumentationProvider implements BusinessRuleDocumentationProvider, ApplicationContextAware {

  private static ApplicationContext APPLICATION_CONTEXT;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    APPLICATION_CONTEXT = applicationContext;
  }

  @Override
  public BusinessRule getBusinessRuleDocument(String name) {    
    return APPLICATION_CONTEXT.getBean(name, BusinessRule.class);    
  }

  @Override
  public BusinessRuleSetDocumentation getBusinessRuleDocumentation(String name) {
    return getBusinessRuleDocument(name).getDocumentation();
   }
 
}
