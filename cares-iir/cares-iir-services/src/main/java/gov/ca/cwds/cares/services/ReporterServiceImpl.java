package gov.ca.cwds.cares.services;

import gov.ca.cwds.cares.common.exception.DataIntegrityException;
import gov.ca.cwds.cares.common.identifier.CmsKeyIdGenerator;
import gov.ca.cwds.cares.persistence.entity.XrefCode;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.ReporterData;
import gov.ca.cwds.cics.restclient.CicsReporterRestApiClient;


import static gov.ca.cwds.cares.common.Constants.LOGGED_USER_STAFF_ID;

/**
 * CWDS J Team
 */
@Service
public class ReporterServiceImpl implements ReporterService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ReporterServiceImpl.class);

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
    reporterData.setIdentifier(CmsKeyIdGenerator.getNextValue(LOGGED_USER_STAFF_ID));

    businessRuleExecutor.executeBusinessRules("ReporterBusinessRules", reporterData);

    CicsReporterRequest cicsReporterRequest = new CicsReporterRequest();
    cicsReporterRequest.setReporterData(reporterData);
    CicsResponse cicsResponse = cicsReporterRestApiClient.createReporter(cicsReporterRequest);

    reporter.setLastUpdateTimestamp(cicsResponse.getDfhCommArea().getApiTimestamp());
    String reporterLegacyId = reporterData.getIdentifier();
    Collection<PersonCrossReferenceEntity> references =
        filterReporterReferences(personCrossReferenceRepository.findByXrefId(reporterLegacyId));
    if (references.isEmpty()) {
      LOGGER.error("No Person Cross Reference was found for Reporter Id '{}'", reporterLegacyId);
      throw new DataIntegrityException("No Person Cross Reference was found for given Reporter Id");
    }

    String personLegacyId = references.iterator().next().getPersonId();
    String uiId = CmsKeyIdGenerator.getUIIdentifierFromKey(personLegacyId);

    reporter.setIdentifier(uiId);
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
    String uiId = reporter.getIdentifier();
    String personLegacyId = CmsKeyIdGenerator.getKeyFromUIIdentifier(uiId);
    Collection<PersonCrossReferenceEntity> references =
        filterReporterReferences(personCrossReferenceRepository.findByPersonId(personLegacyId));

    if (references.isEmpty()) {
      LOGGER.error("No Person Cross Reference was found for Person Id '{}'" + personLegacyId);
      throw new DataIntegrityException("No Person Cross Reference was found for given Person Id");
    }
    String reporterLegacyId = references.iterator().next().getXrefId();
    reporterData.setIdentifier(reporterLegacyId);

    businessRuleExecutor.executeBusinessRules("ReporterBusinessRules", reporterData);

    CicsReporterRequest cicsReporterRequest = new CicsReporterRequest();
    cicsReporterRequest.setReporterData(reporterData);
    CicsResponse cicsResponse = cicsReporterRestApiClient.updateReporter(cicsReporterRequest, 
        reporter.getLastUpdateTimestamp());
    reporter.setLastUpdateTimestamp(cicsResponse.getDfhCommArea().getApiTimestamp());
    return reporter;
  }

  private Collection<PersonCrossReferenceEntity> filterReporterReferences(Collection<PersonCrossReferenceEntity> references) {
    return references.stream()
          .filter(r -> XrefCode.R.name().equals(r.getXrefCode())).collect(Collectors.toSet());
  }
}
