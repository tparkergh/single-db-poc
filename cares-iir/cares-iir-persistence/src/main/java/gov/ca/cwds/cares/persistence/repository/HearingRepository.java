package gov.ca.cwds.cares.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gov.ca.cwds.cares.persistence.entity.HearingEntity;

@Repository
public interface HearingRepository extends JpaRepository<HearingEntity, String> {

}