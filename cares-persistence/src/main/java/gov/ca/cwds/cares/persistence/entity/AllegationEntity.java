package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "ALLGTN_T")
public class AllegationEntity implements Serializable {
  
  private static final long serialVersionUID = 3149684314971127414L;

  @Id
  @Column(name = "IDENTIFIER")
  private String identifier;
  
  @Column(name = "FKCLIENT_T")
  private String victimClientId;
  
  @Column(name = "FKCLIENT_0")
  private String perpetratorClientId;
  
  @Column(name = "FKREFERL_T")
  private String referralId;
  
  @Column(name = "ALG_TPC")
  private Integer allegationTypeCode;
  
  @Column(name = "LST_UPD_ID")
  private String lastUpdateId;
  
  @Column(name = "LST_UPD_TS")
  private LocalDateTime lastUpdateTimestamp;
  
  public AllegationEntity() {
    // Default no-argument constructor
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getVictimClientId() {
    return victimClientId;
  }

  public void setVictimClientId(String victimClientId) {
    this.victimClientId = victimClientId;
  }

  public String getPerpetratorClientId() {
    return perpetratorClientId;
  }

  public void setPerpetratorClientId(String perpetratorClientId) {
    this.perpetratorClientId = perpetratorClientId;
  }

  public String getReferralId() {
    return referralId;
  }

  public void setReferralId(String referralId) {
    this.referralId = referralId;
  }

  public Integer getAllegationTypeCode() {
    return allegationTypeCode;
  }

  public void setAllegationTypeCode(Integer allegationTypeCode) {
    this.allegationTypeCode = allegationTypeCode;
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
