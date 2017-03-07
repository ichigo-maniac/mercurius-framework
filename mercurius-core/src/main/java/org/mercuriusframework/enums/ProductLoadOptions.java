package org.mercuriusframework.enums;

/**
 * Product load options
 */
public enum ProductLoadOptions implements LoadOptions {
    /**
     * Values
     */
    DESCRIPTION("DESCRIPTION"),
    BREAD_CRUMBS("BREAD_CRUMBS"),
    ALL_PRICES("ALL_PRICES"),
    DEFAULT_UNIT_PRICES("DEFAULT_UNIT_PRICES"),
    DEFAULT_CURRENCY_PRICES("DEFAULT_CURRENCY_PRICES"),
    DEFAULT_CURRENCY_AND_UNIT_PRICE("DEFAULT_CURRENCY_AND_UNIT_PRICE"),
    ALL_STOCKS("ALL_STOCKS"),
    ALL_STOCKS_FOR_SET_STORE("ALL_STOCKS_FOR_SET_STORE"),
    DEFAULT_UNIT_STOCKS("DEFAULT_UNIT_STOCKS"),
    DEFAULT_UNIT_STOCKS_FOR_SET_STORE("DEFAULT_UNIT_STOCKS_FOR_SET_STORE");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    ProductLoadOptions(String value) {
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
