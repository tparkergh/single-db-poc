package gov.ca.cwds.cares.interfaces.model.search.match;

import java.io.Serializable;
import gov.ca.cwds.cares.interfaces.model.search.field.SearchField;

public class ExactMatch extends Match implements Serializable {

  private static final long serialVersionUID = -1238246813125339584L;
  
  private SearchField field;

  public ExactMatch() {
    super();
  }
  
  public ExactMatch(SearchField field) {
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
