package org.mercuriusframework.enums;

/**
 * Password encoding type
 */
public enum PasswordEncodingType {

    /**
     * Values
     */
    PLAIN_TEXT("PLAIN_TEXT"),
    MD4("MD4"),
    MD5("MD5"),
    SHA("SHA");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    PasswordEncodingType(String value) {
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
