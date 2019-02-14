package gov.ca.cwds.cares.interfaces.model.search.query;

import java.io.Serializable;

public class PersonSearchQuery extends SearchQuery implements Serializable {
  
  private static final long serialVersionUID = -4697131958352739556L;
  
  private String firstName;
  private String lastName;
  private Long primaryPhoneNumber;

  public PersonSearchQuery() {
    super();
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Long getPrimaryPhoneNumber() {
    return primaryPhoneNumber;
  }

  public void setPrimaryPhoneNumber(Long primaryPhoneNumber) {
    this.primaryPhoneNumber = primaryPhoneNumber;
  }
}
