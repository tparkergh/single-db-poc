package gov.ca.cwds.cares.services.service;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gov.ca.cwds.cares.persistence.entity.ReferralEntity;
import gov.ca.cwds.cares.persistence.repository.ReferralRepository;
import gov.ca.cwds.cares.services.interfaces.api.ReferralService;
import gov.ca.cwds.cares.services.interfaces.model.Referral;
import gov.ca.cwds.cares.services.mapping.ReferralMapper;

@Service
public class ReferralServiceImpl implements ReferralService {

  @Autowired 
  ReferralRepository referralRepository;
  
  @Override
  public Collection<Referral> getAllReferrals() {
    // White listed referral ids
    String[] referralIds = {
        "AgLPH9f00R", 
        "Ai3VQBU0Um", 
        "AqVHAqw0AB", 
        "AwEdTKm0I3", 
        "AwEtHFQ0Ki",
        "Ax2Kdk80VJ", 
        "AJuT5J20Rt", 
        "BhvFBFY0Ki", 
        "Bjjwfhu0Mq", 
        "BnpfMhU0Py", 
        "BttXSZm00b", 
        "BEeGWrD0Hg", 
        "BShQ64u0SS"
        };
    Collection<ReferralEntity> referralEntities =
        referralRepository.findAllById(Arrays.asList(referralIds));
    return ReferralMapper.INSTANCE.mapToReferrals(referralEntities);
  }

  @Override
  public Referral getReferral(String referralId) {
    ReferralEntity refEntity = referralRepository.findById(referralId).get();    
    return ReferralMapper.INSTANCE.mapToReferral(refEntity);    
  }
}
