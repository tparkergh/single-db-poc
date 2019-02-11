package gov.ca.cwds.cares.interfaces.model.search.field;

import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class DateSearchField extends SearchField<LocalDate> implements Serializable {
  
  private static final long serialVersionUID = 5302640253851437461L;

  public DateSearchField() {
    super();
  }
  
  public DateSearchField(String name, LocalDate value) {
    super(name, value);
  }

  @Override
  @JsonSerialize(using = LocalDateSerializer.class)
  public LocalDate getValue() {
    return super.getValue();
  }

  @Override
  @JsonDeserialize(using = LocalDateDeserializer.class)
  public void setValue(LocalDate value) {
    super.setValue(value);
  }
}
