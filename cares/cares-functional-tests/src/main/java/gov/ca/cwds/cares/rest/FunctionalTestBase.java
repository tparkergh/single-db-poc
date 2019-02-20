package gov.ca.cwds.cares.rest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * CWDS J Team
 */
@RunWith(SpringRunner.class)
@TestPropertySource
public abstract class FunctionalTestBase {

  @Value("${cares-service.base-url}")
  private String restServiceUrl;
 
  protected String getRestServiceUrl() {
    return restServiceUrl;
  }
}
