package org.mercuriusframework.enums;

/**
 * Task status enum
 */
public enum  TaskStatus {
    /**
     * Values
     */
    NEW("READY"),
    RUNNING("RUNNING"),
    ERROR("ERROR"),
    ABORTED("ABORTED"),
    FINISHED("FINISHED");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    TaskStatus(String value) {
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
