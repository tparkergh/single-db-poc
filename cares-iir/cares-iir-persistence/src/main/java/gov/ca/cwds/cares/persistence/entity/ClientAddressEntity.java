package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import gov.ca.cwds.cares.common.model.ObjectBase;

@Entity
@Table(name = "CL_ADDRT")
@NamedQuery(name = "ClientAddressEntity.findByClientId", query = "SELECT ca FROM ClientAddressEntity ca WHERE ca.clientId = ?1")
public class ClientAddressEntity extends ObjectBase implements Serializable {
  
  private static final long serialVersionUID = 9121958878607026060L;

  @Id
  @Column(name = "IDENTIFIER")
  private String identifier;
  
  @Column(name = "ADDR_TPC")
  private Integer addressTytpeCode;
  
  @Column(name = "BK_INMT_ID")
  private String bookingInmateId;
  
  @Column(name = "EFF_STRTDT")
  private LocalDate effectiveStartDate;
  
  @Column(name = "EFF_END_DT")
  private LocalDate effectiveEndDate;
  
  @Column(name = "LST_UPD_ID")
  private String lastUpdateId;
  
  @Column(name = "LST_UPD_TS")
  private LocalDateTime lastUpdateTimestamp;
  
  @Column(name = "FKADDRS_T")
  private String addressId;
  
  @Column(name = "FKCLIENT_T")
  private String clientId;
  
  @Column(name = "FKREFERL_T")
  private String referralId;
  
  @Column(name = "HOMLES_IND")
  private String homelessIndicator;
  
  @ManyToOne
  @JoinColumn(name = "FKADDRS_T", referencedColumnName = "IDENTIFIER", insertable = false, updatable = false)
  private AddressEntity address;
  
  public ClientAddressEntity() {
    // Default no-argument constructor
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public Integer getAddressTytpeCode() {
    return addressTytpeCode;
  }

  public void setAddressTytpeCode(Integer addressTytpeCode) {
    this.addressTytpeCode = addressTytpeCode;
  }

  public String getBookingInmateId() {
    return bookingInmateId;
  }

  public void setBookingInmateId(String bookingInmateId) {
    this.bookingInmateId = bookingInmateId;
  }

  public LocalDate getEffectiveStartDate() {
    return effectiveStartDate;
  }

  public void setEffectiveStartDate(LocalDate effectiveStartDate) {
    this.effectiveStartDate = effectiveStartDate;
  }

  public LocalDate getEffectiveEndDate() {
    return effectiveEndDate;
  }

  public void setEffectiveEndDate(LocalDate effectiveEndDate) {
    this.effectiveEndDate = effectiveEndDate;
  }

  public String getLastUpdateId() {
    return lastUpdateId;
  }

  public void setLastUpdateId(String lastUpdateId) {
    this.lastUpdateId = lastUpdateId;
  }

  public LocalDateTime getLastUpdateTimestamp() {
    return lastUpdateTimestamp;
  }

  public void setLastUpdateTimestamp(LocalDateTime lastUpdateTimestamp) {
    this.lastUpdateTimestamp = lastUpdateTimestamp;
  }

  public String getAddressId() {
    return addressId;
  }

  public void setAddressId(String addressId) {
    this.addressId = addressId;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getReferralId() {
    return referralId;
  }

  public void setReferralId(String referralId) {
    this.referralId = referralId;
  }

  public String getHomelessIndicator() {
    return homelessIndicator;
  }

  public void setHomelessIndicator(String homelessIndicator) {
    this.homelessIndicator = homelessIndicator;
  }
  
  public AddressEntity getAddress() {
    return address;
  }

  public void setAddress(AddressEntity address) {
    this.address = address;
  }
}
