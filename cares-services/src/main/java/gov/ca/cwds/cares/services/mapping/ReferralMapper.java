package gov.ca.cwds.cares.services.mapping;

import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import gov.ca.cwds.cares.persistence.entity.ReferralEntity;
import gov.ca.cwds.cares.services.interfaces.model.Referral;

/**
 * CWDS J Team
 */
@Mapper
public interface ReferralMapper {
  
  public ReferralMapper INSTANCE = Mappers.getMapper(ReferralMapper.class);
  
  Referral mapToReferral(ReferralEntity referralEntity);
  
  Collection<Referral> mapToReferrals(Collection<ReferralEntity> referralEntities);
}
