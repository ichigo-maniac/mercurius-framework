package org.mercuriusframework.enums;

/**
 * Solr sort direction enum
 */
public enum SolrSortDirectionType implements LoadOptions {

    /**
     * Values
     */
    ASC("ASC"),
    DESC("DESC");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    SolrSortDirectionType(String value) {
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
