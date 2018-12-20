package gov.ca.cwds.bre.services.api;

import java.util.List;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition;

/**
 * @author CWDS J Team
 */
public interface BusinessRuleProvider {

  /**
   * Lookup business rule by name/id.
   * 
   * @param name Name or ID of business rule
   * @return The business rule 
   */
  BusinessRule getBusinessRule(String name);
  
  List<BusinessRuleDefinition> getBusinessRuleDefinitions();
  
  BusinessRuleDefinition getBusinessRuleDefinition(String name);
}
