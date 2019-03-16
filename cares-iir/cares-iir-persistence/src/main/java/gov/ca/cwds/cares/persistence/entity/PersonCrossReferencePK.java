package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;
import gov.ca.cwds.cares.common.model.ObjectBase;

/**
 * CWDS J Team
 */
public class PersonCrossReferencePK extends ObjectBase implements Serializable {
  private static final long serialVersionUID = 4138049536599812353L;

  private String personId;

  private String xrefId;

  private String xrefCode;

  public PersonCrossReferencePK() {
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

  public String getXrefCode() {
    return xrefCode;
  }

  public void setXrefCode(String xrefCode) {
    this.xrefCode = xrefCode;
  }
}
