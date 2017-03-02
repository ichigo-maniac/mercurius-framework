package org.mercuriusframework.enums;

/**
 * Store load options
 */
public enum StoreLoadOptions implements LoadOptions {
    /**
     * Values
     */
    ENABLED_WAREHOUSES("ENABLED_WAREHOUSES"),
    WAREHOUSES("WAREHOUSES");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    StoreLoadOptions(String value) {
        this.value = value;
    }

    /**
     * Get enum value
     * @return Enum value
     */
    @Override
    public String getValue() {
        return value;
    }
}
