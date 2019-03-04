package gov.ca.cwds.cares.services.mapping;

import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cics.model.ReporterData;
import gov.ca.cwds.data.persistence.cms.CmsKeyIdGenerator;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


import static gov.ca.cwds.cares.common.Constants.LOGGED_USER_STAFF_ID;
import static gov.ca.cwds.cares.common.Constants.NOT;
import static gov.ca.cwds.cares.common.Constants.ZERO;
import static gov.ca.cwds.cares.common.Constants.STATE_OF_CALIFORNIA;

/**
 * CWDS J Team
 */
@Mapper
public interface ReporterMapper {
  ReporterMapper INSTANCE = Mappers.getMapper(ReporterMapper.class);

  @Mapping(target = "txnHeaderStaffId", constant = LOGGED_USER_STAFF_ID)
  @Mapping(target = "primaryPhoneExtensionNumber", source = "phoneExtension")
  @Mapping(target = "primaryPhoneNumber", source = "phoneNumber")
  @Mapping(target = "communicationMethod", constant = ZERO)
  @Mapping(target = "collateralClientReporterRelationship", constant = ZERO)
  @Mapping(target = "confidentialWaiverIndicator", constant = NOT)
  @Mapping(target = "countySpecificCode", constant = STATE_OF_CALIFORNIA)
  @Mapping(target = "feedbackRequiredIndicator", constant = NOT)
  @Mapping(target = "mandatedReporterIndicator", constant = NOT)
  @Mapping(target = "state", constant = ZERO)
  ReporterData mapToReporterData(Reporter address);

  @AfterMapping
  default void enrichReporterData(@MappingTarget ReporterData reporterData) {
    reporterData.setIdentifier(CmsKeyIdGenerator.getNextValue(LOGGED_USER_STAFF_ID));
  }
}
