package gov.ca.cwds.cics.restclient;

import java.net.URI;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cics.CicsRestApiHelper;
import gov.ca.cwds.cics.Constants;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.CicsResponse;

/**
 * CWDS J Team
 */
@Component
public class CicsReporterRestApiClient {
  
  public static final String REPORTER_PATH = "/reporters/";

  @Value("${app.cics-service.base-url}")
  private String baseUrl;

  @Autowired
  @Qualifier("CicsRestApiHelper")
  private CicsRestApiHelper cicsRestApiHelper;

  public CicsReporterRestApiClient() {
    // Default no-argument constructor
  }

  @ExecutionTimer
  public CicsResponse createReporter(CicsReporterRequest request) {
    URI requestUri = UriComponentsBuilder
        .fromHttpUrl(baseUrl)
        .path(REPORTER_PATH)
        .build()
        .toUri();
    return cicsRestApiHelper.exchange(requestUri, HttpMethod.POST, request);
  }

  @ExecutionTimer
  public CicsResponse updateReporter(CicsReporterRequest request,
      LocalDateTime lastUpdateTimestamp) {
    URI requestUri = UriComponentsBuilder
        .fromHttpUrl(baseUrl)
        .path(REPORTER_PATH)
        .path("/" + request.getReporterData().getIdentifier())
        .path("/" + lastUpdateTimestamp.format(Constants.CICS_TIMESTAMP_FORMATTER))
        .build().toUri();    
    return cicsRestApiHelper.exchange(requestUri, HttpMethod.POST, request);    
  }

  void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }
}
