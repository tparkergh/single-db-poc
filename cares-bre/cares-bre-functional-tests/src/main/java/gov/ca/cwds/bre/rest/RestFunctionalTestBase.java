package gov.ca.cwds.bre.rest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * CWDS J Team
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:rest-functional-test.properties"})
public abstract class RestFunctionalTestBase {

  @Value("${cares-service.base-url}")
  private String restServiceUrl;
 
  protected String getRestServiceUrl() {
    return restServiceUrl;
  }
}
