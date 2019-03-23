package gov.ca.cwds.cares.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import gov.ca.cwds.cares.interfaces.api.ReporterService;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;

/**
 * CWDS J Team
 */
@RunWith(MockitoJUnitRunner.class)
public class ReporterControllerTest {
  private LocalDateTime lastUpdateTimestamp;
  @Mock
  private ReporterService reporterService;

  @InjectMocks
  private ReporterController reporterController;

  @Test
  public void testCreateReporter() {
    Reporter request = new Reporter();
    Reporter response = new Reporter();
    when(reporterService.createReporter(request)).thenReturn(response);
    assertEquals(response, reporterController.createReporter(request));
    verify(reporterService).createReporter(request);
  }

  @Test
  public void shouldReturn201ResponseCode() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(reporterController).build();

    mockMvc.perform(post("/reporters").content("{}").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void testUpdateReporter() {
    Reporter request = new Reporter();
    Reporter response = new Reporter();
    when(reporterService.updateReporter(request)).thenReturn(response);
    assertEquals(response, reporterController.updateReporter(request));
    verify(reporterService).updateReporter(request);
  }

  @Test
  public void testGetReporter() {
    String id = "test id";
    Reporter response = new Reporter();
    when(reporterService.getReporter(id)).thenReturn(response);
    assertEquals(response, reporterController.getReporter(id));
    verify(reporterService).getReporter(id);
  }
}
