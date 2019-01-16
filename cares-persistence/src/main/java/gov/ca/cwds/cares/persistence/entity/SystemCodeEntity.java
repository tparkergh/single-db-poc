
package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "SYS_CD_C")
@NamedQuery(name = "SystemCodeEntity.findByMetaName",
    query = "SELECT sc FROM SystemCodeEntity sc WHERE sc.fkToSystemMeta = ?1")
public class SystemCodeEntity implements Serializable {

  private static final long serialVersionUID = 6969398151287232182L;

  @Id
  @Column(name = "SYS_ID")
  private Integer systemId;

  @Column(name = "CATEGORYID")
  private Integer categoryId;

  @Column(name = "FKS_META_T")
  private String fkToSystemMeta;

  @Column(name = "INACTV_IND")
  private String inactiveIndicator;

  @Column(name = "LST_UPD_ID")
  private String lastUpdatedById;

  @Column(name = "LST_UPD_TS")
  private LocalDateTime lastUpdatedTimeStamp;

  @Column(name = "OTHER_CD")
  private String otherCode;

  @Column(name = "SHORT_DSC")
  private String shortDescription;

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

  public String getFkToSystemMeta() {
    return fkToSystemMeta;
  }

  public void setFkToSystemMeta(String fkToSystemMeta) {
    this.fkToSystemMeta = fkToSystemMeta;
  }

  public String getInactiveIndicator() {
    return inactiveIndicator;
  }

  public void setInactiveIndicator(String inactiveIndicator) {
    this.inactiveIndicator = inactiveIndicator;
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
