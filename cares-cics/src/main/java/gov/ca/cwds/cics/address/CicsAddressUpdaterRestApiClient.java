package gov.ca.cwds.cics.address;

import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cics.CicsRestApiHelper;
import gov.ca.cwds.cics.Constants;
import gov.ca.cwds.cics.model.CicsResponse;
import gov.ca.cwds.cics.model.address.CicsAddressRequest;
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
@Component("CicsAddressUpdaterRestApiClient")
public class CicsAddressUpdaterRestApiClient {
  public static final String ADDRESS_PATH = "/addresses";

  @Value("${app.cics-service.base-url}")
  private String baseUrl;

  @Autowired
  @Qualifier("CicsRestApiHelper")
  private CicsRestApiHelper cicsRestApiHelper;

  public CicsAddressUpdaterRestApiClient() {
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
        request,
        CicsResponse.class);

    return response;
  }

  public RestTemplate getRestTemplate() {
    return cicsRestApiHelper.getRestTemplate();
  }

}
