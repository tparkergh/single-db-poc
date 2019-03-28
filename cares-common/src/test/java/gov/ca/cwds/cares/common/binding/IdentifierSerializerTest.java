package gov.ca.cwds.cares.common.binding;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class IdentifierSerializerTest {
  private static final String base62Key = "5Y3vKVs0X5";
  private static final String base10Key = "0315-2076-8676-8002051";
  private JsonGenerator generator;
  private SerializerProvider provider;
  private IdentifierSerializer serializer;

  @Before
  public void setup(){
    generator = mock(JsonGenerator.class);
    provider = mock(SerializerProvider.class);
    serializer = new IdentifierSerializer();
  }

  @Test
  public void shouldConvertBase62KeyToBase10Key() throws IOException {
    serializer.serialize(base62Key, generator, provider);
    verify(generator).writeString(base62Key);
    verify(generator).writeStringField("expanded_identifier", base10Key);
  }

  @Test
  public void doesNotWriteValueOutIfNull() throws IOException {
    serializer.serialize(null, generator, provider);
    verify(generator, never()).writeString(anyString());
  }
}