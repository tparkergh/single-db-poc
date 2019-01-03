package gov.ca.cwds.bre.interfaces.model;

import java.util.Map;
import gov.ca.cwds.bre.interfaces.model.RuleDocumentation.Type;

public class RuleDocumentationBuilder {

  private String ruleName;
  private String ruleType;
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

  public RuleDocumentationBuilder() {
    
  }
  
  public RuleDocumentationBuilder setRuleName(String name) {
    this.ruleName = name;
    return this;
  }
  
  public RuleDocumentationBuilder setRuleType(String type) {
    this.ruleType = type;
    return this;
  }
  
  public RuleDocumentationBuilder setRuleDescription(String ruleDescription) {
    this.ruleDescription = ruleDescription;
    return this;
  }
  public RuleDocumentationBuilder setRuleDroolsFile(String ruleDroolsRule) {
    this.ruleDroolsRule = ruleDroolsRule;
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
  
  public RuleDocumentationBuilder setDocDoctoolName(String docToolName) {
    this.docDoctoolName = docToolName;
    return this;
  }
  public RuleDocumentationBuilder setDocDoctoolDescription(String docDoctoolDescription) {
    this.docDoctoolDescription = docDoctoolDescription;
    return this;
  }
  public RuleDocumentationBuilder setDocXtoolEntity(String docXtoolEntity) {
    this.docXtoolEntity = docXtoolEntity;
    return this;
  }
  public RuleDocumentationBuilder setDocXtoolAttribute(String docXtoolAttribute) {
    this.docXtoolAttribute = docXtoolAttribute;
    return this;
  }
  public RuleDocumentationBuilder setDocXtoolLength(String docXtoolLength) {
    this.docXtoolLength = docXtoolLength;
    return this;
  }
  public RuleDocumentationBuilder setDocXtoolType(String docXtoolType) {
    this.docXtoolType = docXtoolType;
    return this;
  }
  public RuleDocumentationBuilder setDocXtoolNullable(String docXtoolNullable) {
    this.docXtoolNullable = docXtoolNullable;
    return this;
  }
  
  public RuleDocumentationBuilder setDocXtoolMandatory(String docXtoolMandatory) {
    this.docXtoolManadatory = docXtoolMandatory;
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
  public RuleDocumentationBuilder setDocCipName(String docCipName) {
    this.docCipName = docCipName;
    return this;
  }
  public RuleDocumentationBuilder setDocCipDescription(String docCipDescription) {
    this.docCipDescription = docCipDescription;
    return this;
  }
  
  public RuleDocumentation build() {
    return new RuleDocumentation( ruleName,  Type.fromName(ruleType),  ruleDescription,
        ruleDroolsRule,
        docExternalDocumentation, docEnforcementLocations,
        docDoctoolName,  docDoctoolDescription,  docXtoolEntity,
        docXtoolAttribute,  docXtoolLength,  docXtoolType,
        docXtoolNullable,  docXtoolManadatory,  docRiEntity,
        docRiAttribute,  docRiCardinality,  docRiRelationship,
        docRiRelatedEntity,  docRiRelatedAttribute,  docCipName,
        docCipDescription);
    
  }
  
  public RuleDocumentation buildFromMetaData(Map<String, Object> ruleMeta) {
    Object ruleNameObj = ruleMeta.get("rule_name");
    Object ruleTypeObj = ruleMeta.get("rule_type");
    Object ruleDescriptionObj = ruleMeta.get("rule_description");
    Object ruleDroolsRuleObj = ruleMeta.get("rule_drools_rule");
    Object docExternalDocumentationObj = ruleMeta.get("doc_external_documentation");
    Object docEnforcementLocationsObj = ruleMeta.get("doc_enforcement_location");
    Object docDoctoolNameObj = ruleMeta.get("doc_doctool_name");
    Object docDoctoolDescriptioinObj = ruleMeta.get("doc_doctool_description");
    Object docXtoolEntityObj = ruleMeta.get("doc_xtool_entity");
    Object docXtoolAttributeObj = ruleMeta.get("doc_xtool_attribute");
    Object docXtoolLengthObj = ruleMeta.get("doc_xtool_length");
    Object docXtoolTypeObj = ruleMeta.get("doc_xtool_type");
    Object docXtoolNuallableObj = ruleMeta.get("doc_xtool_nullable");
    Object docXtoolMandatoryObj = ruleMeta.get("doc_xtool_mandatory");
    Object docRiEntityObj = ruleMeta.get("doc_ri_entity");
    Object docRiAttributeObj = ruleMeta.get("doc_ri_attribute");
    Object docRiCardinalityObj = ruleMeta.get("doc_ri_cardinality");
    Object docRiRelationshipObj = ruleMeta.get("doc_ri_relationship");
    Object docRiRelatedEntityObj = ruleMeta.get("doc_ri_related_entity");
    Object docRiRelatedAttributeObj = ruleMeta.get("doc_ri_related_attribute");
    Object docCipNameObj = ruleMeta.get("doc_cip_name");
    Object docCipDescriptionObj = ruleMeta.get("doc_cip_description");
    
    this.ruleName = ruleNameObj != null ? (String) ruleNameObj : "";
    this.ruleType = ruleTypeObj != null ? (String)ruleTypeObj : null;
    this.ruleDescription = ruleDescriptionObj != null ? (String) ruleDescriptionObj : "";
    this.ruleDroolsRule = ruleDroolsRuleObj != null ? (String) ruleDroolsRuleObj : "";
    this.docExternalDocumentation = docExternalDocumentationObj != null ? (String) docExternalDocumentationObj : "";
    this.docEnforcementLocations = docEnforcementLocationsObj != null ? (String) docEnforcementLocationsObj : "";
    this.docDoctoolName = docDoctoolNameObj != null ? (String) docDoctoolNameObj : "";
    this.docDoctoolDescription = docDoctoolDescriptioinObj != null ? (String) docDoctoolDescriptioinObj : "";
    this.docXtoolEntity = docXtoolEntityObj != null ? (String) docXtoolEntityObj : "";
    this.docXtoolAttribute = docXtoolAttributeObj != null ? (String) docXtoolAttributeObj : "";
    this.docXtoolLength = docXtoolLengthObj != null ? (String) docXtoolLengthObj : "";
    this.docXtoolType = docXtoolTypeObj != null ? (String) docXtoolTypeObj : "";
    this.docXtoolNullable = docXtoolNuallableObj != null ? (String) docXtoolNuallableObj : "";
    this.docXtoolManadatory = docXtoolMandatoryObj != null ? (String) docXtoolMandatoryObj : "";
    this.docRiEntity = docRiEntityObj != null ? (String) docRiEntityObj : "";
    this.docRiAttribute = docRiAttributeObj != null ? (String) docRiAttributeObj : "";
    this.docRiCardinality = docRiCardinalityObj != null ? (String) docRiCardinalityObj : "";
    this.docRiRelationship = docRiRelationshipObj != null ? (String) docRiRelationshipObj : "";
    this.docRiRelatedEntity = docRiRelatedEntityObj != null ? (String) docRiRelatedEntityObj : "";
    this.docRiRelatedAttribute = docRiRelatedAttributeObj != null ? (String) docRiRelatedAttributeObj : "";
    this.docCipName = docCipNameObj != null ? (String) docCipNameObj : "";
    this.docCipDescription = docCipDescriptionObj != null ? (String) docCipDescriptionObj : "";
   
    return this.build();
  }
}
