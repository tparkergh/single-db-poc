package gov.ca.cwds.bre.services.api;

import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDocumentation;

public interface BusinessRuleDocumentationProvider {
  
  /**
   * Get the documentation for the business rule by name
   * 
   * @param name the business rule name
   * @return The documentation for the business rule
   */
  BusinessRule getBusinessRuleDocument(String name);
  
  BusinessRuleSetDocumentation getBusinessRuleDocumentation(String name);
}
