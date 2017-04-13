package org.mercuriusframework.enums;

/**
 * Criteria value type
 */
public enum CriteriaValueType {

    /**
     * Values
     */
    EQUAL("EQUAL"),
    NOT_EQUAL("NOT_EQUAL"),
    IN("IN"),
    NOT_IN("NOT_IN");
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
}
