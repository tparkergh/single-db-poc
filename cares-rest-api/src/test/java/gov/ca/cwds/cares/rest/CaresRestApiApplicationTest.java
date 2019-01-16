package gov.ca.cwds.cares.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.geo.client.GeoRestClient;
import gov.ca.cwds.cares.services.interfaces.model.ClientAddress;
import gov.ca.cwds.cics.address.CicsAddressUpdaterRestApiClient;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * CWDS J Team
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource
public class CaresRestApiApplicationTest {

  @Value("${server.port}")
  private String port;

  @Value("${app.geo-service.base-url}")
  private String geoServiceBaseUrl;

  @Value("${app.cics-service.base-url}")
  private String cicsServiceBaseUrl;

  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  @Autowired
  private ObjectMapper jacksonObjectMapper;

  @Autowired
  @Qualifier("GeoRestClient")
  private GeoRestClient geoRestClient;

  @Autowired
  @Qualifier("CicsAddressUpdaterRestApiClient")
  private CicsAddressUpdaterRestApiClient cicsAddressUpdaterRestApiClient;

  @Test
  public void whenCallGetClientAddress_thenSuccessResponse() throws Exception {
    MockRestServiceServer geoMockServer = MockRestServiceServer.bindTo(geoRestClient.getRestTemplate()).build();

    String geoExpectedRequest1 = IOUtils.toString(getClass().getResourceAsStream(
        "/fixtures/geo-address-1-expected-request.json"), StandardCharsets.UTF_8);
    String geoMockResponse1 = IOUtils.toString(getClass().getResourceAsStream(
        "/fixtures/geo-address-1-mock-response.json"), StandardCharsets.UTF_8);
    geoMockServer.expect(
        content().json(geoExpectedRequest1))
        .andExpect(requestTo(geoServiceBaseUrl + GeoRestClient.ADDRESS_VALIDATION_PATH))
        .andExpect(method(HttpMethod.POST))
        .andRespond(withSuccess(geoMockResponse1, MediaType.APPLICATION_JSON));

    String geoExpectedRequest2 = IOUtils.toString(getClass().getResourceAsStream(
        "/fixtures/geo-address-2-expected-request.json"), StandardCharsets.UTF_8);
    String geoMockResponse2 = IOUtils.toString(getClass().getResourceAsStream(
        "/fixtures/geo-address-2-mock-response.json"), StandardCharsets.UTF_8);
    geoMockServer.expect(
        content().json(geoExpectedRequest2))
        .andExpect(requestTo(geoServiceBaseUrl + GeoRestClient.ADDRESS_VALIDATION_PATH))
        .andExpect(method(HttpMethod.POST))
        .andRespond(withSuccess(geoMockResponse2, MediaType.APPLICATION_JSON));

    RestTemplate clientRestTemplate = restTemplateBuilder.build();
    ResponseEntity<List<ClientAddress>> response =
        clientRestTemplate.exchange("http://localhost:18090/clients/test-cl-1/addresses", HttpMethod.GET, null,
            new ParameterizedTypeReference<List<ClientAddress>>(){});
    List<ClientAddress> clientAddressResponse = response.getBody();
    String expectedClientAddressResponse = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(
        "fixtures/client-address-expected-get-response.json"), StandardCharsets.UTF_8);
    JSONAssert.assertEquals(expectedClientAddressResponse,
        jacksonObjectMapper.writeValueAsString(clientAddressResponse), JSONCompareMode.NON_EXTENSIBLE);
  }

  @Test
  public void whenCallUpdateClientAddress_thenSuccessResponse() throws Exception {
    MockRestServiceServer geoMockServer = MockRestServiceServer.bindTo(cicsAddressUpdaterRestApiClient.getRestTemplate()).build();

    String cicsExpectedRequest = IOUtils.toString(getClass().getResourceAsStream(
        "/fixtures/cics-address-update-expected-request.json"), StandardCharsets.UTF_8);
    String geoMockResponse1 = IOUtils.toString(getClass().getResourceAsStream(
        "/fixtures/cics-address-update-mock-response.json"), StandardCharsets.UTF_8);
    geoMockServer.expect(
        content().json(cicsExpectedRequest))
        .andExpect(requestTo(cicsServiceBaseUrl + CicsAddressUpdaterRestApiClient.ADDRESS_PATH))
        .andExpect(method(HttpMethod.PUT))
        .andRespond(withSuccess(geoMockResponse1, MediaType.APPLICATION_JSON));

    RestTemplate addressRestTemplate = restTemplateBuilder.build();
    String request = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(
        "fixtures/client-address-update-request.json"), StandardCharsets.UTF_8);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);
    ResponseEntity<ClientAddress> response =
        addressRestTemplate.exchange("http://localhost:18090/clients/test-cl-10/addresses/test-ad-10",
            HttpMethod.PUT, httpEntity, ClientAddress.class);

    ClientAddress clientAddressResponse = response.getBody();
    String expectedClientAddressResponse = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(
        "fixtures/client-address-update-expected-response.json"), StandardCharsets.UTF_8);
    JSONAssert.assertEquals(expectedClientAddressResponse,
        jacksonObjectMapper.writeValueAsString(clientAddressResponse), JSONCompareMode.NON_EXTENSIBLE);

  }
}
