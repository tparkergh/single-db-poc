package gov.ca.cwds.cares.interfaces.model.search.field;

import java.io.Serializable;

public class BooleanSearchField extends SearchField<Boolean> implements Serializable {
  
  private static final long serialVersionUID = -1662420627350649610L;

  public BooleanSearchField() {
    super();
  }
  
  public BooleanSearchField(String name, Boolean value) {
    super(name, value);
  }
}
