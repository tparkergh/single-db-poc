package gov.ca.cwds.bre.interfaces.model;

import java.util.Map;
import gov.ca.cwds.bre.interfaces.model.RuleDocumentation.Type;

public class RuleDocumentationBuilder {

  private String docDroolsFile;
  private String docExternalDocumentation;
  private String docEnforcementLocations;
  private String docRuleType;
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

  public RuleDocumentationBuilder() {
    
  }
  
  public RuleDocumentationBuilder setDocDroolsFile(String docDroolsFile) {
    this.docDroolsFile = docDroolsFile;
    return this;
  }
  
  public RuleDocumentationBuilder setDocExternalDocumentation(String docExternalDocumentation) {
    this.docExternalDocumentation = docExternalDocumentation;
    return this;
  }
  public RuleDocumentationBuilder setDocEnforcementLocations(String docEnforcementLocations) {
    this.docEnforcementLocations = docEnforcementLocations;
    return this;
  }
  
  public RuleDocumentationBuilder setDocRuleType(String docRuleType) {
    this.docRuleType = docRuleType;
    return this;
  }
  
  public RuleDocumentationBuilder setDocRuleName(String docRuleName) {
    this.docRuleName = docRuleName;
    return this;
  }
  
  public RuleDocumentationBuilder setDocDoctoolName(String docToolName) {
    this.docDoctoolName = docToolName;
    return this;
  }
  public RuleDocumentationBuilder setDocDoctoolDescription(String docDoctoolDescription) {
    this.docDoctoolDescription = docDoctoolDescription;
    return this;
  }
  public RuleDocumentationBuilder setDocDiEntity(String docDiEntity) {
    this.docDiEntity = docDiEntity;
    return this;
  }
  public RuleDocumentationBuilder setDocDiAttribute(String docDiAttribute) {
    this.docDiAttribute = docDiAttribute;
    return this;
  }
  public RuleDocumentationBuilder setDocDiLength(String docDiLength) {
    this.docDiLength = docDiLength;
    return this;
  }
  public RuleDocumentationBuilder setDocDiType(String docDiType) {
    this.docDiType = docDiType;
    return this;
  }
  public RuleDocumentationBuilder setDocDiNullable(String docDiNullable) {
    this.docDiNullable = docDiNullable;
    return this;
  }
  
  public RuleDocumentationBuilder setDocDiMandatory(String docDiMandatory) {
    this.docDiManadatory = docDiMandatory;
    return this;
  }
  public RuleDocumentationBuilder setDocDiDefault(String docDiDefault) {
    this.docDiDefault = docDiDefault;
    return this;
  }
  public RuleDocumentationBuilder setDocDiValidationTable(String docDiValidationTable) {
    this.docDiValidationTable = docDiValidationTable;
    return this;
  }
  public RuleDocumentationBuilder setDocDiValidationCategory(String docDiValidationCategory) {
    this.docDiValidationCategory = docDiValidationCategory;
    return this;
  }
  public RuleDocumentationBuilder setDocDiValidationSubCategory(String docDiValidationSubCategory) {
    this.docDiValidationSubCategory = docDiValidationSubCategory;
    return this;
  }
  public RuleDocumentationBuilder setDocRiEntity(String docRiEntity) {
    this.docRiEntity = docRiEntity;
    return this;
  }
  public RuleDocumentationBuilder setDocRiAttribute(String docRiAttribute) {
    this.docRiAttribute = docRiAttribute;
    return this;
  }
  public RuleDocumentationBuilder setDocRiCardinality(String docRiCardinality) {
    this.docRiCardinality = docRiCardinality;
    return this;
  }
  public RuleDocumentationBuilder setDocRiRelationship(String docRiRelationship) {
    this.docRiRelationship = docRiRelationship;
    return this;
  }
  public RuleDocumentationBuilder setDocRiRelatedEntity(String docRiRelatedEntity) {
    this.docRiRelatedEntity = docRiRelatedEntity;
    return this;
  }
  public RuleDocumentationBuilder setDocRiRelatedAttribute(String docRiRelatedAttribute) {
    this.docRiRelatedAttribute = docRiRelatedAttribute;
    return this;
  }
  public RuleDocumentationBuilder setDocCpiName(String docCpiName) {
    this.docCpiName = docCpiName;
    return this;
  }
  public RuleDocumentationBuilder setDocCpiDescription(String docCpiDescription) {
    this.docCpiDescription = docCpiDescription;
    return this;
  }
  
  public RuleDocumentation build() {
    return new RuleDocumentation( docDroolsFile,
        docExternalDocumentation, docEnforcementLocations,
        Type.fromName(docRuleType), docRuleName,
        docDoctoolName,  docDoctoolDescription,  docDiEntity,
        docDiAttribute,  docDiLength,  docDiType,
        docDiNullable,  docDiManadatory,  docDiDefault,
        docDiValidationTable, docDiValidationCategory, docDiValidationSubCategory,
        docRiEntity,
        docRiAttribute,  docRiCardinality,  docRiRelationship,
        docRiRelatedEntity,  docRiRelatedAttribute,  docCpiName,
        docCpiDescription);    
  }
  
