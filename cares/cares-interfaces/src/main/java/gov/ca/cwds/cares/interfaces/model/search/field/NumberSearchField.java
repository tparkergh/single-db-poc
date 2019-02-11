package gov.ca.cwds.cares.interfaces.model.search.field;

import java.io.Serializable;

public class NumberSearchField extends SearchField<Number> implements Serializable {
  
  private static final long serialVersionUID = -938746256259654248L;

  public NumberSearchField() {
    super();
  }
  
  public NumberSearchField(String name, Number value) {
    super(name, value);
  }
}
