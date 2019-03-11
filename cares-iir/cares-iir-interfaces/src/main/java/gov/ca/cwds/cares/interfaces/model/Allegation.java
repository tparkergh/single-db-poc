package gov.ca.cwds.cares.interfaces.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Allegation implements Serializable {

  private static final long serialVersionUID = -1946266094428317625L;
  
  private String identifier;
  private String victimClientId;
  private String perpetratorClientId;
  private String referralId;
  private Integer allegationTypeCode;
  private String lastUpdateId;
  private LocalDateTime lastUpdateTimestamp;
  
  public Allegation() {
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
