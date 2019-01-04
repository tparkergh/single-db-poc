package gov.ca.cwds.bre.interfaces.model;

import org.apache.commons.lang3.EnumUtils;
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
public class RuleDocumentation {

  public enum Type {
    DocTool, DataIntegrity, BusinessRule, ReferentialIntegrity, CARESPlatformIntegration, Hotline, CALS, CaseManagement;
    
    public static Type fromName(String typeName) {
      return EnumUtils.getEnum(Type.class, typeName);
    }
  }

  private String docDroolsFile;
  private String docExternalDocumentation;
  private String docEnforcementLocations;
  private Type docRuleType;
  private String docRuleName;
  private String docDoctoolName;
  private String docDoctoolDescription;
  private String docDiEntity;
  private String docDiAttribute;
  private String docDiLength;
  private String docDiType;
  private String docDiNullable;
  private String docDiManadatory;
  private String docDiDefault;
  private String docDiValidationTable;
  private String docDiValidationCategory;
  private String docDiValidationSubCategory;
  private String docRiEntity;
  private String docRiAttribute;
  private String docRiCardinality;
  private String docRiRelationship;
  private String docRiRelatedEntity;
  private String docRiRelatedAttribute;
  private String docCpiName;
  private String docCpiDescription;
  
  public RuleDocumentation(String docDroolsFile, String docExternalDocumentation, 
      String docEnforcementLocations, Type docRuleType, String docRuleName,
      String docDoctoolName, String docDoctoolDescription, String docDiEntity,
      String docDiAttribute, String docDiLength, String docDiType,
      String docDiNullable, String docDiManadatory, String docDiDefault,
      String docDiValidationTable,
      String docDiValidationCategory, String docDiValidationSubCategory,
      String docRiEntity,
      String docRiAttribute, String docRiCardinality, String docRiRelationship,
      String docRiRelatedEntity, String docRiRelatedAttribute, String docCpiName,
      String docCpiDescription) {
    super();
    this.docDroolsFile = docDroolsFile;
    this.docExternalDocumentation = docExternalDocumentation;
    this.docEnforcementLocations = docEnforcementLocations;
    this.docRuleType = docRuleType;
    this.docRuleName = docRuleName;
    this.docDoctoolName = docDoctoolName;
    this.docDoctoolDescription = docDoctoolDescription;
    this.docDiEntity = docDiEntity;
    this.docDiAttribute = docDiAttribute;
    this.docDiLength = docDiLength;
    this.docDiType = docDiType;
    this.docDiNullable = docDiNullable;
    this.docDiManadatory = docDiManadatory;
    this.docDiDefault = docDiDefault;
    this.docDiValidationTable = docDiValidationTable;
    this.docDiValidationCategory = docDiValidationCategory;
    this.docDiValidationSubCategory = docDiValidationSubCategory;
    this.docRiEntity = docRiEntity;
    this.docRiAttribute = docRiAttribute;
    this.docRiCardinality = docRiCardinality;
    this.docRiRelationship = docRiRelationship;
    this.docRiRelatedEntity = docRiRelatedEntity;
    this.docRiRelatedAttribute = docRiRelatedAttribute;
    this.docCpiName = docCpiName;
    this.docCpiDescription = docCpiDescription;
  }


  public String getDocDroolsFile() {
    return docDroolsFile;
  }
  
  public void setDocDroolsFile(String docDroolsFile) {
    this.docDroolsFile = docDroolsFile;
  }
  
  public String getDocExternalDocumentation() {
    return docExternalDocumentation;
  }
  
  public void setDocExternalDocumentation(String docExternalDocumentation) {
    this.docExternalDocumentation = docExternalDocumentation;
  }
  
  public String getDocEnforcementLocations() {
    return docEnforcementLocations;
  }
  
  public void setDocEnforcementLocations(String docEnforcementLocations) {
    this.docEnforcementLocations = docEnforcementLocations;
  }
  
  public Type getDocRuleType() {
    return docRuleType;
  }
  
  public void setDocRuleType(Type docRuleType) {
    this.docRuleType = docRuleType;
  }
  
