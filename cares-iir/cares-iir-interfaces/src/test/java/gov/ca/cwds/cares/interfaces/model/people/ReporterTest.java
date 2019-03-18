package gov.ca.cwds.cares.interfaces.model.people;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class ReporterTest {

  @Test
  public void shouldContructObject() throws Exception {
    Reporter reporter = new Reporter();
    assertNotNull(reporter);
  }

  @Test
  public void testThatGettersSettersAreCorrect() throws Exception {
    String identifier = "12345678ABC";
    String firstName = "first name";
    String lastName = "last name";
    Long primaryPhone = 1112223333L;

    Reporter reporter = new Reporter();

    reporter.setIdentifier(identifier);
    reporter.setFirstName(firstName);
    reporter.setLastName(lastName);
    reporter.setPrimaryPhoneNumber(primaryPhone);
    assertEquals(reporter.getIdentifier(), identifier);
    assertEquals(reporter.getFirstName(), firstName);
    assertEquals(reporter.getLastName(), lastName);
    assertEquals(reporter.getPrimaryPhoneNumber(), primaryPhone);

  }
}
