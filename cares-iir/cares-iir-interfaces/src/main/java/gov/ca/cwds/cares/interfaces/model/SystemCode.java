package gov.ca.cwds.cares.interfaces.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SystemCode extends BaseModel implements Serializable {

  private static final long serialVersionUID = 5237768756638520258L;

  private Integer systemId;
  private String metaName;
  private String shortDescription;
  private Integer categoryId;  
  private String inactiveIndicator;
  private String userDefinedLogicalId;
  private String longDescription;
  private String lastUpdatedById;
  private LocalDateTime lastUpdatedTimeStamp;
  private String otherCode;  
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
