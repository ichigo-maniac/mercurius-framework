package org.mercuriusframework.entities;

import org.mercuriusframework.enums.TaskStatus;
import javax.persistence.*;
import java.util.Date;

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
     * Status
     */
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    public static final String STATUS = "status";

    /**
     * Task run bean name
     */
    @Basic(optional = false)
    private String taskRunBeanName;
    public static final String TASK_RUN_BEAN_NAME = "taskRunBeanName";

    /**
     * Is a task enabled
     */
    @Basic(optional = true)
    private Boolean enabled;
    public static final String ENABLED = "enabled";

    /**
     * Last start time
     */
    @Basic(optional = true)
    private Date lastStartTime;
    public static final String LAST_START_TIME = "lastStartTime";

    /**
     * Last finish time
     */
    @Basic(optional = true)
    private Date lastFinishTime;
    public static final String LAST_FINISH_TIME = "lastFinishTime";

    /**
     * Get status
     * @return Status
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Set status
     * @param status Status
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    /**
     * Get task run bean name
     * @return Task run bean name
     */
    public String getTaskRunBeanName() {
        return taskRunBeanName;
    }

    /**
     * Set task run bean name
     * @param taskRunBeanName Task run bean name
     */
    public void setTaskRunBeanName(String taskRunBeanName) {
        this.taskRunBeanName = taskRunBeanName;
    }

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

    /**
     * Get last start time
     * @return Last start time
     */
    public Date getLastStartTime() {
        return lastStartTime;
    }

    /**
     * Set last start time
     * @param lastStartTime Last start time
     */
    public void setLastStartTime(Date lastStartTime) {
        this.lastStartTime = lastStartTime;
    }

    /**
     * Get last finish time
     * @return Last finish time
     */
    public Date getLastFinishTime() {
        return lastFinishTime;
    }

    /**
     * Set last finish time
     * @param lastFinishTime Last finish time
     */
    public void setLastFinishTime(Date lastFinishTime) {
        this.lastFinishTime = lastFinishTime;
    }
}
