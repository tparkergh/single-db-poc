package gov.ca.cwds.cares.geo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gov.ca.cwds.cares.common.binding.TrimmingSerializer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * CWDS J Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoAddress {
  @JsonSerialize(using = TrimmingSerializer.class)
  private String streetAddress;
  @JsonSerialize(using = TrimmingSerializer.class)
  private String city;
  @JsonSerialize(using = TrimmingSerializer.class)
  private String state;
  @JsonSerialize(using = TrimmingSerializer.class)
  private String zip;
  @JsonSerialize(using = TrimmingSerializer.class)
  private String zipExtension;
  private double longitude;
  private double lattitude;
  private boolean deliverable;

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getZipExtension() {
    return zipExtension;
  }

  public void setZipExtension(String zipExtension) {
    this.zipExtension = zipExtension;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public double getLattitude() {
    return lattitude;
  }

  public void setLattitude(double lattitude) {
    this.lattitude = lattitude;
  }

  public boolean isDeliverable() {
    return deliverable;
  }

  public void setDeliverable(boolean deliverable) {
    this.deliverable = deliverable;
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
