package org.mercuriusframework.enums;

/**
 * Price load options
 */
public enum PriceLoadOptions implements LoadOptions {

    /**
     * Values
     */
    UNIT("UNIT"),
    PRODUCT("PRODUCT");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    PriceLoadOptions(String value) {
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
