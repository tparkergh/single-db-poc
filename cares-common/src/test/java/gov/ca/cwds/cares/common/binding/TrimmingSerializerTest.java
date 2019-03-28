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

public class TrimmingSerializerTest {
  JsonGenerator generator;
  SerializerProvider provider;
  TrimmingSerializer serializer;

  @Before
  public void setup(){
    generator = mock(JsonGenerator.class);
    provider = mock(SerializerProvider.class);
    serializer = new TrimmingSerializer();

  }

  @Test
  public void shouldReturnContentwhenNoExtraWhiteSpaceIsProvided() throws IOException {
    String content = "content";
    serializer.serialize(content, generator, provider);

    verify(generator).writeString(content);
  }

  @Test
  public void shouldReturnContentTrimmedOfWhiteSpaceWhenExtraWhiteSpaceIsProvidedAtEndOfWord() throws IOException {
    String content = "content    ";
    serializer.serialize(content, generator, provider);

    verify(generator).writeString("content");
  }

  @Test
  public void shouldReturnContentTrimmedOfWhiteSpaceWhenExtraWhiteSpaceIsProvidedAtBeginingOfWord() throws IOException {
    String content = "     content";
    serializer.serialize(content, generator, provider);

    verify(generator).writeString("content");
  }

  @Test
  public void shouldNotremoveWhiteSpaceBetweenWords() throws IOException {
    String content = "some content";
    serializer.serialize(content, generator, provider);

    verify(generator).writeString(content);
  }

  @Test
  public void shouldNotSerializeWhenContentIsNull() throws IOException {
    String content = null;
    serializer.serialize(content, generator, provider);

    verify(generator, never()).writeString(anyString());
  }
}
