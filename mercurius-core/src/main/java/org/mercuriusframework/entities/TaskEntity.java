package org.mercuriusframework.entities;

import javax.persistence.*;

/**
 * Task entity entity class
 */
@Entity(name = TaskEntity.ENTITY_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TASK_TYPE", discriminatorType = DiscriminatorType.STRING)
@Table(name = "TASKS")
public class TaskEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = -9214326341221460539L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Task";

    /**
     * Is a task enabled
     */
    @Basic(optional = true)
    private Boolean enabled;
    public static final String ENABLED = "enabled";

    /**
     * Get is a task enabled
     * @return Is a task enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Set is a task enabled
     * @param enabled Is a task enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
