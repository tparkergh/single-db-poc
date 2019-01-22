package gov.ca.cwds.cares.common.binding;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 * CWDS J Team
 */
public class TrimmingSerializer extends StdSerializer<String> {
  protected TrimmingSerializer() {
    super(String.class);
  }

  @Override
  public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if (value != null) {
      gen.writeString(value.trim());
    }
  }
}
