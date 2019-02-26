package gov.ca.cwds.bre.services.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import gov.ca.cwds.bre.interfaces.api.BusinessRuleService;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDefinition;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDocumentation;
import gov.ca.cwds.bre.services.api.BusinessRule;
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
  
  @Override  
  @ExecutionTimer
  public BreResponse executeBusinessRules(BreRequest breRequest) {        
    return businessRuleProvider
        .getBusinessRule(breRequest.getBusinessRuleSetName())
        .execute(breRequest);            
  }
  
  @Override
  @ExecutionTimer
  public BusinessRuleSetDefinition getBusinessRuleSetDefinition(String name) {
    return businessRuleProvider.getBusinessRule(name).getDefinition();
  }

  @Override
  @ExecutionTimer
  public BusinessRuleSetDocumentation getBusinessRuleSetDocumentation(String name) {
    return businessRuleProvider
        .getBusinessRule(name)
        .getDocumentation();    
  }

  @Override
  @ExecutionTimer
  public Collection<String> getAllBusinessRuleSetNames() {
    Collection<BusinessRule> allRules = businessRuleProvider.getAllBusinessRules();
    List<String> names = new ArrayList<>();
    
    for (BusinessRule br : allRules) {
      names.add(br.getName());
    }
    
    return names;
  }
}
