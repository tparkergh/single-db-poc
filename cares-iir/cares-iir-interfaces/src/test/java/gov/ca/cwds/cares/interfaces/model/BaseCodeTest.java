package gov.ca.cwds.cares.interfaces.model;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import gov.ca.cwds.cares.common.model.ObjectBase;

public class BaseCodeTest {
  @Test
  public void toStringShouldPrintPropertiesOfDerivedClass(){
    ChildModel child = new ChildModel("foo", "bar");
    String toString = child.toString();
    assertTrue(toString.contains("foo"));
    assertTrue(toString.contains("bar"));
  }

  @Test
  public void equalsShouldReturnTrueWhenObjectFieldsAreTheSame(){
    ChildModel child1 = new ChildModel("foo", "bar");
    ChildModel child2 = new ChildModel("foo", "bar");

    assertTrue(child1.equals(child2));
  }

  @Test
  public void equalsShouldReturnFalseWhenObjectFieldsAreNotTheSame(){
    ChildModel child1 = new ChildModel("foo", "bar");
    ChildModel child2 = new ChildModel("fin", "baz");

    assertFalse(child1.equals(child2));
  }

  @Test
  public void hashCodeShouldReturnSameHashCodeWhenValuesAreTheSame(){
    ChildModel child1 = new ChildModel("foo", "bar");
    ChildModel child2 = new ChildModel("foo", "bar");

    assertEquals(child1.hashCode(), child2.hashCode());
  }

  @Test
  public void hashCodeShouldReturnDifferentHashCodeWhenValuesAreDifferent(){
    ChildModel child1 = new ChildModel("foo", "bar");
    ChildModel child2 = new ChildModel("fin", "baz");

    assertNotEquals(child1.hashCode(), child2.hashCode());
  }

  class ChildModel extends ObjectBase{
    private String foo;
    private String bar;

    public ChildModel(String foo, String bar){
      this.foo = foo;
      this.bar = bar;
    }

    public String getFoo(){
      return foo;
    }
    public String getBar(){
      return bar;
    }
  }
}
