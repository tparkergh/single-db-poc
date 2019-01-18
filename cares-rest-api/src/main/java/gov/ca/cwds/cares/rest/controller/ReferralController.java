package gov.ca.cwds.cares.rest.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import gov.ca.cwds.cares.common.aop.ExecutionTimer;
import gov.ca.cwds.cares.services.interfaces.api.ReferralService;
import gov.ca.cwds.cares.services.interfaces.model.Referral;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author CWDS Team J
 */
@RestController
@CrossOrigin
public class ReferralController {
  
  @Autowired
  private ReferralService referralService;

  @GetMapping("/referrals")
  @ApiOperation(value = "Get all referrals")
  @ExecutionTimer
  public Collection<Referral>  getAllReferrals() {
    return referralService.getAllReferrals();
  }

  @GetMapping("/referrals/{referral_id}")
  @ApiOperation(value = "Get referral identified by given ID")
  @ExecutionTimer
  public Referral getClient(@ApiParam("Referral ID") @PathVariable("referral_id") String id) {
    return referralService.getReferral(id);
  }  
}
