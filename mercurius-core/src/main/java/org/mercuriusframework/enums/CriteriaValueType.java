package org.mercuriusframework.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Criteria value type
 */
public enum CriteriaValueType {

    /**
     * Values
     */
    START_WITH("START_WITH"),
    END_WITH("END_WITH"),
    EQUAL("EQUAL"),
    NOT_EQUAL("NOT_EQUAL"),
    IN("IN");
    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    CriteriaValueType(String value) {
        this.value = value;
    }

    /**
     * Get enum value
     * @return Enum value
     */
    public String getValue() {
        return value;
    }

    /**
     * Get value from string
     * @param value String value
     * @return Enum value
     */
    public static CriteriaValueType valueFromString(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (CriteriaValueType valueType : CriteriaValueType.values()) {
            if (valueType.getValue().equalsIgnoreCase(value.toLowerCase())) {
                return valueType;
            }
        }
        return null;
    }
}
