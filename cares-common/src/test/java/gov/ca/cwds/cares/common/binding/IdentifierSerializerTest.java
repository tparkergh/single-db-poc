package gov.ca.cwds.cares.common.binding;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.junit.Test;

public class IdentifierSerializerTest {
  private static final String base62Key = "5Y3vKVs0X5";
  private static final String base10Key = "0315-2076-8676-8002051";

  @Test
  public void shouldConvertBase62KeyToBase10Key() throws IOException {
    JsonGenerator generator = mock(JsonGenerator.class);
    SerializerProvider provider = mock(SerializerProvider.class);
    IdentifierSerializer serializer = new IdentifierSerializer();
    serializer.serialize(base62Key, generator, provider);
    verify(generator).writeString(base62Key);
    verify(generator).writeStringField("expanded_identifier", base10Key);
  }

  @Test
  public void doesNotWriteValueOutIfNull() throws IOException {
    JsonGenerator generator = mock(JsonGenerator.class);
    SerializerProvider provider = mock(SerializerProvider.class);
    IdentifierSerializer serializer = new IdentifierSerializer();
    serializer.serialize(null, generator, provider);
    verify(generator, never()).writeString(anyString());
  }
}