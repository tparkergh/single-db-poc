package gov.ca.cwds.bre.interfaces.model;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class RuleDocumentationTest {
    
  
  @Test
  public void shouldCreateObjectUsingEmpyConstructor() throws Exception {
    RuleDocumentation rd = new RuleDocumentation();
    assertThat(rd, notNullValue());
  }
  
  @Test
  public void shouldCreateObjectUsingMapConstructor() throws Exception {
    Map<String, String> testMap = new HashMap<>();
    testMap.put(RuleDocumentation.DOC_NOTATION + "1", "documentation one");
    testMap.put(RuleDocumentation.DOC_NOTATION + "2", "documentation two");
    RuleDocumentation rd = new RuleDocumentation(testMap);
    assertThat(rd, notNullValue());
  }
   
  @Test
  public void shouldSetRuleDocumentFromMetaData() throws Exception {
    Map<String, Object> ruleMeta = new HashMap<>();
    ruleMeta.put(RuleDocumentation.DOC_NOTATION + "1", (Object) "Documentation one");
    ruleMeta.put(RuleDocumentation.DOC_NOTATION + "2", (Object) "Documentation two");
    
    RuleDocumentation rd = new RuleDocumentation();
    rd.setRuleDocumentFromRuleMetaData(ruleMeta);
    Map<String, String> docMap = rd.getRuleDocument();
    String row1 = docMap.get(RuleDocumentation.DOC_NOTATION + "1");
    assertEquals(row1, "Documentation one");
    String row2 = docMap.get(RuleDocumentation.DOC_NOTATION + "2");
    assertEquals(row2, "Documentation two");
  }

  @Test
  public void shouldSetRuleDocument() throws Exception {
    RuleDocumentation rd = new RuleDocumentation();
    Map<String, String> testMap = new HashMap<>();
    testMap.put(RuleDocumentation.DOC_NOTATION + "1", "documentation one");
    testMap.put(RuleDocumentation.DOC_NOTATION + "2", "documentation two");
    rd.setRuleDocument(testMap);
    Map<String, String> returnMap = rd.getRuleDocument();
    String row1 = returnMap.get(RuleDocumentation.DOC_NOTATION + "1");
    assertEquals(row1, "documentation one");
  }
  
}
