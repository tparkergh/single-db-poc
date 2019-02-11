package gov.ca.cwds.cares.interfaces.model.search.match;

import java.io.Serializable;
import gov.ca.cwds.cares.interfaces.model.search.field.SearchField;

public class RangeMatch extends Match implements Serializable {

  private static final long serialVersionUID = 4227278240512325951L;
  
  private SearchField<?> from;
  private SearchField<?> to;

  public RangeMatch() {
    super();
  }
  
  public RangeMatch(SearchField<?> from, SearchField<?> to) {
    super();
    this.from = from;
    this.to = to;
  }

  public SearchField<?> getFrom() {
    return from;
  }

  public void setFrom(SearchField<?> from) {
    this.from = from;
  }

  public SearchField<?> getTo() {
    return to;
  }

  public void setTo(SearchField<?> to) {
    this.to = to;
  }
}
