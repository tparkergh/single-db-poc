package gov.ca.cwds.cares.rest.controller;

import gov.ca.cwds.cares.interfaces.api.ReporterService;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * CWDS J Team
 */
@RunWith(MockitoJUnitRunner.class)
public class ReporterControllerTest {
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
}