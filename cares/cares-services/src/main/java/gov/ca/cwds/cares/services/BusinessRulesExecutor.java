package gov.ca.cwds.cares.services;

/**
 * CWDS J Team
 */
public interface BusinessRulesExecutor<R, F> {
  R executeBusinessRules(String ruleName, F fact);
}
