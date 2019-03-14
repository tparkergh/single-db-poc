
package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import gov.ca.cwds.cares.common.model.ObjectBase;

@Entity
@Table(name = "S_META_T")
public class SystemMetaEntity extends ObjectBase implements Serializable {

  private static final long serialVersionUID = 6969398151287232182L;

  @Id
  @Column(name = "TBL_DSD_NM")
  private String metaName;
  
  @Column(name = "USR_TBL_NM")
  private String userTableName;
  
  @Column(name = "SHT_DSC_NM")
  private String shortDescription;

  @Column(name = "DEF_SYSID")
  private Integer defaultSystemId;

  @Column(name = "LNG_DSC_NM")
  private String longDescription;

  @Column(name = "LNG_DSCIND")
  private String longDescriptionIndicator;

  @Column(name = "LST_BRD_TS")
  private LocalDateTime lastBroadcastTimestamp;

  @Column(name = "LST_UPD_ID")
  private String lastUpdatedById;

  @Column(name = "LST_UPD_TS")
  private LocalDateTime lastUpdatedTimeStamp;

  @Column(name = "OTHR_CD_NM")
  private String otherCodeName;

  @Column(name = "RLT_CTGIND")
  private String relationshipToCategoryIndicator;  

  @Column(name = "USR_ID_IND")
  private String userDefinedLogicalIdIndicator;

  @Column(name = "USR_ID_NO")
  private Integer userDefinedIdLength;  

  public SystemMetaEntity() {
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
