package org.mercuriusframework.enums;

/**
 * Stock load options
 */
public enum  StockLoadOptions implements LoadOptions {

    /**
     * Values
     */
    UNIT("UNIT"),
    PRODUCT("PRODUCT"),
    WAREHOUSE("WAREHOUSE");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    StockLoadOptions(String value) {
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

    /**
     * Get enum value
     *
     * @return Array of values
     */
    @Override
    public LoadOptions[] getValues() {
        return values();
    }


}
