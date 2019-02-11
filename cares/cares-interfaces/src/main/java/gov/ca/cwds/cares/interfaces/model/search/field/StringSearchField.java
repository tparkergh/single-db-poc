package gov.ca.cwds.cares.interfaces.model.search.field;

import java.io.Serializable;

public class StringSearchField extends SearchField<String> implements Serializable {
  
  private static final long serialVersionUID = -7592535826317879926L;

  public StringSearchField() {
    super();
  }

  public StringSearchField(String name, String value) {
    super(name, value);
  }
}
