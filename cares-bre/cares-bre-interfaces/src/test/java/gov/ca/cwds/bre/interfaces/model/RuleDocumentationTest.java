package gov.ca.cwds.bre.interfaces.model;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Test;

public class RuleDocumentationTest {
    
  
  @Test
  public void shouldCreateObjectUsingEmpyConstructor() throws Exception {
    RuleDocumentation rd = new RuleDocumentation();
    assertThat(rd, notNullValue());
  }
  
  @Test
  public void shouldCreateObjectUsingMapConstructor() throws Exception {
    TreeMap<String, String> testMap = new TreeMap<>();
    testMap.put("doc_1", "documentation one");
    testMap.put("doc_2", "documentation two");
    RuleDocumentation rd = new RuleDocumentation();
    rd.setName("test-name");
    rd.setDocumentation(testMap);
    assertThat(rd, notNullValue());
  }
   
  @Test
  public void shouldSetRuleDocument() throws Exception {
    RuleDocumentation rd = new RuleDocumentation();
    rd.addDocumentation("doc_1", "documentation one");
    rd.addDocumentation("doc_2", "documentation two");
    Map<String, String> returnMap = rd.getDocumentation();
    String row1 = returnMap.get("doc_1");
    assertEquals(row1, "documentation one");
  }
  
}
