package gov.ca.cwds.cares.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cares.interfaces.api.ReporterService;
import gov.ca.cwds.cares.interfaces.model.Address;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cares.persistence.entity.PersonCrossReferenceEntity;
import gov.ca.cwds.cares.persistence.entity.ReporterEntity;
import gov.ca.cwds.cares.persistence.repository.PersonCrossReferenceRepository;
import gov.ca.cwds.cares.persistence.repository.ReporterRepository;
import gov.ca.cwds.cares.services.mapping.ReporterMapper;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.ReporterData;

/**
 * CWDS J Team
 */
@Service
public class ReporterServiceImpl implements ReporterService {
  
  @Autowired
  private PersonCrossReferenceRepository personCrossReferenceRepository;
  
  @Autowired
  private ReporterRepository reporterRepository;

  @Autowired
  private BusinessRulesExecutor<BreResponse, ReporterData> businessRuleExecutor;

  @Autowired
  @Qualifier("CicsReporterServiceCallExecutor")
  private CicsServiceCallExecutor<CicsReporterRequest> cicsServiceCallExecutor;

  @Override
  public Reporter createReporter(Reporter reporter) {
    ReporterData reporterData = ReporterMapper.INSTANCE.mapReporterToReporterData(reporter);

    businessRuleExecutor.executeBusinessRules("ReporterBusinessRules", reporterData);

    CicsReporterRequest cicsReporterRequest = new CicsReporterRequest();
    cicsReporterRequest.setReporterData(reporterData);
    cicsServiceCallExecutor.executeServiceCall(cicsReporterRequest);

    reporter.setIdentifier(reporterData.getIdentifier());
    return reporter;
  }

  @Override
  public Reporter getReporter(String identifier) {
    Reporter reporter = null;    
    Collection<PersonCrossReferenceEntity> personCrossReferenceEntities = personCrossReferenceRepository.findByPersonId(identifier);
    
    if (personCrossReferenceEntities != null && 
        !personCrossReferenceEntities.isEmpty()) {
      PersonCrossReferenceEntity personCrossReferenceEntity = personCrossReferenceEntities.iterator().next();    
      ReporterEntity reporterEntity = reporterRepository.findById(personCrossReferenceEntity.getXrefId()).get();
      reporter = ReporterMapper.INSTANCE.mapReporterEntityToReporter(reporterEntity);
    }
    
    return reporter;    
  }

  @Override
  public Reporter updateReporter(Reporter reporter, LocalDateTime lastUpdateTimestamp) {
    ReporterData reporterData = ReporterMapper.INSTANCE.mapReporterToReporterData(reporter);

    businessRuleExecutor.executeBusinessRules("ReporterBusinessRules", reporterData);

    CicsReporterRequest cicsReporterRequest = new CicsReporterRequest();
    cicsReporterRequest.setReporterData(reporterData);
    cicsServiceCallExecutor.executeServiceCallForUpdate(cicsReporterRequest, lastUpdateTimestamp);

    reporter.setIdentifier(reporterData.getIdentifier());
    return reporter;
  }
}
