package org.mercuriusframework.mmc.enums;

/**
 * Load widget status result
 */
public enum LoadWidgetResultStatus {
    /**
     * Values
     */
    CORRECT("CORRECT"),
    NOT_FOUND("NOT_FOUND"),
    ERROR("ERROR");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    LoadWidgetResultStatus(String value) {
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
