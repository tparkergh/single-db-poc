package gov.ca.cwds.cares.interfaces.model.people;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gov.ca.cwds.cares.common.binding.IdentifierSerializer;
import gov.ca.cwds.cares.common.model.ObjectBase;
import gov.ca.cwds.cares.interfaces.model.Address;

/**
 * @author CWDS Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reporter extends ObjectBase implements Serializable {

  private static final long serialVersionUID = 653930568046236729L;
  
  @JsonSerialize(using = IdentifierSerializer.class)
  private String identifier;
  
  private String firstName;
  private String lastName;
  private Long primaryPhoneNumber;
  private Integer primaryPhoneExtension;
  private String relationToChild;
  private LocalDate birthDate;
  private String employerName;
  private String titleDescription;
  private LocalDateTime lastUpdateTimestamp;
  private Address address;  

  public Reporter() {
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

  public Integer getPrimaryPhoneExtension() {
    return primaryPhoneExtension;
  }

  public void setPrimaryPhoneExtension(Integer primaryPhoneExtension) {
    this.primaryPhoneExtension = primaryPhoneExtension;
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

  public String getTitleDescription() {
    return titleDescription;
  }

  public void setTitleDescription(String titleDescription) {
    this.titleDescription = titleDescription;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public LocalDateTime getLastUpdateTimestamp() {
    return lastUpdateTimestamp;
  }

  public void setLastUpdateTimestamp(LocalDateTime lastUpdateTimestamp) {
    this.lastUpdateTimestamp = lastUpdateTimestamp;
  }
}
