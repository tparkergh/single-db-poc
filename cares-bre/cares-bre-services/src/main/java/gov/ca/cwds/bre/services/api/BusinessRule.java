package gov.ca.cwds.bre.services.api;

import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDefinition;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDocumentation;

/**
 * @author CWDS J Team
 */
public interface BusinessRule {
  
  default BreResponse execute(BreRequest breRequest) {
    throw new UnsupportedOperationException("Execution of rule is not supported for rule: " + getName());    
  }
  
  String getName();
  
  BusinessRuleSetDocumentation getDocumentation();
  
  default BusinessRuleSetDefinition getDefinition() {
    throw new UnsupportedOperationException("Definition of rule is not available for rule: " + getName());
  }
  
  default boolean isDefinitionAvailable() {
    return false;
  }
  
  default boolean isExecutionSupported() {
    return true;
  }
}
