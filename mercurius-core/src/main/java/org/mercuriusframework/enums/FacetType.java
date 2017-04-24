package org.mercuriusframework.enums;

/**
 * Facet type enum
 */
public enum FacetType {

    /**
     * Values
     */
    DICTIONARY("DICTIONARY"),
    RANGE("RANGE");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    FacetType(String value) {
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
     * Get facet type from string
     * @param rawValue Raw string value
     * @return Facet type
     */
    public static FacetType valueFromString(String rawValue) {
        if (rawValue == null) {
            return null;
        }
        if (DICTIONARY.getValue().equalsIgnoreCase(rawValue)) {
            return DICTIONARY;
        }
        if (RANGE.getValue().equalsIgnoreCase(rawValue)) {
            return RANGE;
        }
        return null;
    }
}
