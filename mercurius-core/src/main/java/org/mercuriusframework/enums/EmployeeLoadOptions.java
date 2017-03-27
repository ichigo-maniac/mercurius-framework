package org.mercuriusframework.enums;

/**
 * Employee load options
 */
public enum  EmployeeLoadOptions implements LoadOptions {

    /**
     * Values
     */
    ROLES("ROLES");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    EmployeeLoadOptions(String value) {
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
