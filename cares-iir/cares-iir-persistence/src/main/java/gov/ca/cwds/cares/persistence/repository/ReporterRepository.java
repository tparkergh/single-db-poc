package gov.ca.cwds.cares.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import gov.ca.cwds.cares.persistence.entity.ReporterEntity;

@Repository
public interface ReporterRepository
    extends JpaRepository<ReporterEntity, String>, JpaSpecificationExecutor {

}
