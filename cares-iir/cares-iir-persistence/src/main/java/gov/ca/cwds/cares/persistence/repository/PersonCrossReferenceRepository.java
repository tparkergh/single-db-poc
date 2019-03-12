package gov.ca.cwds.cares.persistence.repository;

import gov.ca.cwds.cares.persistence.entity.PersonCrossReferenceEntity;
import gov.ca.cwds.cares.persistence.entity.PersonCrossReferencePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CWDS J Team
 */
@Repository
public interface PersonCrossReferenceRepository extends JpaRepository<PersonCrossReferenceEntity, PersonCrossReferencePK> {

}
