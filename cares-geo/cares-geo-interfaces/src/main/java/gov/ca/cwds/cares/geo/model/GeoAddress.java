package gov.ca.cwds.cares.geo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gov.ca.cwds.cares.common.binding.TrimmingSerializer;
import gov.ca.cwds.cares.common.model.ObjectBase;

/**
 * CWDS J Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoAddress extends ObjectBase {
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
}
