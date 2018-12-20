package gov.ca.cwds.cics.interfaces.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import gov.ca.cwds.cics.interfaces.binding.SnakeUpperCaseStrategy;

/**
 * CWDS J Team
 */
@JsonNaming(SnakeUpperCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientData implements Serializable {

  private static final long serialVersionUID = 1239981216058087475L;
  
  private String identifier;
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate birthDt;
  private String chldCltB;
  private String comFstNm;
  private String comLstNm;
  private String comMidNm;
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate creatnDt;
  private String drvLicNo;
  private String genderCd;
  private Integer nameTpc;
  private String ssNo;
  private String sufxTldsc;  
  private String estDobCd;
  private String emailAddr;
  private String clIndxNo;
  private Integer clntSoc;
  private String soUdCd;
  private Integer clntGic;
  private Integer clntGec;  
  private String txnHdrStaffId;  
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss.SSSSSS")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime lstUpdTs;  
  
  /**
   * No-argument constructor.
   */
  public ClientData() {
    // No-argument constructor
  }
  
  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public LocalDate getBirthDt() {
    return birthDt;
  }

  public void setBirthDt(LocalDate birthDt) {
    this.birthDt = birthDt;
  }

  public String getChldCltB() {
    return chldCltB;
  }

  public void setChldCltB(String chldCltB) {
    this.chldCltB = chldCltB;
  }

  public String getComFstNm() {
    return comFstNm;
  }

  public void setComFstNm(String comFstNm) {
    this.comFstNm = comFstNm;
  }

  public String getComLstNm() {
    return comLstNm;
  }

  public void setComLstNm(String comLstNm) {
    this.comLstNm = comLstNm;
  }

  public String getComMidNm() {
    return comMidNm;
  }

  public void setComMidNm(String comMidNm) {
    this.comMidNm = comMidNm;
  }

  public LocalDate getCreatnDt() {
    return creatnDt;
  }

  public void setCreatnDt(LocalDate creatnDt) {
    this.creatnDt = creatnDt;
  }

  public String getDrvLicNo() {
    return drvLicNo;
  }

  public void setDrvLicNo(String drvLicNo) {
    this.drvLicNo = drvLicNo;
  }

  public String getGenderCd() {
    return genderCd;
  }

  public void setGenderCd(String genderCd) {
    this.genderCd = genderCd;
  }

  public Integer getNameTpc() {
    return nameTpc;
  }

  public void setNameTpc(Integer nameTpc) {
    this.nameTpc = nameTpc;
  }

  public String getSsNo() {
    return ssNo;
  }

  public void setSsNo(String ssNo) {
    this.ssNo = ssNo;
  }

  public String getSufxTldsc() {
    return sufxTldsc;
  }

  public void setSufxTldsc(String sufxTldsc) {
    this.sufxTldsc = sufxTldsc;
  }

  public String getEstDobCd() {
    return estDobCd;
  }

  public void setEstDobCd(String estDobCd) {
    this.estDobCd = estDobCd;
  }

  public String getEmailAddr() {
    return emailAddr;
  }

  public void setEmailAddr(String emailAddr) {
    this.emailAddr = emailAddr;
  }

  public String getClIndxNo() {
    return clIndxNo;
  }

  public void setClIndxNo(String clIndxNo) {
    this.clIndxNo = clIndxNo;
  }

  public Integer getClntSoc() {
    return clntSoc;
  }

  public void setClntSoc(Integer clntSoc) {
    this.clntSoc = clntSoc;
  }

  public String getSoUdCd() {
    return soUdCd;
  }

  public void setSoUdCd(String soUdCd) {
    this.soUdCd = soUdCd;
  }

  public Integer getClntGic() {
    return clntGic;
  }

  public void setClntGic(Integer clntGic) {
    this.clntGic = clntGic;
  }

  public Integer getClntGec() {
    return clntGec;
  }

  public void setClntGec(Integer clntGec) {
    this.clntGec = clntGec;
  }
  
  public String getTxnHdrStaffId() {
    return txnHdrStaffId;
  }

  public void setTxnHdrStaffId(String txnHdrStaffId) {
    this.txnHdrStaffId = txnHdrStaffId;
  }
  
  public LocalDateTime getLstUpdTs() {
    return lstUpdTs;
  }

  public void setLstUpdTs(LocalDateTime lstUpdTs) {
    this.lstUpdTs = lstUpdTs;
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
