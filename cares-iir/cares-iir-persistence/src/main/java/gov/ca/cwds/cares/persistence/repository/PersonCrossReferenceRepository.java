package gov.ca.cwds.cares.persistence.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gov.ca.cwds.cares.persistence.entity.PersonCrossReferenceEntity;
import gov.ca.cwds.cares.persistence.entity.PersonCrossReferencePK;

/**
 * CWDS J Team
 */
@Repository
public interface PersonCrossReferenceRepository extends JpaRepository<PersonCrossReferenceEntity, PersonCrossReferencePK> {

  Collection<PersonCrossReferenceEntity> findByPersonId(String personId);

  Collection<PersonCrossReferenceEntity> findByXrefId(String xrefId);
}
