package org.mercuriusframework.enums;

/**
 * Social network type
 */
public enum SocialNetworkType {

    /**
     * Values
     */
    FACEBOOK("FACEBOOK"),
    VK_COM("VK_COM");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    SocialNetworkType(String value) {
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
