package gov.ca.cwds.cares.geo.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.geo.api.GeoService;
import gov.ca.cwds.cares.geo.model.GeoAddress;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * CWDS J Team
 */
@Component("GeoRestClient")
public class GeoRestClient implements GeoService {
  private static final Logger LOGGER = LoggerFactory.getLogger(GeoRestClient.class);

  public static final String ADDRESS_VALIDATION_PATH = "/address/validate";

  @Autowired
  private ObjectMapper jacksonObjectMapper;

  @Value("${app.geo-service.base-url}")
  private String baseUrl;

  private final RestTemplate restTemplate;

  public GeoRestClient(RestTemplateBuilder restTemplateBuilder) {
    restTemplate = restTemplateBuilder.build();
    CloseableHttpClient httpClient = HttpClients.createDefault();

    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

    requestFactory.setHttpClient(httpClient);
    restTemplate.setRequestFactory(requestFactory);
  }

  @Override
  public List<GeoAddress> validateAddress(GeoAddress request) {
    LOGGER.info("Geo address request: {}", request);
    HttpEntity<GeoAddress> httpEntity = new HttpEntity<>(request);

    List<GeoAddress> response = new ArrayList<>();
    try {
      ResponseEntity<List<GeoAddress>> exchange =
          restTemplate.exchange(baseUrl + ADDRESS_VALIDATION_PATH, HttpMethod.POST, httpEntity,
              new ParameterizedTypeReference<List<GeoAddress>>() {
              });

      response = exchange.getBody();
    } catch (Exception e) {
      LOGGER.warn("Address is not deliverable", e);
      response.add(request);
    }
    LOGGER.info("Geo address response: {}", response);
    return response;
  }

  public RestTemplate getRestTemplate() {
    return restTemplate;
  }
}
