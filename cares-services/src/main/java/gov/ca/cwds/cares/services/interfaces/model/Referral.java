package gov.ca.cwds.cares.services.interfaces.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Referral implements Serializable {
  
  private static final long serialVersionUID = 440890092508367802L;
  
  private String identifier;
  private String referralName;
  private Integer approvalStatusCode;
  private Integer communicationMethodCode;
  private Integer governmentEntityCode;
  private Integer responseTypeCode;
  private LocalDate receivedDate;
  private LocalTime receivedTime;
  private LocalDate responseDeterminationDate;
  private LocalTime responseDeterminationTime;
  private String responsibleAgencyCode;
  private String lastUpdateId;
  private LocalDateTime lastUpdateTimestamp;
  private Collection<Allegation> allegations = new ArrayList<>();
  
  public Referral() {
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
  
  public Collection<Allegation> getAllegations() {
    return allegations;
  }

  public void setAllegations(Collection<Allegation> allegations) {
    this.allegations = allegations;
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
