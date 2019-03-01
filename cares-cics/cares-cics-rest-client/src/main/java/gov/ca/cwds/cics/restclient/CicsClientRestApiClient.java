package gov.ca.cwds.cics.restclient;

import gov.ca.cwds.cics.CicsRestApiHelper;
import gov.ca.cwds.cics.Constants;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cics.model.CicsClientRequest;
import gov.ca.cwds.cics.model.CicsResponse;

/**
 * CWDS J Team
 */
@Component
@Lazy
public final class CicsClientRestApiClient {
  public static final String CLIENT_PATH = "/clients";

  @Value("${app.cics-service.base-url}")
  private String baseUrl;

  @Autowired
  private CicsRestApiHelper cicsRestApiHelper;
  
  @Autowired
  private ObjectMapper jacksonObjectMapper;

  public CicsClientRestApiClient() {
    // Default no-argument constructor
  }

  @ExecutionTimer
  public CicsResponse updateClient(CicsClientRequest clientRequest) {
    String clientId = clientRequest.getClientData().getIdentifier();
    LocalDateTime lastUpdatedDateTime = clientRequest.getClientData().getLstUpdTs();

    URI requestUri = UriComponentsBuilder
        .fromHttpUrl(baseUrl)
        .path(CLIENT_PATH)
        .path("/" + clientId)
        .path("/" + lastUpdatedDateTime.format(Constants.CICS_TIMESTAMP_FORMATTER))
        .build().toUri();
    
    CicsClientRequest updatedClientRequest = getClientUpdateRequest(clientRequest);
    
    CicsResponse response = cicsRestApiHelper.exchange(
        requestUri, 
        HttpMethod.PUT, 
        updatedClientRequest, 
        CicsResponse.class);

    return response;
  }
  
  private CicsClientRequest getClientUpdateRequest(CicsClientRequest client) {
    CicsClientRequest clientRequest = null;
    try {
      clientRequest = jacksonObjectMapper.readValue(jacksonObjectMapper.writeValueAsString(client), CicsClientRequest.class);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error deep copying clint data", e);
    }
    clientRequest.getClientData().setIdentifier(null);
    clientRequest.getClientData().setLstUpdTs(null);
    return clientRequest;
  }  
}
