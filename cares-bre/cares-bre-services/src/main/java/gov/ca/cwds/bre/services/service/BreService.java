package gov.ca.cwds.bre.services.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import gov.ca.cwds.bre.interfaces.api.BusinessRuleService;
import gov.ca.cwds.bre.interfaces.exception.BreException;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDocumentation;
import gov.ca.cwds.bre.services.api.BusinessRule;
import gov.ca.cwds.bre.services.api.BusinessRuleDocumentationProvider;
import gov.ca.cwds.bre.services.api.BusinessRuleProvider;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;

/**
 * @author CWDS J Team
 */
@Component("BreService")
public class BreService implements BusinessRuleService {
  
  @Autowired
  @Qualifier(value="ContextAwareBusinessRuleProvider")
  private BusinessRuleProvider businessRuleProvider;
  
  @Autowired
  @Qualifier(value="ContextAwareBusinessRuleDocumentationProvider")
  private BusinessRuleDocumentationProvider businessDocumentationProvider;
  
  @Override  
  @ExecutionTimer
  public BreResponse executeBusinessRules(BreRequest breRequest) {        
    BusinessRule br = findBusinessRule(breRequest);    
    return executeBusinessRules(breRequest, br);        
  }
    
  @Override
  @ExecutionTimer
  public List<BusinessRuleDefinition> getBusinessRules() {
    return businessRuleProvider.getBusinessRuleDefinitions();                       
  }
  
  @Override
  @ExecutionTimer
  public BusinessRuleDefinition getBusinessRule(String name) {
    return businessRuleProvider.getBusinessRuleDefinition(name);
  }

  @Override
  @ExecutionTimer
  public BusinessRuleDocumentation getBusinessRuleDocumentation(String name) {
    BusinessRuleDocumentation businessRuleDocumentation;
    try {
      businessRuleDocumentation = businessDocumentationProvider.getBusinessRuleDocumentation(name);
    } catch (BreException ex) {
      throw ex;
    } catch (Throwable t) {
      BreException breException = new BreException("Error finding business rule: " + name, t);
      throw breException;
    }
    return businessRuleDocumentation;
  }

  private BusinessRule findBusinessRule(BreRequest breRequest) {
    String businessRuleName = null;
    BusinessRule br = null;
    try {
      businessRuleName = breRequest.getBusinessRuleName();
      br = businessRuleProvider.getBusinessRule(businessRuleName);
    } catch (Throwable t) {
      BreException breException = new BreException("Error finding business rule: " + businessRuleName, t);
      breException.setBreRequest(breRequest);
      throw breException;
    }
    return br;
  }
  
  private BreResponse executeBusinessRules(BreRequest breRequest, BusinessRule br) {        
    BreResponse breReaposne = null;
    
    try {
      breReaposne = br.execute(breRequest);
    } catch (BreException ex) {
      throw ex;
    } catch (Throwable t) {
      String businessRuleName = breRequest != null ? breRequest.getBusinessRuleName() : null;
      throw new BreException("Error executing business rule." + businessRuleName, 
          t, breRequest, br.getDefinition());      
    }
    
    return breReaposne;
  }

}
