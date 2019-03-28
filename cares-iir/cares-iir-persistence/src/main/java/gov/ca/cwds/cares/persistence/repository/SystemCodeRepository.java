package gov.ca.cwds.cares.persistence.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.ca.cwds.cares.persistence.entity.SystemCodeEntity;

@Repository
public interface SystemCodeRepository extends JpaRepository<SystemCodeEntity, Integer> {

  Collection<SystemCodeEntity> findByMetaNameOrderByShortDescriptionAsc(String metaName);
}
