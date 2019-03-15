package gov.ca.cwds.cares.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cics.model.CicsResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * CWDS J Team
 */
@RestController
public class CicsMockRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CicsMockRestController.class);

  @Autowired
  private ObjectMapper jacksonObjectMapprer;

  @PutMapping("/cics-mock/clients/{client_id}/{last_time_stamp}")
  CicsResponse putClient(@RequestBody String request,
      @PathVariable("client_id") String clientId,
      @PathVariable("last_time_stamp") String lastTimeStamp) {
    return executeRequest(request);
  }
  
  @PutMapping("/cics-mock/addresses/{address_id}/{last_time_stamp}")
  CicsResponse putAddress(@RequestBody String request,
      @PathVariable("address_id") String addressId,
      @PathVariable("last_time_stamp") String lastTimeStamp) {
    return executeRequest(request);
  }

  @PostMapping("/cics-mock/reporters")
  CicsResponse postReporter(@RequestBody String request) {
    return executeRequest(request);
  }

  @PutMapping("/cics-mock/reporters/{reporter_id}/{last_time_stamp}")
  CicsResponse putReporter(@RequestBody String request,
      @PathVariable("reporter_id") String reporterId,
      @PathVariable("last_time_stamp") String lastTimeStamp) {
    return executeRequest(request);
  }

  private CicsResponse executeRequest(String request) {
    LOGGER.info("Received: {}", request);

    CicsResponse response = new CicsResponse();
    try {
      String responseValue = IOUtils.toString(
          getClass().getClassLoader().getResourceAsStream("mock/mock-cics-response.json"),
          StandardCharsets.UTF_8);
      response = jacksonObjectMapprer.readValue(responseValue, CicsResponse.class);
    } catch (IOException e) {
      LOGGER.debug("Exception Thrown while trying to map response. Sending back blank response. Response was :" + request);
    }

    LOGGER.info("Sending back... {}", response);
    return response;        
  }
}
