package gov.ca.cwds.cares.interfaces.model.search.hit;

import java.io.Serializable;

public class PersonSearchHit extends SearchHit implements Serializable {

  private static final long serialVersionUID = -971361222823606910L;
  
  private String identifier;
  private String firstName;
  private String lastName;
  private Long primaryPhoneNumber;
  
  public PersonSearchHit() {
    super();
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
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
