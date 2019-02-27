package gov.ca.cwds.cares.services.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cics.model.ReporterData;

/**
 * CWDS J Team
 */
@Mapper
public interface ReporterMapper {
  ReporterMapper INSTANCE = Mappers.getMapper(ReporterMapper.class);

  @Mapping(target = "txnHeaderStaffId", constant = "0WM")
  @Mapping(target = "colltrClientRptrReltnshipType", constant = "0")
  @Mapping(target = "communicationMethodType", constant = "0")
  @Mapping(target = "countySpecificCode", constant = "99")
  @Mapping(target = "messagePhoneExtensionNumber", constant = "0")
  @Mapping(target = "messagePhoneNumber", constant = "0")
  @Mapping(target = "primaryPhoneExtensionNumber", constant = "0")
  @Mapping(target = "primaryPhoneNumber", constant = "0")
  @Mapping(target = "zipNumber", constant = "0")
  @Mapping(target = "zipSuffixNumber", constant = "0")

  ReporterData mapToReporterData(Reporter reporter);

  Reporter mapToReporter(ReporterData reporterDate);

}
