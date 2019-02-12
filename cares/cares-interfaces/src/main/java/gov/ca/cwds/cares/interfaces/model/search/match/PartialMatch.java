package gov.ca.cwds.cares.interfaces.model.search.match;

import java.io.Serializable;
import gov.ca.cwds.cares.interfaces.model.search.field.SearchField;

public class PartialMatch extends Match implements Serializable {

  private static final long serialVersionUID = 686933963187194006L;
  
  private SearchField field;

  public PartialMatch() {
    super();
  }
  
  public PartialMatch(SearchField field) {
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
