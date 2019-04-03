package gov.ca.cwds.cares.rest.systemcode;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.ca.cwds.cares.rest.RestFunctionalTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SystemCodesTest extends RestFunctionalTestBase {
  
  private static final String REQUEST_PATH = "/system_codes"; 
  
  private static final String SORT_FIELD = "short_description";

  @Test
  public void whenCallSystemCodesForMeta_thenSuccessResponse() throws Exception {
    ArrayList<HashMap <String, String>> actualSystemCodes = getSystemCodes("COL_RELC");    
    ArrayList<HashMap <String, String>> expectedSystemCodes = Lists.newArrayList(actualSystemCodes);
    Collections.sort(expectedSystemCodes, new SystemCodeListComparator());

    // Make sure system codes are sorted by SORT_FIELD
    Assert.assertEquals(expectedSystemCodes, actualSystemCodes);
  }

  private ArrayList <HashMap <String, String>> getSystemCodes(String metaName) throws Exception {
    Response response = given()
        .contentType(ContentType.JSON)
        .when()
        .get(getRestServiceUrl() + REQUEST_PATH + "/" + metaName);
    
    Assert.assertEquals(200, response.getStatusCode());
    String responseJson  = response.asString();
    ObjectMapper mapper = new ObjectMapper();
    ArrayList <HashMap <String, String>> systemCodes = mapper.readValue(responseJson, ArrayList.class);
    return systemCodes;
  }
  
  private static final class SystemCodeListComparator implements Comparator<HashMap<String, String>> {

    @Override
    public int compare(HashMap<String, String> s1, HashMap<String, String> s2) {
      String leftSortValue = s1.get(SORT_FIELD);
      String rightSortValue = s2.get(SORT_FIELD);
      return StringUtils.compareIgnoreCase(leftSortValue, rightSortValue);      
    }
  }
}
