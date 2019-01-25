package gov.ca.cwds.cics.model;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
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
public class CicsClientRequestTest {

  @Test
  public void whenDeserialized_shouldSerialize() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    String partisipantClientValue = IOUtils.toString(getClass().getResource("/fixtures/cics-client-request-serialization.json"), StandardCharsets.UTF_8);
    JsonNode partisipantClientNode = objectMapper.readTree(partisipantClientValue);
    
    CicsClientRequest deserialized = objectMapper.readValue(objectMapper.writeValueAsString(partisipantClientNode), CicsClientRequest.class);
    
    String serialized = objectMapper.writeValueAsString(deserialized);
    JSONAssert.assertEquals(serialized, partisipantClientValue, JSONCompareMode.LENIENT);
    
    LocalDateTime expectedLastUpdatedTs = LocalDateTime.parse("2018-12-06-02.08.37.278241", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSSSSS"));
    LocalDateTime actualLastUpdatedTs = deserialized.getClientData().getLstUpdTs();
    Assert.assertEquals(expectedLastUpdatedTs, actualLastUpdatedTs);    
  }

}
