package gov.ca.cwds.cares.common.binding;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import gov.ca.cwds.cares.common.identifier.CmsKeyIdGenerator;
import java.io.IOException;

public class IdentifierSerializer extends StdSerializer<String> {
  public IdentifierSerializer() {
    super(String.class);
  }

  @Override
  public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if (value != null) {
      gen.writeString(value);
      String converted = CmsKeyIdGenerator.getUIIdentifierFromKey(value);
      gen.writeStringField("expanded_identifier", converted.trim());
    }
  }
}
