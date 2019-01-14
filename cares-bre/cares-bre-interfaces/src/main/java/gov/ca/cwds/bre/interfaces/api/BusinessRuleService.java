package gov.ca.cwds.bre.interfaces.api;

import java.util.Collection;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDocumentation;

/**
 * @author CWDS Team J
 */
public interface BusinessRuleService {
  
  /**
   * Execute business rule.
   *  
   * @param breRequest BreRequest
   * @return BreResponse
   */
  public BreResponse executeBusinessRules(BreRequest breRequest);
  
  /**
   * Get business rule set documentation
   * 
   * @param name Name of business rule set
   * @return Business rule set documentation
   * 
   */
  public BusinessRuleSetDocumentation getBusinessRuleSetDocumentation(String name);
  
  /**
   * Get names of all business rule sets
   * 
   * @return Names of all business rule sets
   * 
   */
  public Collection<String> getAllBusinessRuleSetNames();
  
}
