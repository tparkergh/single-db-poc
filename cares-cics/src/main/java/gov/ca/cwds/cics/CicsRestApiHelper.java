package gov.ca.cwds.cics;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import javax.net.ssl.SSLContext;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cics.model.CicsRequest;
import gov.ca.cwds.cics.model.CicsResponse;

/**
 * CWDS J Team
 */
@Component("CicsRestApiHelper")
@Lazy
public class CicsRestApiHelper {
  
  private final static Logger LOGGER = LoggerFactory.getLogger(CicsRestApiHelper.class);
  
  @Value("${app.cics-service.username}")
  private String username;

  @Value("${app.cics-service.password}")
  private String password;
  
  private final RestTemplate restTemplate;
  private final HttpHeaders httpHeaders;

  public CicsRestApiHelper(RestTemplateBuilder restTemplateBuilder) {
    restTemplate = restTemplateBuilder.basicAuthorization(username, password).build();
    CloseableHttpClient httpClient = getCloseableHttpClient();
    HttpComponentsClientHttpRequestFactory requestFactory
        = new HttpComponentsClientHttpRequestFactory();

    requestFactory.setHttpClient(httpClient);
    restTemplate.setRequestFactory(requestFactory);
    
    httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    String plainCreds = username + ":" + password;
    byte[] plainCredsBytes = plainCreds.getBytes();
    byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
    String base64Creds = new String(base64CredsBytes, StandardCharsets.UTF_8);
    httpHeaders.add("Authorization", "Basic " + base64Creds);
  }

  @ExecutionTimer
  public CicsResponse exchange(
      URI requestUri, 
      HttpMethod httpMethod, 
      CicsRequest cicsRequest, 
      Class cicsResponseType) {
    HttpEntity<CicsRequest> request = new HttpEntity<>(cicsRequest, httpHeaders);
        
    LOGGER.info("CICS REQUEST URI: {}", requestUri);
    LOGGER.info("CICS REQUEST: {}", cicsRequest);

    ResponseEntity<CicsResponse> exchange =
        restTemplate.exchange(requestUri, httpMethod, request, cicsResponseType);

    CicsResponse cicsResponse = exchange.getBody();
    
    LOGGER.info("CICS RESPONSE: {}", cicsResponse);
    
    return cicsResponse;
  }

  private CloseableHttpClient getCloseableHttpClient() {
    TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;
    SSLContext sslContext;
    try {
      sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
    } catch (NoSuchAlgorithmException e) {
      String message = "Cannot create SSL context. No Algorithm found";
      throw new IllegalStateException(message, e);
    } catch (KeyManagementException e) {
      String message = "Cannot create SSL context. Key Management issue";
      throw new IllegalStateException(message, e);
    } catch (KeyStoreException e) {
      String message = "Cannot create SSL context. Key Store issue";
      throw new IllegalStateException(message, e);
    }
    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,
        new NoopHostnameVerifier());

    return HttpClients.custom().setSSLSocketFactory(csf).build();
  }

  public RestTemplate getRestTemplate() {
    return restTemplate;
  }
}
