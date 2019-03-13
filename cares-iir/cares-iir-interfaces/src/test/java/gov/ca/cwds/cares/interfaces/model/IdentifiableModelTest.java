package gov.ca.cwds.cares.interfaces.model;

import static org.junit.Assert.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

public class IdentifiableModelTest {
  private String base10_identifier;
  private String base62_identifier;

  IdentifiableModel entity;

  @Before
  public void setup(){
    base10_identifier = "0YIPkZU0S0";
    base62_identifier = "0031-4206-2756-0001736";
    entity = new IdentifiableModel();
  }

  @Test
  public void shouldSerializeIdFields() throws JsonProcessingException {
    entity.setIdentifier(base10_identifier);

    String actualJson = new ObjectMapper().writeValueAsString(entity);
    String expectedJson = "{\"identifier\":\"" + base10_identifier + "\",\"expanded_identifier\":\"" + base62_identifier + "\"}";
    assertEquals(expectedJson, actualJson);
  }

  @Test
  public void shouldNotSerializeIdFieldsWhenIDisNull() throws JsonProcessingException {
    base10_identifier = null;
    entity.setIdentifier(base10_identifier);

    String actualJson = "";
    String expectedJson = "";
    assertEquals(expectedJson, actualJson);
  }
}