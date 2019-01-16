package gov.ca.cwds.cics.rest.controller;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.DfhCommArea;

/**
 * CWDS J Team
 */
@RestController
public class CicsRestController {

  private final static Logger LOGGER = LoggerFactory.getLogger(CicsRestController.class);

  @PostMapping("/clients")
  CicsResponse postClient(@RequestBody String request) {    
    return doClient(request);
  }

  @PutMapping("/clients")
  CicsResponse putClient(@RequestBody String request) {
    return doClient(request);
  }
  
  @PutMapping("/clients/{clientId}/addresses/{addressId}")
  CicsResponse putClientAddress(@RequestBody String request) {
    return doClient(request);
  }

  private CicsResponse doClient(String request) {
    LOGGER.info("Received: {}", request);
    DfhCommArea dfhCommArea = new DfhCommArea();
    dfhCommArea.setAdditionalDesc("Success");
    dfhCommArea.setApiTimestamp(LocalDateTime.now());
    dfhCommArea.setErrorMsgType("");
    
    CicsResponse response = new CicsResponse();
    response.setDfhCommArea(dfhCommArea);
    LOGGER.info("Sending back... {}", response);
    return response;        
  }
}
