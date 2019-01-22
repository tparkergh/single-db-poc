package gov.ca.cwds.cares.services.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import gov.ca.cwds.cares.persistence.entity.AllegationEntity;
import gov.ca.cwds.cares.persistence.entity.ReferralEntity;
import gov.ca.cwds.cares.persistence.repository.ReferralRepository;
import gov.ca.cwds.cares.services.interfaces.model.Allegation;
import gov.ca.cwds.cares.services.interfaces.model.Referral;

@RunWith(MockitoJUnitRunner.class)
public class ReferralServiceImplTest {

  @Mock
  private ReferralRepository referralRepository;

  @InjectMocks
  private ReferralServiceImpl referralService;    

  @Test
  public void testGetClient() {
    String id = "testId";
    ReferralEntity entity = new ReferralEntity();
    entity.setIdentifier(id);
    
    AllegationEntity allegationEntity = new AllegationEntity();
    allegationEntity.setVictimClientId(id);
    allegationEntity.setIdentifier("alleghationId");
    
    entity.addAllegation(allegationEntity);
    
    Referral expected = new Referral();
    expected.setIdentifier(id);
    
    Allegation allegation = new Allegation();
    allegation.setVictimClientId(id);
    allegation.setIdentifier("alleghationId");
    
    expected.addAllegation(allegation);
    
    when(referralRepository.findById(id)).thenReturn(Optional.of(entity));
    
    Referral actual = referralService.getReferral(id);
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetAllReferrals() {
    String id_1 = "testId_1";
    String id_2 = "testId_2";
    
    ReferralEntity entity_1 = new ReferralEntity();
    entity_1.setIdentifier(id_1);
    
    ReferralEntity entity_2 = new ReferralEntity();
    entity_2.setIdentifier(id_2);
    
    List<ReferralEntity> entities = Arrays.asList(entity_1, entity_2);
    
    Referral referral_1 = new Referral();
    referral_1.setIdentifier(id_1);
    
    Referral referral_2 = new Referral();
    referral_2.setIdentifier(id_2);
    
    Collection<Referral> expected = Arrays.asList(referral_1, referral_2);
    
    when(referralRepository.findAllById(any())).thenReturn(entities);
    
    Collection<Referral> actual = referralService.getAllReferrals();
    assertEquals(expected, actual);
  }
}
