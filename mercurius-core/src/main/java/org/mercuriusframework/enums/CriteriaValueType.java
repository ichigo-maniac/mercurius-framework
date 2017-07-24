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
    EQUALS("EQUALS"),
    NOT_EQUALS("NOT_EQUALS"),
    IN("IN"),
    MORE("MORE"),
    MORE_OR_EQUALS("MORE_OR_EQUALS"),
    LESS("LESS"),
    LESS_OR_EQUALS("LESS_OR_EQUALS"),
    CONTAINS("CONTAINS");

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
