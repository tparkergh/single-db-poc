package gov.ca.cwds.cares.services.mapping;

import static gov.ca.cwds.cares.common.Constants.LOGGED_USER_STAFF_ID;
import static gov.ca.cwds.cares.common.Constants.NOT;
import static gov.ca.cwds.cares.common.Constants.STATE_OF_CALIFORNIA;
import static gov.ca.cwds.cares.common.Constants.ZERO;
import java.util.Collection;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cares.persistence.entity.PersonCrossReferenceEntity;
import gov.ca.cwds.cares.persistence.entity.ReporterEntity;
import gov.ca.cwds.cics.model.ReporterData;

/**
 * CWDS J Team
 */
@Mapper
public abstract class ReporterMapper {

  public final static ReporterMapper INSTANCE = Mappers.getMapper(ReporterMapper.class);

  @Mapping(target = "identifier", ignore = true)
  @Mapping(target = "txnHeaderStaffId", constant = LOGGED_USER_STAFF_ID)
  @Mapping(target = "primaryPhoneExtensionNumber", source = "primaryPhoneExtension")
  @Mapping(target = "communicationMethod", constant = ZERO)
  @Mapping(target = "collateralClientReporterRelationship", constant = ZERO)
  @Mapping(target = "confidentialWaiverIndicator", constant = NOT)
  @Mapping(target = "countySpecificCode", constant = STATE_OF_CALIFORNIA)
  @Mapping(target = "feedbackRequiredIndicator", constant = NOT)
  @Mapping(target = "mandatedReporterIndicator", constant = NOT)
  @Mapping(target = "streetNumber", source = "address.streetNumber")
  @Mapping(target = "streetName", source = "address.streetName")
  @Mapping(target = "cityName", source = "address.city")
  @Mapping(target = "state", source = "address.stateCode")
  @Mapping(target = "zipNumber", source = "address.zipCode")
  @Mapping(target = "zipSuffixNumber", source = "address.zipSuffix")
  public abstract ReporterData mapReporterToReporterData(Reporter reporter);

  @Mapping(target = "address.city", source = "cityName")
  @Mapping(target = "address.stateCode", source = "stateCode")
  @Mapping(target = "address.streetName", source = "streetName")
  @Mapping(target = "address.streetNumber", source = "streetNumber")
  @Mapping(target = "address.zipCode", source = "zipCode")
  public abstract Reporter mapReporterEntityToReporter(ReporterEntity reporterEntity);
  
  @AfterMapping
  public void afterMappingReporterEntityToReporter(@MappingTarget Reporter reporter, ReporterEntity reporterEntity) {
    Collection<PersonCrossReferenceEntity> personCrossReferences = reporterEntity.getPersonCrossReferences();
    if (personCrossReferences != null && !personCrossReferences.isEmpty()) {
      PersonCrossReferenceEntity personCrossReferenceEntity = personCrossReferences.iterator().next();
      String personId = personCrossReferenceEntity.getPersonId();
      reporter.setIdentifier(personId);
    }    
  }
}
