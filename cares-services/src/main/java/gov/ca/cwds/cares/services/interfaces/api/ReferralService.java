package gov.ca.cwds.cares.services.interfaces.api;

import java.util.Collection;
import gov.ca.cwds.cares.services.interfaces.model.Referral;

public interface ReferralService {
  
  Collection<Referral> getAllReferrals();
  
  Referral getReferral(String referralId);

}
