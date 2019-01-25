package gov.ca.cwds.cares.rest.controller;

import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.DfhCommArea;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * CWDS J Team
 */
@RestController
public class CicsMockRestController {

  private final static Logger LOGGER = LoggerFactory.getLogger(CicsMockRestController.class);

  @PutMapping("/clients/{clientId}/{lastTimeStamp}")
  CicsResponse putClient(@RequestBody String request,
      @PathVariable("clientId") String clientId,
      @PathVariable("lastTimeStamp") String lastTimeStamp) {
    return executeRequest(request);
  }
  
  @PutMapping("/addresses/{addressId}/{lastTimeStamp}")
  CicsResponse putAddress(@RequestBody String request,
      @PathVariable("addressId") String addressId,
      @PathVariable("lastTimeStamp") String lastTimeStamp) {
    return executeRequest(request);
  }

  private CicsResponse executeRequest(String request) {
    LOGGER.info("Received: {}", request);
    DfhCommArea dfhCommArea = new DfhCommArea();
    dfhCommArea.setAdditionalDesc("Success");
    dfhCommArea.setApiTimestamp(LocalDateTime.now());
    dfhCommArea.setErrorMsgType("");
    dfhCommArea.setProgReturnCode(0);
    
    CicsResponse response = new CicsResponse();
    response.setDfhCommArea(dfhCommArea);
    LOGGER.info("Sending back... {}", response);
    return response;        
  }
}
