package gov.ca.cwds.cares.interfaces.model.search.field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public enum SearchFieldName implements Serializable {

  PERSON_FIRST_NAME("person.first_name"),
  
  PERSON_LAST_NAME("person.last_name"),
  
  PERSON_PRIMARY_PHONR("person.primary_phone"),
  
  PERSON_DOB("person.dob");
  
  private String name;
  
  private SearchFieldName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
  
  public static Collection<String> getAllFieldNames() {
    Collection<String> allNames = new ArrayList<>();
    
    for (SearchFieldName name : SearchFieldName.values()) {
      allNames.add(name.getName());
    }
    return allNames;
  }
}
