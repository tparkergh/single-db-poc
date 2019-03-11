package gov.ca.cwds.cares.services.search.jdbc;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import gov.ca.cwds.cares.interfaces.model.search.query.PersonSearchQuery;
import gov.ca.cwds.cares.persistence.entity.ReporterEntity;

public class ReporterSearchSpecification implements Specification<ReporterEntity> {

  private static final long serialVersionUID = 7155245663194741399L;

  private final PersonSearchQuery personSearchQuery;

  public ReporterSearchSpecification(PersonSearchQuery query) {
    this.personSearchQuery = query;
  }

  @Override
  public Predicate toPredicate(Root<ReporterEntity> root, CriteriaQuery<?> query,
      CriteriaBuilder cb) {
    String firstName = StringUtils.lowerCase(personSearchQuery.getFirstName());
    String lastName = StringUtils.lowerCase(personSearchQuery.getLastName());
    Long phone = personSearchQuery.getPrimaryPhoneNumber();
    
    List<Predicate> predicates = new ArrayList<>();

    if (StringUtils.isNotBlank(firstName)) {
      predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + firstName + "%"));
    }

    if (StringUtils.isNotBlank(lastName)) {
      predicates.add(cb.like(cb.lower(root.get("lastName")), "%" + lastName + "%"));
    }

    if (phone != null) {
      predicates.add(cb.equal(root.get("primaryPhoneNumber"), phone));
    }

    return cb.and(predicates.toArray(new Predicate[0]));
  }
}
