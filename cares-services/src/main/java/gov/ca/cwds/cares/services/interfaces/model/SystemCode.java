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
public class SystemCode implements Serializable {

  private static final long serialVersionUID = 5237768756638520258L;

  private Integer systemId;
  private Integer categoryId;
  private String fkToSystemMeta;
  private String inactiveIndicator;
  private String lastUpdatedById;
  private LocalDateTime lastUpdatedTimeStamp;
  private String otherCode;
  private String shortDescription;
  private String thirdId;

  public SystemCode() {
    // Default no-argument constructor
  }

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
