package org.mercuriusframework.enums;

/**
 * Solr criteria value type enum
 */
public enum SolrCriteriaValueType {

    /**
     * Values
     */
    IS("IS"),
    IN("IN");
    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    SolrCriteriaValueType(String value) {
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
    public static SolrCriteriaValueType valueFromString(String rawValue) {
        if (rawValue == null) {
            return null;
        }
        if (IS.getValue().equalsIgnoreCase(rawValue)) {
            return IS;
        }
        if (IN.getValue().equalsIgnoreCase(rawValue)) {
            return IN;
        }
        return null;
    }
}
