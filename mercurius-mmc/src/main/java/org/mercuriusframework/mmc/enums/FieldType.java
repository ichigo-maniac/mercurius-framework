package org.mercuriusframework.mmc.enums;

/**
 * Field type enum
 */
public enum FieldType {
    /**
     * Values
     */
    NUMBER("NUMBER"),
    STRING("STRING"),
    BOOLEAN("BOOLEAN");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    FieldType(String value) {
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
