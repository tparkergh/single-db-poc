package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Entity
@Table(name = "HEARNG_T")
public class HearingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "IDENTIFIER")
    private String id;

    @Column(name = "ATNDEE_DSC")
    private String attendeeDescription;

    @Column(name = "CIT_SUBT_B")
    private String citSubtCode;

    @Column(name = "CNTHRG_C")
    private Short cnthrgCode;

    @Column(name = "CNTY_SPCFD")
    private String specifiedCounty;

    @Column(name = "CT_DEPT_NO")
    private String courtDeptNum;

    @Column(name = "CT_SUM_DOC")
    private String courtSummaryDocId;

    @Id
    @Column(name = "FKCOURT_T")
    private String courtID;

    //@Type(type = "LocalDate")
    @Column(name = "HEARING_DT")
    private LocalDate hearingDate;

    @Column(name = "HEARING_TM")
    private LocalDate hearingTime;

    @Column(name = "HRG_ATNT_B")
    private String hearingAtntCode;

    @Column(name = "HR_NTE_DSC")
    private String hearingNTEDescription;

    @Column(name = "JDCL_OFFNM")
    private String judicialOffenseName;

    @Column(name = "JDCL_TLC")
    private Short judicialTLCode;

    @Column(name = "LANG_TPC")
    private Short languageTPCode;

    @Column(name = "LST_UPD_TS", nullable = false)
    public Timestamp lastUpdatedTS;

    @Column(name = "LST_UPD_ID", nullable = false, length = 3)
    public String lastUpdatedID;

    //@javax.persistence.Type(type = "LocalDate")
    @Column(name = "NEXT_HR_DT")
    private LocalDate nextHearingDate;

    //@Type (type = "LocalDate")
    @Column(name = "NEXT_HR_TM")
    private LocalDate nextHearingTime;

    @Column(name = "PT_HRG_T_B")
    private String ptHearingCode;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

}