  public RuleDocumentation buildFromMetaData(Map<String, Object> ruleMeta) {
    Object docDroolsFileObj = ruleMeta.get("doc_drools_file");
    Object docExternalDocumentationObj = ruleMeta.get("doc_external_documentation");
    Object docEnforcementLocationsObj = ruleMeta.get("doc_enforcement_location");
    Object docRuleTypeObj = ruleMeta.get("doc_rule_type");
    Object docRuleNameObj = ruleMeta.get("doc_rule_name");
    Object docDoctoolNameObj = ruleMeta.get("doc_doctool_name");
    Object docDoctoolDescriptioinObj = ruleMeta.get("doc_doctool_description");
    Object docDiEntityObj = ruleMeta.get("doc_di_entity");
    Object docDiAttributeObj = ruleMeta.get("doc_di_attribute");
    Object docDiLengthObj = ruleMeta.get("doc_di_length");
    Object docDiTypeObj = ruleMeta.get("doc_di_type");
    Object docDiNuallableObj = ruleMeta.get("doc_di_nullable");
    Object docDiMandatoryObj = ruleMeta.get("doc_di_mandatory");
    Object docDiDefaultObj = ruleMeta.get("doc_di_default");
    Object docDiValidationTableObj = ruleMeta.get("doc_di_validation_table");
    Object docDiValidationCategoryObj = ruleMeta.get("doc_di_validation_category");
    Object docDiValidationSubCategoryObj = ruleMeta.get("doc_di_validation_sub_category");
    Object docRiEntityObj = ruleMeta.get("doc_ri_entity");
    Object docRiAttributeObj = ruleMeta.get("doc_ri_attribute");
    Object docRiCardinalityObj = ruleMeta.get("doc_ri_cardinality");
    Object docRiRelationshipObj = ruleMeta.get("doc_ri_relationship");
    Object docRiRelatedEntityObj = ruleMeta.get("doc_ri_related_entity");
    Object docRiRelatedAttributeObj = ruleMeta.get("doc_ri_related_attribute");
    Object docCpiNameObj = ruleMeta.get("doc_cip_name");
    Object docCpiDescriptionObj = ruleMeta.get("doc_cip_description");
    
    this.docDroolsFile = docDroolsFileObj != null ? (String) docDroolsFileObj : "";
    this.docExternalDocumentation = docExternalDocumentationObj != null ? (String) docExternalDocumentationObj : "";
    this.docEnforcementLocations = docEnforcementLocationsObj != null ? (String) docEnforcementLocationsObj : "";
    this.docRuleType = docRuleTypeObj != null ? (String)docRuleTypeObj : null;
    this.docRuleName = docRuleNameObj != null ? (String) docRuleNameObj : "";
    this.docDoctoolName = docDoctoolNameObj != null ? (String) docDoctoolNameObj : "";
    this.docDoctoolDescription = docDoctoolDescriptioinObj != null ? (String) docDoctoolDescriptioinObj : "";
    this.docDiEntity = docDiEntityObj != null ? (String) docDiEntityObj : "";
    this.docDiAttribute = docDiAttributeObj != null ? (String) docDiAttributeObj : "";
    this.docDiLength = docDiLengthObj != null ? (String) docDiLengthObj : "";
    this.docDiType = docDiTypeObj != null ? (String) docDiTypeObj : "";
    this.docDiNullable = docDiNuallableObj != null ? (String) docDiNuallableObj : "";
    this.docDiManadatory = docDiMandatoryObj != null ? (String) docDiMandatoryObj : "";
    this.docDiDefault = docDiDefaultObj != null ? (String) docDiDefaultObj : "" ;
    this.docDiValidationTable = docDiValidationTableObj != null ? (String) docDiValidationTableObj : "";
    this.docDiValidationCategory = docDiValidationCategoryObj != null ? (String) docDiValidationCategoryObj : "";
    this.docDiValidationSubCategory = docDiValidationSubCategoryObj != null ? (String) docDiValidationSubCategoryObj : "";
    this.docRiEntity = docRiEntityObj != null ? (String) docRiEntityObj : "";
    this.docRiAttribute = docRiAttributeObj != null ? (String) docRiAttributeObj : "";
    this.docRiCardinality = docRiCardinalityObj != null ? (String) docRiCardinalityObj : "";
    this.docRiRelationship = docRiRelationshipObj != null ? (String) docRiRelationshipObj : "";
    this.docRiRelatedEntity = docRiRelatedEntityObj != null ? (String) docRiRelatedEntityObj : "";
    this.docRiRelatedAttribute = docRiRelatedAttributeObj != null ? (String) docRiRelatedAttributeObj : "";
    this.docCpiName = docCpiNameObj != null ? (String) docCpiNameObj : "";
    this.docCpiDescription = docCpiDescriptionObj != null ? (String) docCpiDescriptionObj : "";
   
    return this.build();
  }
}
