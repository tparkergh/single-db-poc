package gov.ca.cwds.cares.services.search.jdbc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import gov.ca.cwds.cares.services.mapping.ReporterMapper;
import gov.ca.cwds.cics.model.ReporterData;

public class ReporterMapperTest {

  private ReporterMapper mapper = Mappers.getMapper(ReporterMapper.class);

  @Test
  public void shouldMapReporterToData() {
    Reporter reporter = new Reporter();
    reporter.setFirstName("first name");
    reporter.setLastName("last name");
    reporter.setPrimaryPhoneNumber(1234567);
    reporter.setIdentifier("reporterId");

    ReporterData reporterData = mapper.mapToReporterData(reporter);

    assertThat(reporterData.getFirstName(), is("last name"));
    assertThat(reporterData.getLastName(), is("first name"));
    assertThat(reporterData.getPrimaryPhoneNumber(), is(1234567));
    assertThat(reporterData.getIdentifier(), is("reporterId"));


  }

}
