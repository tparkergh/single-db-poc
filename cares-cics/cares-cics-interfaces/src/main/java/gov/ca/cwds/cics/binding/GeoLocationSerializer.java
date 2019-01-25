package gov.ca.cwds.cics.binding;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * CWDS J Team
 */
public class GeoLocationSerializer extends StdSerializer<Double> {
  protected GeoLocationSerializer() {
    super(Double.class);
  }

  @Override
  public void serialize(Double value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if (value != null) {
      gen.writeString(new DecimalFormat("+0000.000000;-0000.000000").format(value));
    }
  }
}
