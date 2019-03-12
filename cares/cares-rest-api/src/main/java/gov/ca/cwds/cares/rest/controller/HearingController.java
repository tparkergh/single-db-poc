package gov.ca.cwds.cares.rest.controller;

import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.interfaces.api.HearingService;
import gov.ca.cwds.cares.interfaces.model.Hearing;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author CWDS Team J
 */
@RestController
@CrossOrigin
public class HearingController {
  
  @Autowired
  private HearingService hearingService;

  @GetMapping("/hearings")
  @ApiOperation(value = "Get all hearings")
  @ExecutionTimer
  public Collection<Hearing>  getAllHearings() {
    return hearingService.getAllHearings();
  }

  @GetMapping("/hearings/{hearing_id}")
  @ApiOperation(value = "Get hearing identified by given ID")
  @ExecutionTimer
  public Hearing getClient(@ApiParam("Hearing ID") @PathVariable("hearing_id") String id) {
    return hearingService.getHearing(id);
  }  
}
