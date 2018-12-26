package gov.ca.cwds.cares.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import gov.ca.cwds.cares.persistence.entity.ClientEntity;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, String> {

}
