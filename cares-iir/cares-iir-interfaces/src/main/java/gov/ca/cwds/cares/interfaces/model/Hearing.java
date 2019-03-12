package gov.ca.cwds.cares.interfaces.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hearing implements Serializable {

    private static final long serialVersionUID = 440890092508367802L;

    private String identifier;
    private String attendeeDescription;
    private String citSubtCode;
    private Short cnthrgCode;
    private String specifiedCounty;
    private String courtDeptNumber;
    private String courtSummaryDocId;
    private String courtID;
    private LocalDate hearingDate;
    private LocalDate hearingTime;
    private String hearingAtntCode;
    private String hearingNTEDescription;
    private String judicialOffenseName;
    private Short judicialTLCode;
    private Short languageTPCode;
    private LocalDate nextHearingDate;
    private LocalDate nextHearingTime;
    public LocalDateTime lastUpdatedTimestamp;
    public String lastUpdatedID;
    private String ptHearingCode;

    public Hearing () {
        // Default no-argument constructor
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getAttendeeDescription() {
        return attendeeDescription;
    }

    public void setAttendeeDescription(String attendeeDescription) {
        this.attendeeDescription = attendeeDescription;
    }

    public String getCourtID() {return courtID; }

    public void setCourtID (String courtID ) {this.courtID = courtID;}

    //=============================
    public String getLastUpdateId() {
        return lastUpdatedID;
    }

    public void setLastUpdateId(String lastUpdateId) {
        this.lastUpdatedID = lastUpdateId;
    }

    public LocalDateTime getLastUpdateTimestamp() {
        return lastUpdatedTimestamp;
    }

    public void setLastUpdateTimestamp(LocalDateTime lastUpdateTimestamp) {
        this.lastUpdatedTimestamp = lastUpdateTimestamp;
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

