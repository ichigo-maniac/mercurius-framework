package org.mercuriusframework.enums;

/**
 * Feature type enum
 */

public enum FeatureType {

    /**
     * Values
     */
    STRING_TYPE("STRING_TYPE"),
    NUMERIC_TYPE("NUMERIC_TYPE"),
    FLOAT_NUMERIC_TYPE("FLOAT_NUMERIC_TYPE"),
    BOOLEAN_TYPE("BOOLEAN_TYPE"),
    DICTIONARY_TYPE("DICTIONARY_TYPE");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    FeatureType(String value) {
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
     * Get feature type from string
     * @param rawValue Raw string value
     * @return Feature type
     */
    public static FeatureType valueFromString(String rawValue) {
        if (rawValue == null) {
            return null;
        }
        if (STRING_TYPE.getValue().equalsIgnoreCase(rawValue)) {
            return STRING_TYPE;
        }
        if (NUMERIC_TYPE.getValue().equalsIgnoreCase(rawValue)) {
            return NUMERIC_TYPE;
        }
        if (FLOAT_NUMERIC_TYPE.getValue().equalsIgnoreCase(rawValue)) {
            return FLOAT_NUMERIC_TYPE;
        }
        if (BOOLEAN_TYPE.getValue().equalsIgnoreCase(rawValue)) {
            return BOOLEAN_TYPE;
        }
        if (DICTIONARY_TYPE.getValue().equalsIgnoreCase(rawValue)) {
            return DICTIONARY_TYPE;
        }
        return null;
    }
}
