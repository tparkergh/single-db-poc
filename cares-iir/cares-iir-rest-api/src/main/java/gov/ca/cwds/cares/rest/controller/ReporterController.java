package gov.ca.cwds.cares.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.interfaces.api.ReporterService;
import gov.ca.cwds.cares.interfaces.model.people.Reporter;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * CWDS J Team
 */
@RestController
@RequestMapping(value = "/reporters")
@CrossOrigin
public class ReporterController {

  @Autowired
  private ReporterService reporterService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create Reporter")
  @ExecutionTimer
  public @ResponseBody Reporter createReporter(@RequestBody Reporter reporter) {
    return reporterService.createReporter(reporter);
  }

  @GetMapping("/{reporter_id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get reporter identified by given ID")
  @ExecutionTimer
  public Reporter getReporter(@ApiParam("Reporter ID") @PathVariable("reporter_id") String id) {
    return reporterService.getReporter(id);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Update Reporter")
  @ExecutionTimer
  public @ResponseBody Reporter updateReporter(@RequestBody Reporter reporter) {
    return reporterService.updateReporter(reporter);
  }
}
