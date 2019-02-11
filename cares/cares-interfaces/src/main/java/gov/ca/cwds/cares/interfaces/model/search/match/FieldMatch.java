package gov.ca.cwds.cares.interfaces.model.search.match;

import java.io.Serializable;
import gov.ca.cwds.cares.interfaces.model.search.field.SearchField;

public class FieldMatch extends Match implements Serializable {

  private static final long serialVersionUID = -1238246813125339584L;
  
  private SearchField field;

  public FieldMatch() {
    super();
  }
  
  public FieldMatch(SearchField field) {
    super();    
    this.field = field;
  }

  public SearchField getField() {
    return field;
  }

  public void setField(SearchField field) {
    this.field = field;
  }
}
