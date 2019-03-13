package gov.ca.cwds.cares.services;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cares.interfaces.api.ReporterService;
import gov.ca.cwds.cares.interfaces.model.Address;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cares.services.mapping.ReporterMapper;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.ReporterData;

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

    businessRuleExecutor.executeBusinessRules("ReporterBusinessRules", reporterData);

    CicsReporterRequest cicsReporterRequest = new CicsReporterRequest();
    cicsReporterRequest.setReporterData(reporterData);
    cicsServiceCallExecutor.executeServiceCall(cicsReporterRequest);

    reporter.setIdentifier(reporterData.getIdentifier());
    return reporter;
  }

  @Override
  public Reporter getReporter(String identifier) {
    // Stub response
    Reporter reporter = new Reporter();
    reporter.setIdentifier(identifier);
    reporter.setFirstName("Fake First");
    reporter.setLastName("Fake Last");
    reporter.setPhoneNumber(1234567890L);
    reporter.setPhoneExtension(123);
    reporter.setBirthDate(LocalDate.now());
    reporter.setEmployerName("School XYZ");
    reporter.setTitle("School Admin");
    
    Address address = new Address();
    address.setCity("Sacramento");
    address.setIdentifier("addressId");
    address.setStateCode(1828);
    address.setStreetName("Main Street");
    address.setStreetNumber("123");
    address.setZipCode(95833);
    reporter.setAddress(address);
    
    reporter.setAddress(address);
    return reporter;
  }
}
