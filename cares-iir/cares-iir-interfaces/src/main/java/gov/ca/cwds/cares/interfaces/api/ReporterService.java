package gov.ca.cwds.cares.interfaces.api;

import java.time.LocalDateTime;

import gov.ca.cwds.cares.interfaces.model.people.Reporter;

/**
 * CWDS J Team
 */
public interface ReporterService {

  Reporter createReporter(Reporter reporter);

  Reporter getReporter(String identifier);

  Reporter updateReporter(Reporter reporter, LocalDateTime lastUpdateTimestamp);
}
