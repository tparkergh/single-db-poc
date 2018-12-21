package gov.ca.cwds.cics.client;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.client.CicsClientRequest;
import gov.ca.cwds.cics.model.client.CicsClientResponse;

/**
 * CWDS J Team
 */
@Component("CicsClientUpdaterRestApiClient")
@Lazy
public final class CicsClientUpdaterRestApiClient {
  
  @Value("${app.cics.base-url}")
  private String baseUrl;

  @Value("${app.cics.client-path}")
  private String clientPath;
  
  @Autowired
  private CicsRestApiHelper cicsRestApiHelper;
  
  @Autowired
  private ObjectMapper jacksonObjectMapper;

  public CicsClientUpdaterRestApiClient() {
    // Default no-argument constructor
  }

  @ExecutionTimer
  public CicsClientResponse updateClient(CicsClientRequest clientRequest) {
    String clientId = clientRequest.getClientData().getIdentifier();
    LocalDateTime lastUpdatedDateTime = clientRequest.getClientData().getLstUpdTs();
    String lastUpdatedDateTimeStr = lastUpdatedDateTime.format(
        DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSSSSS"));

    URI requestUri = UriComponentsBuilder
        .fromHttpUrl(baseUrl)
        .path(clientPath)
        .path("/" + clientId)
        .path("/" + lastUpdatedDateTimeStr)
        .build().toUri();
    
    CicsClientRequest updatedClientRequest = getClientUpdateRequest(clientRequest);
    
    CicsResponse response = cicsRestApiHelper.exchange(
        requestUri, 
        HttpMethod.PUT, 
        updatedClientRequest, 
        CicsClientResponse.class);

    return (CicsClientResponse) response;
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
