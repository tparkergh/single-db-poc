package gov.ca.cwds.cics.restclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.net.URI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import gov.ca.cwds.cares.common.exception.CicsException;
import gov.ca.cwds.cics.CicsRestApiHelper;
import gov.ca.cwds.cics.model.CicsReporterRequest;
import gov.ca.cwds.cics.model.CicsResponse;

/**
 * CWDS J Team
 */
@RunWith(MockitoJUnitRunner.class)
public class CicsReporterRestApiClientTest {

  @Mock
  private CicsRestApiHelper cicsRestApiHelper;

  @InjectMocks
  private CicsReporterRestApiClient cicsReporterRestApiClient;

  @Before
  public void setUp() {
    cicsReporterRestApiClient.setBaseUrl("http://test-BaseUrl");
  }

  @Test
  public void shouldSuccess() {
    CicsReporterRequest request = new CicsReporterRequest();

    CicsResponse response = new CicsResponse();
    when(cicsRestApiHelper.exchange(
        argThat(new ArgumentMatcher<URI>() {
          @Override
          public boolean matches(URI uri) {
            assertEquals("http://test-BaseUrl/reporters/", uri.toString());
            return true;
          }
        }),
        eq(HttpMethod.POST),
        same(request))
    ).thenReturn(response);

    assertSame(response, cicsReporterRestApiClient.createReporter(request));
    verify(cicsRestApiHelper).exchange(any(), any(), any());
  }

  @Test(expected = CicsException.class)
  public void shouldThrowExceptionWhenHasNoResponseFromCicsService() {
    when(cicsRestApiHelper.exchange(any(), any(), any())).thenThrow(CicsException.class);

    cicsReporterRestApiClient.createReporter(new CicsReporterRequest());
    verify(cicsRestApiHelper).exchange(any(), any(), any());
  }
}