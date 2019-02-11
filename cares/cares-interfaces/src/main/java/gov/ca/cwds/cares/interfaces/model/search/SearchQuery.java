package gov.ca.cwds.cares.interfaces.model.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cares.interfaces.model.search.match.Match;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SearchQuery implements Serializable {
  
  private static final long serialVersionUID = -4319859951956866212L;
  
  private Collection<Match> musts = new ArrayList<>();
  private Collection<Match> shoulds = new ArrayList<>();
  private Collection<Match> mustNots = new ArrayList<>();
  
  public SearchQuery() {
    super();
  }

  public Collection<Match> getMusts() {
    return musts;
  }
  
  public void addMust(Match match) {
    this.musts.add(match);
  }

  public void setMusts(Collection<Match> musts) {
    this.musts = musts;
  }

  public Collection<Match> getMustNots() {
    return mustNots;
  }

  public void setMustNots(Collection<Match> mustNots) {
    this.mustNots = mustNots;
  }
  
  public void addMustNot(Match match) {
    this.mustNots.add(match);
  }

  public Collection<Match> getShoulds() {
    return shoulds;
  }

  public void setShoulds(Collection<Match> shoulds) {
    this.shoulds = shoulds;
  }
  
  public void addShould(Match match) {
    this.shoulds.add(match);
  }
  
  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }
}
