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
    DocTool, xTool, BusinessRule, Validation, CARESPlatformIntegration;
    
    public static Type fromName(String typeName) {
      return EnumUtils.getEnum(Type.class, typeName);
    }
  }

  private String ruleName;
  private Type ruleType;
  private String ruleDescription;
  private String ruleDroolsRule;
  private String docExternalDocumentation;
  private String docEnforcementLocations;
  private String docDoctoolName;
  private String docDoctoolDescription;
  private String docXtoolEntity;
  private String docXtoolAttribute;
  private String docXtoolLength;
  private String docXtoolType;
  private String docXtoolNullable;
  private String docXtoolManadatory;
  private String docRiEntity;
  private String docRiAttribute;
  private String docRiCardinality;
  private String docRiRelationship;
  private String docRiRelatedEntity;
  private String docRiRelatedAttribute;
  private String docCipName;
  private String docCipDescription;
  
  public RuleDocumentation(String ruleName, Type ruleType, String ruleDescription,
      String ruleDroolsRule,
      String docExternalDocumentation, String docEnforcementLocations,
      String docDoctoolName, String docDoctoolDescription, String docXtoolEntity,
      String docXtoolAttribute, String docXtoolLength, String docXtoolType,
      String docXtoolNullable, String docXtoolManadatory, String docRiEntity,
      String docRiAttribute, String docRiCardinality, String docRiRelationship,
      String docRiRelatedEntity, String docRiRelatedAttribute, String docCipName,
      String docCipDescription) {
    super();
    this.ruleName = ruleName;
    this.ruleType = ruleType;
    this.ruleDescription = ruleDescription;
    this.ruleDroolsRule = ruleDroolsRule;
    this.docExternalDocumentation = docExternalDocumentation;
    this.docEnforcementLocations = docEnforcementLocations;
    this.docDoctoolName = docDoctoolName;
    this.docDoctoolDescription = docDoctoolDescription;
    this.docXtoolEntity = docXtoolEntity;
    this.docXtoolAttribute = docXtoolAttribute;
    this.docXtoolLength = docXtoolLength;
    this.docXtoolType = docXtoolType;
    this.docXtoolNullable = docXtoolNullable;
    this.docXtoolManadatory = docXtoolManadatory;
    this.docRiEntity = docRiEntity;
    this.docRiAttribute = docRiAttribute;
    this.docRiCardinality = docRiCardinality;
    this.docRiRelationship = docRiRelationship;
    this.docRiRelatedEntity = docRiRelatedEntity;
    this.docRiRelatedAttribute = docRiRelatedAttribute;
    this.docCipName = docCipName;
    this.docCipDescription = docCipDescription;
  }

  public String getRuleName() {
    return ruleName;
  }

  public void setRuleName(String ruleName) {
    this.ruleName = ruleName;
  }

  public Type getRuleType() {
    return ruleType;
  }

  public void setRuleType(Type ruleType) {
    this.ruleType = ruleType;
  }

  public String getRuleDescription() {
    return ruleDescription;
  }

  public void setRuleDescription(String ruleDescription) {
    this.ruleDescription = ruleDescription;
  }

  public String getRuleDroolsRule() {
    return ruleDroolsRule;
  }
  
  public void setRuleDroolsRule(String ruleDroolsRule) {
    this.ruleDroolsRule = ruleDroolsRule;
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

  public String getDocXtoolEntity() {
    return docXtoolEntity;
  }

  public void setDocXtoolEntity(String docXtoolEntity) {
    this.docXtoolEntity = docXtoolEntity;
  }

  public String getDocXtoolAttribute() {
    return docXtoolAttribute;
  }

  public void setDocXtoolAttribute(String docXtoolAttribute) {
    this.docXtoolAttribute = docXtoolAttribute;
  }

  public String getDocXtoolLength() {
    return docXtoolLength;
  }

  public void setDocXtoolLength(String docXtoolLength) {
    this.docXtoolLength = docXtoolLength;
  }

  public String getDocXtoolType() {
    return docXtoolType;
  }

  public void setDocXtoolType(String docXtoolType) {
    this.docXtoolType = docXtoolType;
  }

  public String getDocXtoolNullable() {
    return docXtoolNullable;
  }

  public void setDocXtoolNullable(String docXtoolNullable) {
    this.docXtoolNullable = docXtoolNullable;
  }

  public String getDocXtoolManadatory() {
    return docXtoolManadatory;
  }

  public void setDocXtoolManadatory(String docXtoolManadatory) {
    this.docXtoolManadatory = docXtoolManadatory;
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

  public String getDocCipName() {
    return docCipName;
  }

  public void setDocCipName(String docCipName) {
    this.docCipName = docCipName;
  }

  public String getDocCipDescription() {
    return docCipDescription;
  }

  public void setDocCipDescription(String docCipDescription) {
    this.docCipDescription = docCipDescription;
  }

  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
  
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

}
