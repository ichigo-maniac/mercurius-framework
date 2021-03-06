package org.mercuriusframework.enums;

/**
 * Category load options
 */
public enum CategoryLoadOptions implements LoadOptions {
    /**
     * Values
     */
    DESCRIPTION("DESCRIPTION"),
    BREAD_CRUMBS("BREAD_CRUMBS");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    CategoryLoadOptions(String value) {
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
     * @return Array of values
     */
    @Override
    public LoadOptions[] getValues() {
        return values();
    }

}
