package gov.ca.cwds.bre.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import gov.ca.cwds.bre.interfaces.api.BusinessRuleService;
import gov.ca.cwds.bre.interfaces.model.BreRequest;
import gov.ca.cwds.bre.interfaces.model.BreResponse;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleDefinition;
import gov.ca.cwds.bre.interfaces.model.BusinessRuleSetDocumentation;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author CWDS Team J
 */
@RestController
public class BreRestController {
  
  @Autowired
  @Qualifier(value="BreService")  
  private BusinessRuleService businessRuleService;

  @PostMapping("/bre")
  @ExecutionTimer
  @ApiOperation(value = "Execute business rules")
  public BreResponse executeBusinessRules(@RequestBody BreRequest breRequest) {
    return businessRuleService.executeBusinessRules(breRequest);
  }
  
  @GetMapping("/bre")
  @ExecutionTimer
  @ApiOperation(value = "Get definitions of all available business rules")
  public List<BusinessRuleDefinition> getBusinessRules() {
    return businessRuleService.getBusinessRules();
  }  
  
  @GetMapping("/bre/{name}")
  @ExecutionTimer
  @ApiOperation(value = "Get definition of business rule identified by given name")
  public BusinessRuleDefinition getBusinessRule(@ApiParam("Business rule name") @PathVariable("name") String name) {
    return businessRuleService.getBusinessRule(name);
  }
  
  @GetMapping("/bre/documentation/{name}")
  @ExecutionTimer
  @ApiOperation(value = "Get documentation of a business rule identified by given name")
  public BusinessRuleSetDocumentation getBusinessRuleDocumentation(@ApiParam("Business rule name") @PathVariable("name") String name) {
    return businessRuleService.getBusinessRuleDocumentation(name);
  }
}
