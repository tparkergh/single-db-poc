package gov.ca.cwds.cares.services.interfaces.model;

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
public class SystemMeta implements Serializable {

  private static final long serialVersionUID = 5237768756638520258L;

  private String logicalTableDsdName;
  private Integer defaultSystemId;
  private String longDescriptionName;
  private String longDescriptionIndicator;
  private LocalDateTime lastBroadcastTimestamp;
  private String lastUpdatedById;
  private LocalDateTime lastUpdatedTimeStamp;
  private String otherCodeName;
  private String relationshipToCategoryIndicator;
  private String shortDescriptionName;
  private String userDefinedLogicalIdIndicator;
  private Integer userDefinedIdLength;
  private String userTableName;

  public SystemMeta() {
    // Default no-argument constructor
  }


  public String getLogicalTableDsdName() {
    return logicalTableDsdName;
  }


  public void setLogicalTableDsdName(String logicalTableDsdName) {
    this.logicalTableDsdName = logicalTableDsdName;
  }


  public Integer getDefaultSystemId() {
    return defaultSystemId;
  }


  public void setDefaultSystemId(Integer defaultSystemId) {
    this.defaultSystemId = defaultSystemId;
  }


  public String getLongDescriptionName() {
    return longDescriptionName;
  }


  public void setLongDescriptionName(String longDescriptionName) {
    this.longDescriptionName = longDescriptionName;
  }


  public String getLongDescriptionIndicator() {
    return longDescriptionIndicator;
  }


  public void setLongDescriptionIndicator(String longDescriptionIndicator) {
    this.longDescriptionIndicator = longDescriptionIndicator;
  }


  public LocalDateTime getLastBroadcastTimestamp() {
    return lastBroadcastTimestamp;
  }


  public void setLastBroadcastTimestamp(LocalDateTime lastBroadcastTimestamp) {
    this.lastBroadcastTimestamp = lastBroadcastTimestamp;
  }


  public String getLastUpdatedById() {
    return lastUpdatedById;
  }


  public void setLastUpdatedById(String lastUpdatedById) {
    this.lastUpdatedById = lastUpdatedById;
  }


  public LocalDateTime getLastUpdatedTimeStamp() {
    return lastUpdatedTimeStamp;
  }


  public void setLastUpdatedTimeStamp(LocalDateTime lastUpdatedTimeStamp) {
    this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
  }


  public String getOtherCodeName() {
    return otherCodeName;
  }


  public void setOtherCodeName(String otherCodeName) {
    this.otherCodeName = otherCodeName;
  }


  public String getRelationshipToCategoryIndicator() {
    return relationshipToCategoryIndicator;
  }


  public void setRelationshipToCategoryIndicator(String relationshipToCategoryIndicator) {
    this.relationshipToCategoryIndicator = relationshipToCategoryIndicator;
  }


  public String getShortDescriptionName() {
    return shortDescriptionName;
  }


  public void setShortDescriptionName(String shortDescriptionName) {
    this.shortDescriptionName = shortDescriptionName;
  }


  public String getUserDefinedLogicalIdIndicator() {
    return userDefinedLogicalIdIndicator;
  }


  public void setUserDefinedLogicalIdIndicator(String userDefinedLogicalIdIndicator) {
    this.userDefinedLogicalIdIndicator = userDefinedLogicalIdIndicator;
  }


  public Integer getUserDefinedIdLength() {
    return userDefinedIdLength;
  }


  public void setUserDefinedIdLength(Integer userDefinedIdLength) {
    this.userDefinedIdLength = userDefinedIdLength;
  }


  public String getUserTableName() {
    return userTableName;
  }


  public void setUserTableName(String userTableName) {
    this.userTableName = userTableName;
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
