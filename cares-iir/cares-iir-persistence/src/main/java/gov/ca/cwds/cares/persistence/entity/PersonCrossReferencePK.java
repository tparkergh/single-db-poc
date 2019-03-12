package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * CWDS J Team
 */
public class PersonCrossReferencePK implements Serializable {
  private static final long serialVersionUID = 4138049536599812353L;

  private String personId;

  private String xrefId;

  private XrefCode xrefCode;

  public PersonCrossReferencePK() {
  }

  public PersonCrossReferencePK(String personId, String xrefId, XrefCode xrefCode) {
    this.personId = personId;
    this.xrefId = xrefId;
    this.xrefCode = xrefCode;
  }

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
