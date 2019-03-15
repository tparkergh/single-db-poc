
package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import gov.ca.cwds.cares.common.model.ObjectBase;

@Entity
@Table(name = "SYS_CD_C")
@NamedQuery(name = "SystemCodeEntity.findByMetaName",
    query = "SELECT sc FROM SystemCodeEntity sc WHERE sc.metaName = ?1")
public class SystemCodeEntity extends ObjectBase implements Serializable {

  private static final long serialVersionUID = 6969398151287232182L;

  @Id
  @Column(name = "SYS_ID")
  private Integer systemId;

  @Column(name = "FKS_META_T")
  private String metaName;
  
  @Column(name = "SHORT_DSC")
  private String shortDescription;
  
  @Column(name = "CATEGORYID")
  private Integer categoryId;

  @Column(name = "INACTV_IND")
  private String inactiveIndicator;

  @Column(name = "LGC_ID")
  private String userDefinedLogicalId;

  @Column(name = "LONG_DSC")
  private String longDescription;

  @Column(name = "LST_UPD_ID")
  private String lastUpdatedById;

  @Column(name = "LST_UPD_TS")
  private LocalDateTime lastUpdatedTimeStamp;

  @Column(name = "OTHER_CD")
  private String otherCode;

  @Column(name = "THIRD_ID")
  private String thirdId;


  public Integer getSystemId() {
    return systemId;
  }

  public void setSystemId(Integer systemId) {
    this.systemId = systemId;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getInactiveIndicator() {
    return inactiveIndicator;
  }

  public void setInactiveIndicator(String inactiveIndicator) {
    this.inactiveIndicator = inactiveIndicator;
  }


  public String getUserDefinedLogicalId() {
    return userDefinedLogicalId;
  }

  public void setUserDefinedLogicalId(String userDefinedLogicalId) {
    this.userDefinedLogicalId = userDefinedLogicalId;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
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

  public String getOtherCode() {
    return otherCode;
  }

  public void setOtherCode(String otherCode) {
    this.otherCode = otherCode;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getThirdId() {
    return thirdId;
  }

  public void setThirdId(String thirdId) {
    this.thirdId = thirdId;
  }

  public String getMetaName() {
    return metaName;
  }

  public void setMetaName(String metaName) {
    this.metaName = metaName;
  }
}
