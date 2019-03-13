package gov.ca.cwds.cares.interfaces.model.people;

import gov.ca.cwds.cares.interfaces.model.BaseEntity;
import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.interfaces.model.Address;

/**
 * @author CWDS Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reporter extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 653930568046236729L;
  
  private String firstName;
  private String lastName;
  private Long phoneNumber;
  private Integer phoneExtension;
  private String relationToChild;
  private LocalDate birthDate;
  private String employerName;
  private String title;
  private Address address;  

  public Reporter() {
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

  public Long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Integer getPhoneExtension() {
    return phoneExtension;
  }

  public void setPhoneExtension(Integer phoneExtension) {
    this.phoneExtension = phoneExtension;
  }

  public String getRelationToChild() {
    return relationToChild;
  }

  public void setRelationToChild(String relationToChild) {
    this.relationToChild = relationToChild;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getEmployerName() {
    return employerName;
  }

  public void setEmployerName(String employerName) {
    this.employerName = employerName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
