package gov.ca.cwds.cares.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gov.ca.cwds.cares.persistence.entity.ReferralEntity;
import gov.ca.cwds.cares.persistence.repository.ReferralRepository;
import gov.ca.cwds.cares.interfaces.api.ReferralService;
import gov.ca.cwds.cares.interfaces.model.Referral;
import gov.ca.cwds.cares.services.mapping.ReferralMapper;

@Service
public class ReferralServiceImpl implements ReferralService {

  @Autowired
  private ReferralRepository referralRepository;
  private static final String[] REFERRAL_IDS = new String[]{
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

  @Override
  public Collection<Referral> getAllReferrals() {
    // White listed referral ids
    Collection<ReferralEntity> referralEntities =
        referralRepository.findAllById(Arrays.asList(REFERRAL_IDS));
    return ReferralMapper.INSTANCE.mapToReferrals(referralEntities);
  }

  @Override
  public Referral getReferral(String referralId) {
    ReferralEntity refEntity = null;
    Optional<ReferralEntity> referral = referralRepository.findById(referralId);
    if(referral.isPresent()){
      refEntity = referral.get();
    }
    return ReferralMapper.INSTANCE.mapToReferral(refEntity);    
  }
}
