package gov.ca.cwds.cics.restclient;

import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cics.CicsRestApiHelper;
import gov.ca.cwds.cics.Constants;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.CicsAddressRequest;
import java.net.URI;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * CWDS J Team
 */
@Component
public class CicsAddressRestApiClient {
  public static final String ADDRESS_PATH = "/addresses";

  @Value("${app.cics-service.base-url}")
  private String baseUrl;

  @Autowired
  @Qualifier("CicsRestApiHelper")
  private CicsRestApiHelper cicsRestApiHelper;

  public CicsAddressRestApiClient() {
    // Default no-argument constructor
  }

  @ExecutionTimer
  public CicsResponse updateAddress(CicsAddressRequest request, LocalDateTime lastUpdateTimestamp) {
    URI requestUri = UriComponentsBuilder
        .fromHttpUrl(baseUrl)
        .path(ADDRESS_PATH)
        .path("/" + request.getAddressData().getIdentifier())
        .path("/" + lastUpdateTimestamp.format(Constants.CICS_TIMESTAMP_FORMATTER))
        .build().toUri();

    request.getAddressData().setIdentifier(null);

    CicsResponse response = cicsRestApiHelper.exchange(
        requestUri,
        HttpMethod.PUT,
        request);

    return response;
  }

  public RestTemplate getRestTemplate() {
    return cicsRestApiHelper.getRestTemplate();
  }

}
