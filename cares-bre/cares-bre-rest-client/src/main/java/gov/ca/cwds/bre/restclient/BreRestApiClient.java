package gov.ca.cwds.bre.restclient;

import java.util.Collection;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import gov.ca.cwds.bre.interfaces.api.BusinessRuleService;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDefinition;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDocumentation;

/**
 * @author CWDS J Team
 */
@Lazy
@Component("BreRestApiClient")
public final class BreRestApiClient implements BusinessRuleService {
  
  private final static Logger LOGGER = LoggerFactory.getLogger(BreRestApiClient.class);
  
  public static final String BRE_EXEC_PATH = "/bre/exec";

  @Value("${app.bre-service.base-url}")
  private String baseUrl;

  private final RestTemplate restTemplate;
  
  public BreRestApiClient(RestTemplateBuilder restTemplateBuilder) {
    restTemplate = restTemplateBuilder.build();
    CloseableHttpClient httpClient = HttpClients.createDefault();
    
    HttpComponentsClientHttpRequestFactory requestFactory
        = new HttpComponentsClientHttpRequestFactory();

    requestFactory.setHttpClient(httpClient);
    restTemplate.setRequestFactory(requestFactory);  
  }

  @Override
  public BreResponse executeBusinessRules(BreRequest breRequest) {
    LOGGER.info("BRE REQUEST: {}", breRequest);
    HttpEntity<BreRequest> request = new HttpEntity<>(breRequest);
    
    ResponseEntity<BreResponse> exchange =
        restTemplate.exchange(baseUrl + BRE_EXEC_PATH, HttpMethod.POST, request, BreResponse.class);

    BreResponse breResponse = exchange.getBody();    
    LOGGER.info("BRE RESPONSE: {}", breResponse);
    
    return breResponse;
  }
  
  @Override
  public BusinessRuleSetDocumentation getBusinessRuleSetDocumentation(String name) {
    throw new UnsupportedOperationException("This function is not implemented yet.");
  }

  @Override
  public Collection<String> getAllBusinessRuleSetNames() {
    throw new UnsupportedOperationException("This function is not implemented yet.");
  }

  @Override
  public BusinessRuleSetDefinition getBusinessRuleSetDefinition(String name) {
    throw new UnsupportedOperationException("This function is not implemented yet.");
  }

  public RestTemplate getRestTemplate() {
    return restTemplate;
  }
}