  public String getDocRuleName() {
    return docRuleName;
  }
  
  public void setDocRuleName(String docRuleName) {
    this.docRuleName = docRuleName;
  }
  
  public String getDocDoctoolName() {
    return docDoctoolName;
  }

  public void setDocDoctoolName(String docDoctoolName) {
    this.docDoctoolName = docDoctoolName;
  }

  public String getDocDoctoolDescription() {
    return docDoctoolDescription;
  }

  public void setDocDoctoolDescription(String docDoctoolDescription) {
    this.docDoctoolDescription = docDoctoolDescription;
  }

  public String getDocDiEntity() {
    return docDiEntity;
  }

  public void setDocDiEntity(String docDiEntity) {
    this.docDiEntity = docDiEntity;
  }

  public String getDocDiAttribute() {
    return docDiAttribute;
  }

  public void setDocDiAttribute(String docDiAttribute) {
    this.docDiAttribute = docDiAttribute;
  }

  public String getDocDiLength() {
    return docDiLength;
  }

  public void setDocDiLength(String docDiLength) {
    this.docDiLength = docDiLength;
  }

  public String getDocDiType() {
    return docDiType;
  }

  public void setDocDiType(String docDiType) {
    this.docDiType = docDiType;
  }

  public String getDocDiNullable() {
    return docDiNullable;
  }

  public void setDocDiNullable(String docDiNullable) {
    this.docDiNullable = docDiNullable;
  }

  public String getDocDiManadatory() {
    return docDiManadatory;
  }

  public String getDocDiDefault() {
    return docDiDefault;
  }


  public void setDocDiDefault(String docDiDefault) {
    this.docDiDefault = docDiDefault;
  }


  public void setDocDiManadatory(String docDiManadatory) {
    this.docDiManadatory = docDiManadatory;
  }

  public String getDocDiValidationTable() {
    return docDiValidationTable;
  }


  public void setDocDiValidationTable(String docDiValidationTable) {
    this.docDiValidationTable = docDiValidationTable;
  }


  public String getDocDiValidationCategory() {
    return docDiValidationCategory;
  }


  public void setDocDiValidationCategory(String docDiValidationCategory) {
    this.docDiValidationCategory = docDiValidationCategory;
  }


  public String getDocDiValidationSubCategory() {
    return docDiValidationSubCategory;
  }


  public void setDocDiValidationSubCategory(String docDiValidationSubCategory) {
    this.docDiValidationSubCategory = docDiValidationSubCategory;
  }


  public String getDocRiEntity() {
    return docRiEntity;
  }

  public void setDocRiEntity(String docRiEntity) {
    this.docRiEntity = docRiEntity;
  }

  public String getDocRiAttribute() {
    return docRiAttribute;
  }

  public void setDocRiAttribute(String docRiAttribute) {
    this.docRiAttribute = docRiAttribute;
  }

  public String getDocRiCardinality() {
    return docRiCardinality;
  }

  public void setDocRiCardinality(String docRiCardinality) {
    this.docRiCardinality = docRiCardinality;
  }

  public String getDocRiRelationship() {
    return docRiRelationship;
  }

  public void setDocRiRelationship(String docRiRelationship) {
    this.docRiRelationship = docRiRelationship;
  }

  public String getDocRiRelatedEntity() {
    return docRiRelatedEntity;
  }

  public void setDocRiRelatedEntity(String docRiRelatedEntity) {
    this.docRiRelatedEntity = docRiRelatedEntity;
  }

  public String getDocRiRelatedAttribute() {
    return docRiRelatedAttribute;
  }

  public void setDocRiRelatedAttribute(String docRiRelatedAttribute) {
    this.docRiRelatedAttribute = docRiRelatedAttribute;
  }

  public String getDocCpiName() {
    return docCpiName;
  }

  public void setDocCpiName(String docCpiName) {
    this.docCpiName = docCpiName;
  }

  public String getDocCpiDescription() {
    return docCpiDescription;
  }

  public void setDocCpiDescription(String docCpiDescription) {
    this.docCpiDescription = docCpiDescription;
  }

  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
  
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

}
