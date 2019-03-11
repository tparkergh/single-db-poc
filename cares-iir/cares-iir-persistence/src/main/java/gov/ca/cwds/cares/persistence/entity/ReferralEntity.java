package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "REFERL_T")
public class ReferralEntity implements Serializable {

  private static final long serialVersionUID = 1932421535948072459L;

  @Id
  @Column(name = "IDENTIFIER")
  private String identifier;
  
  @Column(name = "REFERRL_NM")
  private String referralName;
  
  @Column(name = "APV_STC")
  private Integer approvalStatusCode;
  
  @Column(name = "CMM_MTHC")
  private Integer communicationMethodCode;
  
  @Column(name = "GVR_ENTC")
  private Integer governmentEntityCode;
  
  @Column(name = "RFR_RSPC")
  private Integer responseTypeCode;
  
  @Column(name = "REF_RCV_DT")
  private LocalDate receivedDate;
  
  @Column(name = "REF_RCV_TM")
  private LocalTime receivedTime;
  
  @Column(name = "RSP_DTR_DT")
  private LocalDate responseDeterminationDate;
  
  @Column(name = "RSP_DTR_TM")
  private LocalTime responseDeterminationTime;
  
  @Column(name = "RSP_AGY_CD")
  private String responsibleAgencyCode;
  
  @Column(name = "LST_UPD_ID")
  private String lastUpdateId;
  
  @Column(name = "LST_UPD_TS")
  private LocalDateTime lastUpdateTimestamp;
  
  @OneToMany
  @JoinColumn(name = "FKREFERL_T", insertable = false, updatable = false)
  private Collection<AllegationEntity> allegations = new ArrayList<>();
  
  public ReferralEntity() {
    // Default no-argument constructor 
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getReferralName() {
    return referralName;
  }

  public void setReferralName(String referralName) {
    this.referralName = referralName;
  }

  public Integer getApprovalStatusCode() {
    return approvalStatusCode;
  }

  public void setApprovalStatusCode(Integer approvalStatusCode) {
    this.approvalStatusCode = approvalStatusCode;
  }

  public Integer getCommunicationMethodCode() {
    return communicationMethodCode;
  }

  public void setCommunicationMethodCode(Integer communicationMethodCode) {
    this.communicationMethodCode = communicationMethodCode;
  }

  public Integer getGovernmentEntityCode() {
    return governmentEntityCode;
  }

  public void setGovernmentEntityCode(Integer governmentEntityCode) {
    this.governmentEntityCode = governmentEntityCode;
  }

  public Integer getResponseTypeCode() {
    return responseTypeCode;
  }

  public void setResponseTypeCode(Integer responseTypeCode) {
    this.responseTypeCode = responseTypeCode;
  }

  public LocalDate getReceivedDate() {
    return receivedDate;
  }

  public void setReceivedDate(LocalDate receivedDate) {
    this.receivedDate = receivedDate;
  }

  public LocalTime getReceivedTime() {
    return receivedTime;
  }

  public void setReceivedTime(LocalTime receivedTime) {
    this.receivedTime = receivedTime;
  }

  public LocalDate getResponseDeterminationDate() {
    return responseDeterminationDate;
  }

  public void setResponseDeterminationDate(LocalDate responseDeterminationDate) {
    this.responseDeterminationDate = responseDeterminationDate;
  }

  public LocalTime getResponseDeterminationTime() {
    return responseDeterminationTime;
  }

  public void setResponseDeterminationTime(LocalTime responseDeterminationTime) {
    this.responseDeterminationTime = responseDeterminationTime;
  }

  public String getResponsibleAgencyCode() {
    return responsibleAgencyCode;
  }

  public void setResponsibleAgencyCode(String responsibleAgencyCode) {
    this.responsibleAgencyCode = responsibleAgencyCode;
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
  
  public Collection<AllegationEntity> getAllegations() {
    return allegations;
  }

  public void setAllegations(Collection<AllegationEntity> allegations) {
    this.allegations = allegations;
  }
  
  public void addAllegation(AllegationEntity allegation) {
    this.allegations.add(allegation);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }
}
