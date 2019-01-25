package gov.ca.cwds.cares.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.geo.restclient.GeoRestClient;
import gov.ca.cwds.cares.interfaces.model.Address;
import gov.ca.cwds.cares.interfaces.model.ClientAddress;
import gov.ca.cwds.cics.restclient.CicsAddressUpdaterRestApiClient;
import java.io.IOException;
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
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
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
    MockRestServiceServer mockServer = MockRestServiceServer.bindTo(geoRestClient.getRestTemplate()).build();

    verifyGeoServiceCall(mockServer,
        "/fixtures/address/geo-address-1-expected-request.json", "/fixtures/address/geo-address-1-mock-response.json");
    verifyGeoServiceCall(mockServer,
        "/fixtures/address/geo-address-2-expected-request.json", "/fixtures/address/geo-address-2-mock-response.json");

    RestTemplate clientRestTemplate = restTemplateBuilder.build();
    ResponseEntity<List<ClientAddress>> response =
        clientRestTemplate.exchange("http://localhost:18090/clients/test-cl-1/addresses", HttpMethod.GET, null,
            new ParameterizedTypeReference<List<ClientAddress>>(){});
    List<ClientAddress> clientAddressResponse = response.getBody();
    String expectedClientAddressResponse = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(
        "fixtures/address/client-address-expected-get-response.json"), StandardCharsets.UTF_8);
    JSONAssert.assertEquals(expectedClientAddressResponse,
        jacksonObjectMapper.writeValueAsString(clientAddressResponse), JSONCompareMode.NON_EXTENSIBLE);
  }

  @Test
  public void whenCallUpdateClientAddress_thenSuccessResponse() throws Exception {
    MockRestServiceServer geoMockServer = MockRestServiceServer.bindTo(geoRestClient.getRestTemplate()).build();

    verifyGeoServiceCall(geoMockServer,
        "/fixtures/address/geo-address-expected-request.json", "/fixtures/address/geo-address-mock-response.json");

    MockRestServiceServer cicsMockServer = MockRestServiceServer.bindTo(cicsAddressUpdaterRestApiClient.getRestTemplate()).build();

    verifyCicsUpdateAddressServiceCall(cicsMockServer,
        "/fixtures/address/cics-address-update-expected-request.json", "/fixtures/address/cics-address-update-mock-response.json");

    RestTemplate addressRestTemplate = restTemplateBuilder.build();
    String request = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(
        "fixtures/address/client-address-update-request.json"), StandardCharsets.UTF_8);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);
    ResponseEntity<ClientAddress> response =
        addressRestTemplate.exchange("http://localhost:18090/clients/test-cl-10/addresses/test-ad-10",
            HttpMethod.PUT, httpEntity, ClientAddress.class);

    ClientAddress clientAddressResponse = response.getBody();
    String expectedClientAddressResponse = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(
        "fixtures/address/client-address-update-expected-response.json"), StandardCharsets.UTF_8);
    JSONAssert.assertEquals(expectedClientAddressResponse,
        jacksonObjectMapper.writeValueAsString(clientAddressResponse), JSONCompareMode.NON_EXTENSIBLE);
  }

  @Test
  public void whenCallUpdateAddress_thenSuccessResponse() throws Exception {
    MockRestServiceServer geoMockServer = MockRestServiceServer.bindTo(geoRestClient.getRestTemplate()).build();

    verifyGeoServiceCall(geoMockServer,
        "/fixtures/address/geo-address-expected-request.json", "/fixtures/address/geo-address-mock-response.json");

    MockRestServiceServer cicsMockServer = MockRestServiceServer.bindTo(cicsAddressUpdaterRestApiClient.getRestTemplate()).build();

    verifyCicsUpdateAddressServiceCall(cicsMockServer,
        "/fixtures/address/cics-address-update-expected-request.json", "/fixtures/address/cics-address-update-mock-response.json");

    RestTemplate addressRestTemplate = restTemplateBuilder.build();
    String request = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(
        "fixtures/address/address-update-request.json"), StandardCharsets.UTF_8);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);
    ResponseEntity<Address> response =
        addressRestTemplate.exchange("http://localhost:18090/addresses/test-ad-10",
            HttpMethod.PUT, httpEntity, Address.class);

    Address addressResponse = response.getBody();
    String expectedAddressResponse = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(
        "fixtures/address/address-update-expected-response.json"), StandardCharsets.UTF_8);
    JSONAssert.assertEquals(expectedAddressResponse,
        jacksonObjectMapper.writeValueAsString(addressResponse), JSONCompareMode.NON_EXTENSIBLE);
  }

  private void verifyGeoServiceCall(MockRestServiceServer mockServer, String expectedRequestPath, String mockResponsePath) throws IOException {
    String expectedRequest = IOUtils.toString(getClass().getResourceAsStream(
        expectedRequestPath), StandardCharsets.UTF_8);
    String mockResponse = IOUtils.toString(getClass().getResourceAsStream(
        mockResponsePath), StandardCharsets.UTF_8);

    mockServer.expect(
        content().json(expectedRequest))
        .andExpect(requestTo(geoServiceBaseUrl + GeoRestClient.ADDRESS_VALIDATION_PATH))
        .andExpect(method(HttpMethod.POST))
        .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));
  }

  private void verifyCicsUpdateAddressServiceCall(MockRestServiceServer mockServer, String expectedRequestPath, String mockResponsePath) throws IOException {
    String cicsExpectedRequest = IOUtils.toString(getClass().getResourceAsStream(
        expectedRequestPath), StandardCharsets.UTF_8);
    String cicsMockResponse = IOUtils.toString(getClass().getResourceAsStream(
        mockResponsePath), StandardCharsets.UTF_8);
    mockServer.expect(
        content().json(cicsExpectedRequest))
        .andExpect(requestTo(cicsServiceBaseUrl + CicsAddressUpdaterRestApiClient.ADDRESS_PATH + "/test-ad-10/2018-07-24-15.06.50.945749"))
        .andExpect(method(HttpMethod.PUT))
        .andExpect(header("Authorization", "Basic dGVzdC1jaWNzLXNlcnZpY2UtdXNlcm5hbWU6dGVzdC1jaWNzLXNlcnZpY2UtcGFzc3dvcmQ="))
        .andRespond(withSuccess(cicsMockResponse, MediaType.APPLICATION_JSON));
  }
}
