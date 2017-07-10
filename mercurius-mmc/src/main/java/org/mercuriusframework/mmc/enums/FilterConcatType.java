package org.mercuriusframework.mmc.enums;

/**
 * Filter concat type enum
 */
public enum FilterConcatType {
    /**
     * Values
     */
    AND("AND"),
    OR("OR");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    FilterConcatType(String value) {
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
     * @param rawValue Raw string value
     * @return Enum value
     */
    public static FilterConcatType valueFromString(String rawValue) {
        if (rawValue == null) {
            return AND;
        }
        if (rawValue.trim().toUpperCase().equals(AND.getValue())) {
            return AND;
        }
        if (rawValue.trim().toUpperCase().equals(OR.getValue())) {
            return OR;
        }
        return AND;
    }
}
