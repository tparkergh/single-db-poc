package gov.ca.cwds.cares.interfaces.model.search.field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public enum SearchFieldName implements Serializable {

  PERSON_FIRST_NAME("person.first_name"),
  
  PERSON_LAST_NAME("person.last_name"),
  
  PERSON_PRIMARY_PHONR("person.primary_phone");
    
  private String value;
  
  private SearchFieldName(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
  
  public static Collection<String> getAllFieldNames() {
    Collection<String> allNames = new ArrayList<>();
    
    for (SearchFieldName name : SearchFieldName.values()) {
      allNames.add(name.getValue());
    }
    return allNames;
  }
}
