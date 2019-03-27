package gov.ca.cwds.cares.common.binding;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SnakeUpperCaseStrategyTest {

  SnakeUpperCaseStrategy strategy;

  @Before
  public void setup() {
    strategy = new SnakeUpperCaseStrategy();
  }

  @Test
  public void shouldReturnEmptyStringWhenInputIsEmptyString() {
    String originalWord = "";
    String translatedWord = strategy.translate(originalWord);
    assertEquals(originalWord, translatedWord);
  }

  @Test
  public void shouldReturnNullWhenInputIsNull() {
    String originalWord = null;
    String translatedWord = strategy.translate(originalWord);
    assertEquals(originalWord, translatedWord);
  }

  @Test
  public void shouldReturnUppercaseWordWhenInputIsSingleWord() {
    String originalWord = "java";
    String translatedWord = strategy.translate(originalWord);
    assertEquals("JAVA", translatedWord);
  }

  @Test
  public void shouldReturnUnderscoreSeperatedWordsWhenInputIsMultipleCamelCaseWords() {
    String originalWord = "myClass";
    String translatedWord = strategy.translate(originalWord);
    assertEquals("MY_CLASS", translatedWord);
  }
}
