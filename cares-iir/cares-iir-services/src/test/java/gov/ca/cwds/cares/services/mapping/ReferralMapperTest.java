package gov.ca.cwds.cares.services.mapping;

import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cares.interfaces.model.Allegation;
import gov.ca.cwds.cares.interfaces.model.Referral;
import gov.ca.cwds.cares.persistence.entity.AllegationEntity;
import gov.ca.cwds.cares.persistence.entity.ReferralEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class ReferralMapperTest {
  @Test
  public void shouldMapEntityToReferral(){
    ReferralEntity referralEntity = new ReferralEntity();
    referralEntity.setIdentifier("HGF543");
    referralEntity.setReferralName("Some Referral");
    referralEntity.setApprovalStatusCode(01);
    referralEntity.setCommunicationMethodCode(02);
    referralEntity.setGovernmentEntityCode(03);
    referralEntity.setResponseTypeCode(04);
    referralEntity.setReceivedDate(LocalDate.now());
    referralEntity.setReceivedTime(LocalTime.now());
    referralEntity.setResponseDeterminationDate(LocalDate.of(2020,9,20));
    referralEntity.setResponseDeterminationTime(LocalTime.of(10,59));
    referralEntity.setResponsibleAgencyCode("05");
    referralEntity.setLastUpdateId("OXE");
    referralEntity.setLastUpdateTimestamp(LocalDateTime.now());

    AllegationEntity allegation = new AllegationEntity();
    allegation.setIdentifier("7654CFGV");
    referralEntity.addAllegation(allegation);

    Referral referral = ReferralMapper.INSTANCE.mapToReferral(referralEntity);

    assertEquals(referralEntity.getIdentifier(), referral.getIdentifier());
    assertEquals(referralEntity.getReferralName(), referral.getReferralName());
    assertEquals(referralEntity.getApprovalStatusCode(), referral.getApprovalStatusCode());
    assertEquals(referralEntity.getCommunicationMethodCode(), referral.getCommunicationMethodCode());
    assertEquals(referralEntity.getGovernmentEntityCode(), referral.getGovernmentEntityCode());
    assertEquals(referralEntity.getResponseTypeCode(), referral.getResponseTypeCode());
    assertEquals(referralEntity.getReceivedDate(), referral.getReceivedDate());
    assertEquals(referralEntity.getReceivedTime(), referral.getReceivedTime());
    assertEquals(referralEntity.getResponseDeterminationDate(), referral.getResponseDeterminationDate());
    assertEquals(referralEntity.getResponseDeterminationTime(), referral.getResponseDeterminationTime());
    assertEquals(referralEntity.getResponsibleAgencyCode(), referral.getResponsibleAgencyCode());
    assertEquals(referralEntity.getLastUpdateId(), referral.getLastUpdateId());
    assertEquals(referralEntity.getLastUpdateTimestamp(), referral.getLastUpdateTimestamp());

    Allegation referralAllegation = referral.getAllegations().iterator().next();
    assertEquals(allegation.getIdentifier(), referralAllegation.getIdentifier());
  }

  @Test
  public void shouldMapCollectionOfEntitiesToCollectionsOfReferrals(){
    ReferralEntity referralEntity1 = buildReferralEntity("HGF543","Referral 1", "7654CFGV");
    ReferralEntity referralEntity2 = buildReferralEntity("OIUN765","Referral 2", "8564CVSA");

    Collection<ReferralEntity> referralEntities = new ArrayList();
    referralEntities.add(referralEntity1);
    referralEntities.add(referralEntity2);
    Collection<Referral> referrals = ReferralMapper.INSTANCE.mapToReferrals(referralEntities);

    Map<String, Referral> referralsById = new HashMap();
    referrals.forEach((n) -> referralsById.put(n.getIdentifier(), n) );

    Referral referral1 = referralsById.get(referralEntity1.getIdentifier());
    assertEquals(referralEntity1.getIdentifier(), referral1.getIdentifier());
    assertEquals(referralEntity1.getReferralName(), referral1.getReferralName());
    assertEquals(referralEntity1.getAllegations().iterator().next().getIdentifier(), referral1.getAllegations().iterator().next().getIdentifier());

    Referral referral2 = referralsById.get(referralEntity2.getIdentifier());
    assertEquals(referralEntity2.getIdentifier(), referral2.getIdentifier());
    assertEquals(referralEntity2.getReferralName(), referral2.getReferralName());
    assertEquals(referralEntity2.getAllegations().iterator().next().getIdentifier(), referral2.getAllegations().iterator().next().getIdentifier());
  }

  private ReferralEntity buildReferralEntity(String id, String referralName, String allegationId ) {
    ReferralEntity referralEntity1 = new ReferralEntity();
    referralEntity1.setIdentifier(id);
    referralEntity1.setReferralName(referralName);
    AllegationEntity allegation1 = new AllegationEntity();
    allegation1.setIdentifier(allegationId);
    referralEntity1.addAllegation(allegation1);
    return referralEntity1;
  }
}
