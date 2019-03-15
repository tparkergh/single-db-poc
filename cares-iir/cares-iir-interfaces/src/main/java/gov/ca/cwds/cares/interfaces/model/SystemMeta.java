package gov.ca.cwds.cares.interfaces.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.model.ObjectBase;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SystemMeta extends ObjectBase implements Serializable {

  private static final long serialVersionUID = 5237768756638520258L;

  private String metaName;
  private String userTableName;
  private String shortDescription;
  private Integer defaultSystemId;
  private String longDescription;
  private String longDescriptionIndicator;
  private LocalDateTime lastBroadcastTimestamp;
  private String lastUpdatedById;
  private LocalDateTime lastUpdatedTimeStamp;
  private String otherCodeName;
  private String relationshipToCategoryIndicator;  
  private String userDefinedLogicalIdIndicator;
  private Integer userDefinedIdLength;  

  public SystemMeta() {
    // Default no-argument constructor
  }

  public Integer getDefaultSystemId() {
    return defaultSystemId;
  }


  public void setDefaultSystemId(Integer defaultSystemId) {
    this.defaultSystemId = defaultSystemId;
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

  public String getMetaName() {
    return metaName;
  }

  public void setMetaName(String metaName) {
    this.metaName = metaName;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

}
