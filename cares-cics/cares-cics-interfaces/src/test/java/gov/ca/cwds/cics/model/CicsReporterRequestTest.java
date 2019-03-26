package gov.ca.cwds.cics.model;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * CWDS J Team
 */
public class CicsReporterRequestTest {

  @Test
  public void whenDeserialized_shouldSerialize() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    String partisipantReporterValue = IOUtils.toString(
        getClass().getResource("/fixtures/cics-reporter-request-serialization.json"),
        StandardCharsets.UTF_8);
    JsonNode partisipantReporterNode = objectMapper.readTree(partisipantReporterValue);

    CicsReporterRequest deserialized = objectMapper.readValue(
        objectMapper.writeValueAsString(partisipantReporterNode), CicsReporterRequest.class);

    String serialized = objectMapper.writeValueAsString(deserialized);
    JSONAssert.assertEquals(serialized, partisipantReporterValue, JSONCompareMode.LENIENT);

    LocalDate expectedBirthDate =
        LocalDate.parse("2000-01-06", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    LocalDate actualBirthDate = deserialized.getReporterData().getBirthDate();
    Assert.assertEquals(expectedBirthDate, actualBirthDate);

    String expectedTitleDescription = "Teacher at Russel Ranch School";
    String actualTitleDescription = deserialized.getReporterData().getTitleDescription();
    Assert.assertEquals(expectedTitleDescription, actualTitleDescription);
  }

}
