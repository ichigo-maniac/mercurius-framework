package org.mercuriusframework.enums;

/**
 * Authentication type enum
 */
public enum AuthenticationType {

    /**
     * Values
     */
    FORM("FORM"),
    OAUTH("OAUTH");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    AuthenticationType(String value) {
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
