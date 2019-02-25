package gov.ca.cwds.cares.services;

import gov.ca.cwds.cares.interfaces.api.ReporterService;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import org.springframework.stereotype.Service;

/**
 * CWDS J Team
 */
@Service
public class ReporterServiceImpl implements ReporterService {
  @Override
  public Reporter createReporter(Reporter reporter) {
    return new Reporter();
  }
}
