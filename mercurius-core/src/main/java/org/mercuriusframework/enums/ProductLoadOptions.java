package org.mercuriusframework.enums;

/**
 * Product load options
 */
public enum ProductLoadOptions implements LoadOptions {
    /**
     * Values
     */
    BREAD_CRUMBS("BREAD_CRUMBS");

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
