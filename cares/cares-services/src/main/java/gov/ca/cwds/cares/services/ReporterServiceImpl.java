package gov.ca.cwds.cares.services;

import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cares.interfaces.api.ReporterService;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cares.services.mapping.ReporterMapper;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.ReporterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * CWDS J Team
 */
@Service
public class ReporterServiceImpl implements ReporterService {
  @Autowired
  private BusinessRulesExecutor<BreResponse, ReporterData> businessRuleExecutor;

  @Autowired
  @Qualifier("CicsReporterServiceCallExecutor")
  private CicsServiceCallExecutor<CicsReporterRequest> cicsServiceCallExecutor;

  @Override
  public Reporter createReporter(Reporter reporter) {
    ReporterData reporterData = ReporterMapper.INSTANCE.mapToReporterData(reporter);

    businessRuleExecutor.executeBusinessRules("ReporterCreateScreenBusinessRules", reporterData);

    CicsReporterRequest cicsReporterRequest = new CicsReporterRequest();
    cicsReporterRequest.setReporterData(reporterData);
    cicsServiceCallExecutor.executeServiceCall(cicsReporterRequest);
    return reporter;
  }
}
