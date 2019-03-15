package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * CWDS J Team
 */
@Entity
@Table(name = "PRS_XRFT")
@IdClass(PersonCrossReferencePK.class)
public class PersonCrossReferenceEntity implements Serializable {
  private static final long serialVersionUID = -8943502337788920742L;

  @Id
  @Column(name = "FKPERSON_T")
  private String personId;

  @Id
  @Column(name = "CWS_XRF_ID")
  private String xrefId;

  @Id
  @Column(name = "CWS_XRF_CD")
  private XrefCode xrefCode;

  @Column(name = "CREATN_TS")
  private LocalDateTime creationTimestamp;

  @Column(name = "LST_UPD_ID")
  private String lastUpdateId;

  @Column(name = "LST_UPD_TS")
  private LocalDateTime lastUpdateTimestamp;

  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public String getXrefId() {
    return xrefId;
  }

  public void setXrefId(String xrefId) {
    this.xrefId = xrefId;
  }

  public XrefCode getXrefCode() {
    return xrefCode;
  }

  public void setXrefCode(XrefCode xrefCode) {
    this.xrefCode = xrefCode;
  }

  public LocalDateTime getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(LocalDateTime creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
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
