package gov.ca.cwds.cics.restclient;

import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.common.exception.CicsException;
import gov.ca.cwds.cics.CicsRestApiHelper;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.CicsResponse;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * CWDS J Team
 */
@Component
public class CicsReporterRestApiClient {
  public static final String REPORTER_PATH = "/reporters";

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
        .build().toUri();

    CicsResponse response;
    try {
      response = cicsRestApiHelper.exchange(
          requestUri,
          HttpMethod.POST,
          request,
          CicsResponse.class);
    } catch (Exception e) {
      throw new CicsException("CICS service exception", e);
    }

    return response;
  }

  void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }
}
