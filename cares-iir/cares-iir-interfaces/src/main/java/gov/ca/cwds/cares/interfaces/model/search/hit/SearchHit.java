package gov.ca.cwds.cares.interfaces.model.search.hit;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.model.ObjectBase;

/**
 * @author CWDS J Team
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
    @JsonSubTypes.Type(name = "person", value = PersonSearchHit.class)})
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class SearchHit extends ObjectBase implements Serializable {

  private static final long serialVersionUID = -3254906030686979013L;
  
  private Double score;
  private String source;
  
  public SearchHit() {
    // Default no-argument constructor.
  }
  
  public Double getScore() {
    return score;
  }
  
  public void setScore(Double score) {
    this.score = score;
  }
  
  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }
}
