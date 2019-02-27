package gov.ca.cwds.bre.rest.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import gov.ca.cwds.bre.interfaces.api.BusinessRuleService;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDefinition;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDocumentation;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author CWDS Team J
 */
@RestController
@CrossOrigin
public class BreRestController {
  
  @Autowired
  @Qualifier(value="BreService")  
  private BusinessRuleService businessRuleService;

  @PostMapping("/bre/exec")
  @ExecutionTimer
  @ApiOperation(value = "Execute business rules")
  public BreResponse executeBusinessRules(@RequestBody BreRequest breRequest) {
    return businessRuleService.executeBusinessRules(breRequest);
  }
  
  @GetMapping("/bre/defs/{name}")
  @ExecutionTimer
  @ApiOperation(value = "Get definition (logic) of business rule set identified by given name")
  public BusinessRuleSetDefinition getBusinessRuleDefinition(@ApiParam("Business rule set name") @PathVariable("name") String name) {
    return businessRuleService.getBusinessRuleSetDefinition(name);
  }
  
  @GetMapping("/bre/docs")
  @ExecutionTimer
  @ApiOperation(value = "Get names of of all business rule sets")
  public Collection<String> getAllBusinessRuleSetNames() {
    return businessRuleService.getAllBusinessRuleSetNames();
  }
  
  @GetMapping("/bre/docs/{name}")
  @ExecutionTimer
  @ApiOperation(value = "Get documentation of business rule set identified by given name")
  public BusinessRuleSetDocumentation getBusinessRuleDocumentation(@ApiParam("Business rule set name") @PathVariable("name") String name) {
    return businessRuleService.getBusinessRuleSetDocumentation(name);
  }
}
