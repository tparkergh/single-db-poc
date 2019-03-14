package gov.ca.cwds.bre.interfaces.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.model.ObjectBase;

/**
 * @author CWDS J Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BreRequest extends ObjectBase {
  
  private String businessRuleSetName;  
  private List<BreRequestData> dataObjects = new ArrayList<>();
  
  public String getBusinessRuleSetName() {
    return businessRuleSetName;
  }

  public void setBusinessRuleSetName(String businessRuleSetName) {
    this.businessRuleSetName = businessRuleSetName;
  }

  public List<BreRequestData> getDataObjects() {
    return dataObjects;
  }

  public void setDataObjects(List<BreRequestData> dataObjects) {
    this.dataObjects = dataObjects;
  }
  
  public void addDataObject(BreRequestData dataObject) {
    this.dataObjects.add(dataObject);
  } 
}
