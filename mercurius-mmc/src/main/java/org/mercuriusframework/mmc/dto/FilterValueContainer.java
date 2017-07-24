package org.mercuriusframework.mmc.dto;

import org.apache.commons.lang.StringUtils;
import org.mercuriusframework.enums.CriteriaValueType;

/**
 * Filter value container
 */
public class FilterValueContainer {

    /**
     * Criteria value type
     */
    private CriteriaValueType criteriaValueType;

    /**
     * Property
     */
    private String property;

    /**
     * Get criteria value type
     * @return Criteria value type
     */
    public CriteriaValueType getCriteriaValueType() {
        return criteriaValueType;
    }

    /**
     * Set criteria value type
     * @param criteriaValueType Criteria value type
     */
    public void setCriteriaValueType(CriteriaValueType criteriaValueType) {
        this.criteriaValueType = criteriaValueType;
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
     * Is container valid
     * @return Check result
     */
    public boolean isValid() {
        return !(StringUtils.isEmpty(property) || criteriaValueType == null);
    }
}
