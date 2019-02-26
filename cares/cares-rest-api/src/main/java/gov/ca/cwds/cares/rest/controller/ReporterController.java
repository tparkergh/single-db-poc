package gov.ca.cwds.cares.rest.controller;

import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.interfaces.api.ReporterService;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CWDS J Team
 */
@RestController
@RequestMapping(value = "/reporters",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ReporterController {
  @Autowired
  private ReporterService reporterService;

  @PostMapping
  @ApiOperation(value = "Create Reporter")
  @ExecutionTimer
  public Reporter createReporter(@RequestBody Reporter reporter) {
    return reporterService.createReporter(reporter);
  }

}
