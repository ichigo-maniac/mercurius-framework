package org.mercuriusframework.mmc.widgets;

/**
 * Widget abstract class
 */
public abstract class Widget {
    /**
     * Priority
     */
    private Integer priority;

    /**
     * Get priority
     * @return Priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Set priority
     * @param priority Priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
