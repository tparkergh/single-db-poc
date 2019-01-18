package gov.ca.cwds.cares.rest.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.services.interfaces.api.SystemCodeService;
import gov.ca.cwds.cares.services.interfaces.model.SystemCode;
import gov.ca.cwds.cares.services.interfaces.model.SystemMeta;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author CWDS Team J
 */
@RestController
public class SystemCodeController {

  @Autowired
  private SystemCodeService systemCodeService;

  @GetMapping("/system_metas")
  @ApiOperation(value = "Get all system metas")
  @ExecutionTimer
  public Collection<SystemMeta> getAllMetas() {
    return systemCodeService.getAllMetas();
  }

  @GetMapping("/system_codes/{meta_name}")
  @ApiOperation(value = "Get system codes for a given meta name")
  @ExecutionTimer
  public Collection<SystemCode> getSystemCodes(
      @ApiParam("Meta Name") @PathVariable("meta_name") String metaName) {
    return systemCodeService.getSystemCodes(metaName);
  }


}
