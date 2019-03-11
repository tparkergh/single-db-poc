package gov.ca.cwds.cares.persistence.repository;

import gov.ca.cwds.cares.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CWDS J Team
 */
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String> {

}
