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
  
  private Collection<Match> mustMatch = new ArrayList<>();
  private Collection<Match> shouldMatch = new ArrayList<>();
  private Collection<Match> mustNotMatch = new ArrayList<>();
  
  public SearchQuery() {
    super();
  }

  public Collection<Match> getMustMatch() {
    return mustMatch;
  }
  
  public void addMustMatch(Match match) {
    this.mustMatch.add(match);
  }

  public void setMustMatch(Collection<Match> mustMatch) {
    this.mustMatch = mustMatch;
  }

  public Collection<Match> getMustNotMatch() {
    return mustNotMatch;
  }

  public void setMustNotMatch(Collection<Match> mustNotMatch) {
    this.mustNotMatch = mustNotMatch;
  }
  
  public void addMustNotMatch(Match match) {
    this.mustNotMatch.add(match);
  }

  public Collection<Match> getShouldMatch() {
    return shouldMatch;
  }

  public void setShouldMatch(Collection<Match> shouldMatch) {
    this.shouldMatch = shouldMatch;
  }
  
  public void addShouldMatch(Match match) {
    this.shouldMatch.add(match);
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
