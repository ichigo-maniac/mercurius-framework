package org.mercuriusframework.enums;

/**
 * Widget type
 */
public enum WidgetType {

    /**
     * Values
     */
    TREE_NODES_VIEW("TREE_NODES_VIEW");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    WidgetType(String value) {
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
