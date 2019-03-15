package gov.ca.cwds.cares.interfaces.model.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.common.model.ExecutionTimeRecorder;
import gov.ca.cwds.cares.common.model.ObjectBase;
import gov.ca.cwds.cares.interfaces.model.search.hit.SearchHit;

/**
 * @author CWDS J Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResults extends ObjectBase implements Serializable, ExecutionTimeRecorder {
  
  private static final long serialVersionUID = 4356596417435911062L;
  
  private Long executionTimeMillis;
  private Long totalHitCount;
  private Collection<? extends SearchHit> hits = new ArrayList<>();

  public SearchResults() {
    // Default no-argument constructor
  }
  
  public Collection<? extends SearchHit> getHits() {
    return hits;
  }

  public void setHits(Collection<? extends SearchHit> hits) {
    this.hits = hits;
  }
 
  public Long getTotalHitCount() {
    return totalHitCount;
  }

  public void setTotalHitCount(Long totalHitCount) {
    this.totalHitCount = totalHitCount;
  }

  public Long getExecutionTimeMillis() {
    return executionTimeMillis;
  }

  public void setExecutionTimeMillis(Long executionTimeMillis) {
    this.executionTimeMillis = executionTimeMillis;
  }
}
