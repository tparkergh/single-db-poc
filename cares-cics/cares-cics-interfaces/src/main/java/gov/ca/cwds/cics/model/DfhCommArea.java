package gov.ca.cwds.cics.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import gov.ca.cwds.cares.common.binding.SnakeUpperCaseStrategy;
import gov.ca.cwds.cares.common.model.ObjectBase;

/**
 * CWDS J Team
 */
@JsonNaming(SnakeUpperCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DfhCommArea extends ObjectBase implements Serializable {

  private static final long serialVersionUID = -5334090942331411606L;
  
  private Integer progReturnCode;  
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss.SSSSSS")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime apiTimestamp;  
  private String errorFocusTableName;
  private String errorMsgCode;
  private String errorMsgType;
  private String errorMsgRsnCode;
  private String errorFieldTest;
  private String additionalDesc;
  private String errorMsgPart1;
  private String errorMsgPart2;
  
  public DfhCommArea() {
    // Default constructor
  }

  public Integer getProgReturnCode() {
    return progReturnCode;
  }

  public void setProgReturnCode(Integer progReturnCode) {
    this.progReturnCode = progReturnCode;
  }

  public LocalDateTime getApiTimestamp() {
    return apiTimestamp;
  }

  public void setApiTimestamp(LocalDateTime apiTimestamp) {
    this.apiTimestamp = apiTimestamp;
  }

  public String getErrorFocusTableName() {
    return errorFocusTableName;
  }

  public void setErrorFocusTableName(String errorFocusTableName) {
    this.errorFocusTableName = errorFocusTableName;
  }

  public String getErrorMsgCode() {
    return errorMsgCode;
  }

  public void setErrorMsgCode(String errorMsgCode) {
    this.errorMsgCode = errorMsgCode;
  }

  public String getErrorMsgType() {
    return errorMsgType;
  }

  public void setErrorMsgType(String errorMsgType) {
    this.errorMsgType = errorMsgType;
  }

  public String getErrorMsgRsnCode() {
    return errorMsgRsnCode;
  }

  public void setErrorMsgRsnCode(String errorMsgRsnCode) {
    this.errorMsgRsnCode = errorMsgRsnCode;
  }

  public String getErrorFieldTest() {
    return errorFieldTest;
  }

  public void setErrorFieldTest(String errorFieldTest) {
    this.errorFieldTest = errorFieldTest;
  }

  public String getAdditionalDesc() {
    return additionalDesc;
  }

  public void setAdditionalDesc(String additionalDesc) {
    this.additionalDesc = additionalDesc;
  }

  public String getErrorMsgPart1() {
    return errorMsgPart1;
  }

  public void setErrorMsgPart1(String errorMsgPart1) {
    this.errorMsgPart1 = errorMsgPart1;
  }

  public String getErrorMsgPart2() {
    return errorMsgPart2;
  }

  public void setErrorMsgPart2(String errorMsgPart2) {
    this.errorMsgPart2 = errorMsgPart2;
  }
}
