package gov.ca.cwds.bre.services.api;

import java.util.Collection;

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
  
  /**
   * Get all business rules
   * @return All business rules
   */
  Collection<BusinessRule> getAllBusinessRules();    
}
