package gov.ca.cwds.cares.services;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.cares.interfaces.api.ReporterService;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cares.persistence.entity.PersonCrossReferenceEntity;
import gov.ca.cwds.cares.persistence.entity.ReporterEntity;
import gov.ca.cwds.cares.persistence.repository.PersonCrossReferenceRepository;
import gov.ca.cwds.cares.persistence.repository.ReporterRepository;
import gov.ca.cwds.cares.services.mapping.ReporterMapper;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.ReporterData;
import gov.ca.cwds.cics.restclient.CicsReporterRestApiClient;

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
  private CicsReporterRestApiClient cicsReporterRestApiClient;
  
  @Override
  public Reporter createReporter(Reporter reporter) {
    if (reporter == null) {
      return null;
    }
    
    ReporterData reporterData = ReporterMapper.INSTANCE.mapReporterToReporterData(reporter);

    businessRuleExecutor.executeBusinessRules("ReporterBusinessRules", reporterData);

    CicsReporterRequest cicsReporterRequest = new CicsReporterRequest();
    cicsReporterRequest.setReporterData(reporterData);
    cicsReporterRestApiClient.createReporter(cicsReporterRequest);

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
      Optional<ReporterEntity> reporterEntityOptional = reporterRepository.findById(personCrossReferenceEntity.getXrefId());
      
      if (reporterEntityOptional.isPresent()) {
        ReporterEntity reporterEntity = reporterEntityOptional.get();
        reporter = ReporterMapper.INSTANCE.mapReporterEntityToReporter(reporterEntity);
      }      
    }
    
    return reporter;    
  }

  @Override
  public Reporter updateReporter(Reporter reporter) {
    if (reporter == null) {
      return null;
    }
    
    ReporterData reporterData = ReporterMapper.INSTANCE.mapReporterToReporterData(reporter);

    businessRuleExecutor.executeBusinessRules("ReporterBusinessRules", reporterData);

    CicsReporterRequest cicsReporterRequest = new CicsReporterRequest();
    cicsReporterRequest.setReporterData(reporterData);
    cicsReporterRestApiClient.updateReporter(cicsReporterRequest, reporter.getLastUpdateTimestamp());

    reporter.setIdentifier(reporterData.getIdentifier());
    return reporter;
  }
}
