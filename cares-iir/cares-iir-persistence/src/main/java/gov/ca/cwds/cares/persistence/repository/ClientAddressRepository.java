package gov.ca.cwds.cares.persistence.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gov.ca.cwds.cares.persistence.entity.ClientAddressEntity;

@Repository
public interface ClientAddressRepository extends JpaRepository<ClientAddressEntity, String> {

  Collection<ClientAddressEntity> findByClientId(String clientId);
}
