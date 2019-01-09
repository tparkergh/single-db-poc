package gov.ca.cwds.bre.interfaces.api;

import java.util.List;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition;
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
   * Get list of business rule descriptions.
   * 
   * @return Business rule descriptions.
   */
  public List<BusinessRuleDefinition> getBusinessRules();
  
  /**
   * Get business rule definition.
   * 
   * @param name Name of the business rule
   * @return Business rule definitions.
   */
  public BusinessRuleDefinition getBusinessRule(String name);
  
  /**
   * Get business rule documentation
   * 
   * @param name Name of business rule
   * @return Business rule documentation
   * 
   */
  public BusinessRuleSetDocumentation getBusinessRuleDocumentation(String name);
  
}
