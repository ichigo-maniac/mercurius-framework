package org.mercuriusframework.mmc.dto;

import org.mercuriusframework.enums.CriteriaValueType;
import org.mercuriusframework.mmc.enums.FieldType;

/**
 * Filter container
 */
public class FilterContainer {

    /**
     * Label
     */
    private String label;

    /**
     * Property
     */
    private String property;

    /**
     * Include on start
     */
    private Boolean includeOnStart;

    /**
     * Field type
     */
    private FieldType fieldType;

    /**
     * Entity name
     */
    private String entityName;

    /**
     * Criteria types
     */
    private CriteriaValueType[] criteriaTypes;

    /**
     * Json criteria types
     */
    private String jsonCriteriaTypes;

    /**
     * Constructor
     * @param property entity property
     * @param includeOnStart Include on start
     */
    public FilterContainer(String property, Boolean includeOnStart) {
        this.property = property;
        this.includeOnStart = includeOnStart;
    }

    /**
     * Get label
     * @return Label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set label
     * @param label Label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Get property
     * @return Property
     */
    public String getProperty() {
        return property;
    }

    /**
     * Set property
     * @param property Property
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * Get include on start
     * @return Include on start
     */
    public Boolean getIncludeOnStart() {
        return includeOnStart;
    }

    /**
     * Set include on start
     * @param includeOnStart Include on start
     */
    public void setIncludeOnStart(Boolean includeOnStart) {
        this.includeOnStart = includeOnStart;
    }

    /**
     * Get criteria types
     * @return Criteria types
     */
    public CriteriaValueType[] getCriteriaTypes() {
        return criteriaTypes;
    }

    /**
     * Set criteria types
     * @param criteriaTypes Criteria types
     */
    public void setCriteriaTypes(CriteriaValueType[] criteriaTypes) {
        this.criteriaTypes = criteriaTypes;
    }

    /**
     * Get entity name
     * @return Entity name
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Set entity name
     * @param entityName Entity name
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * Get field type
     * @return Field type
     */
    public FieldType getFieldType() {
        return fieldType;
    }

    /**
     * Set field type
     * @param fieldType Field type
     */
    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * Get json criteria types
     * @return Json criteria types
     */
    public String getJsonCriteriaTypes() {
        return jsonCriteriaTypes;
    }

    /**
     * Set json criteria types
     * @param jsonCriteriaTypes Json criteria types
     */
    public void setJsonCriteriaTypes(String jsonCriteriaTypes) {
        this.jsonCriteriaTypes = jsonCriteriaTypes;
    }
}
