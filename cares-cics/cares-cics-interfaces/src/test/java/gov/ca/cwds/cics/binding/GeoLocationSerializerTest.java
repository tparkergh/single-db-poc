package gov.ca.cwds.cics.binding;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class GeoLocationSerializerTest {
  private JsonGenerator generator;
  private SerializerProvider provider;
  private GeoLocationSerializer serializer;

  @Before
  public void setup(){
    generator = mock(JsonGenerator.class);
    provider = mock(SerializerProvider.class);
    serializer = new GeoLocationSerializer();
  }

  @Test
  public void shouldPadWithZerosWhenNotEnoughDigitsAreGiven() throws IOException {
    Double coords = 1D;
    serializer.serialize(coords, generator, provider);
    verify(generator).writeString("+0001.000000");
  }

  @Test
  public void shouldKeepExtraDigits() throws IOException {
    Double coords = 12345678903456D;
    serializer.serialize(coords, generator, provider);
    verify(generator).writeString("+12345678903456.000000");
  }

  @Test
  public void shouldTrimLeadingZeros() throws IOException {
    Double coords = 0001234D;
    serializer.serialize(coords, generator, provider);
    verify(generator).writeString("+1234.000000");
  }

  @Test
  public void shouldFormatDecimal() throws IOException {
    Double coords = 1.1D;
    serializer.serialize(coords, generator, provider);
    verify(generator).writeString("+0001.100000");
  }

  @Test
  public void shouldLimitDecimalTo6NumbersAndNotRoundUpWhen7thDigitIs5OrLower() throws IOException {
    Double coords = 1.1234564890;
    serializer.serialize(coords, generator, provider);
    verify(generator).writeString("+0001.123456");
  }

  @Test
  public void shouldLimitDecimalTo6NumbersAndRoundUpWhen7thDigitIs5OrAbove() throws IOException {
    Double coords = 1.1234565890;
    serializer.serialize(coords, generator, provider);
    verify(generator).writeString("+0001.123457");
  }

  @Test
  public void shouldFormatNegativeValues() throws IOException {
    Double coords = -1D;
    serializer.serialize(coords, generator, provider);
    verify(generator).writeString("-0001.000000");
  }


  @Test
  public void shouldNotFormatWhenCoordsAreNull() throws IOException {
    Double coords = null;
    serializer.serialize(coords, generator, provider);
    verify(generator, never()).writeString(anyString());
  }

}
