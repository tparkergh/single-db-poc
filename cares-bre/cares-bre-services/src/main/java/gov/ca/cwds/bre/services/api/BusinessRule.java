package gov.ca.cwds.bre.services.api;

import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition;

/**
 * @author CWDS J Team
 */
public interface BusinessRule {
  
  BreResponse execute(BreRequest breRequest);
  
  BusinessRuleDefinition getDefinition();

}
