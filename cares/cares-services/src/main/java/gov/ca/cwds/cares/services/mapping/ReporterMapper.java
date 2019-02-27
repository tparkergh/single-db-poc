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

/**
 * CWDS J Team
 */
@Mapper
public interface ReporterMapper {
  ReporterMapper INSTANCE = Mappers.getMapper(ReporterMapper.class);

  @Mapping(target = "txnHeaderStaffId", constant = LOGGED_USER_STAFF_ID)
  @Mapping(target = "primaryPhoneExtensionNumber", source = "phoneExtension")
  @Mapping(target = "primaryPhoneNumber", source = "phoneNumber")
  ReporterData mapToReporterData(Reporter address);

  @AfterMapping
  default void enrichReporterData(@MappingTarget ReporterData reporterData) {
    reporterData.setIdentifier(CmsKeyIdGenerator.getNextValue(LOGGED_USER_STAFF_ID));
  }
}
