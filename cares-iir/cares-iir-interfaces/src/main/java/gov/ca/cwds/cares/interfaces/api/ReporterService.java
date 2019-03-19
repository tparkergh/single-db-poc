package gov.ca.cwds.cares.interfaces.api;

import gov.ca.cwds.cares.interfaces.model.people.Reporter;

/**
 * CWDS J Team
 */
public interface ReporterService {

  /**
   * Create new reporter. If given reporter is null then returns null.
   * 
   * @param reporter Reporter to create.
   * @return Newly created reporter. Returns null if given reporter is null.
   */
  Reporter createReporter(Reporter reporter);

  /**
   * Get reporter person identified by given identifier. Return null if not found.
   * 
   * @param identifier Identifier of reporter person.
   * @return Reporter identified by by given identifier or null if not found.
   */
  Reporter getReporter(String identifier);

  /**
   * Update given reporter, does nothing if given reporter is null.
   * 
   * @param reporter The reporter to update.
   * @return Updated reporter. Returns null if given reporter is null.
   */
  Reporter updateReporter(Reporter reporter);
}
