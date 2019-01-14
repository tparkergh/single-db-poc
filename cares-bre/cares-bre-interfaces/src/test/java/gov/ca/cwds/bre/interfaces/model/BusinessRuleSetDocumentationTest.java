package gov.ca.cwds.bre.interfaces.model;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.junit.Test;

public class BusinessRuleSetDocumentationTest {

  @Test
  public void shouldCreateObjectUsingEmptyContructor() throws Exception {
    BusinessRuleSetDocumentation brd = new BusinessRuleSetDocumentation();
    assertThat("BusinessRuleDocumentation is not null", brd, notNullValue());
  }
  
  @Test
  public void shouldSetGetBusinessRuleName() throws Exception {
    String businessRuleName = "ClientAddress";
    BusinessRuleSetDocumentation brd = new BusinessRuleSetDocumentation();
    brd.setBusinessRuleSetName(businessRuleName);
    assertEquals(brd.getBusinessRuleSetName(), businessRuleName);
    
  }
  
  @Test
  public void shouldSetGetDataClassName() throws Exception {
    String dataClassName = "data class";
    BusinessRuleSetDocumentation brd = new BusinessRuleSetDocumentation();
    brd.setDataClassName(dataClassName);
    assertEquals(brd.getDataClassName(), dataClassName);
    
  }
  
  @Test 
  public void shouldSetGetRuleDocumentation() throws Exception {
    TreeMap<String, String> testMap = new TreeMap<>();
    testMap.put("doc_1", "documentation one");
    testMap.put("doc_2", "documentation two");
    RuleDocumentation rd = new RuleDocumentation();
    rd.setDocumentation(testMap);
    List<RuleDocumentation> rdList = new ArrayList<>();
    rdList.add(rd);
    BusinessRuleSetDocumentation brd = new BusinessRuleSetDocumentation();
    brd.setRules(rdList);    
    assertEquals(brd.getRules(), rdList);
    
  }
}
