package org.mercuriusframework.mmc.enums;

/**
 * Field type enum
 */
public enum FieldType {
    /**
     * Values
     */
    ENTITY("ENTITY"),
    ENTITY_COLLECTION("ENTITY_COLLECTION"),
    NUMBER("NUMBER"),
    STRING("STRING"),
    BOOLEAN("BOOLEAN"),
    DATETIME("DATETIME");

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
