package org.mercuriusframework.tasks;

import org.mercuriusframework.entities.TaskEntity;
import org.mercuriusframework.enums.TaskStatus;
import org.mercuriusframework.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Abstract Task runner
 */
@Service("abstractTaskRunner")
public abstract class AbstractTaskRunner implements Runnable {

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Current task uuid
     */
    protected String currentTaskUuid;

    /**
     * Run task
     */
    @Override
    public synchronized void run() {
        try {
            updateTaskStatus(TaskStatus.RUNNING);
            taskExecution();
            updateTaskStatus(TaskStatus.FINISHED);
        } catch (Exception exception) {
            exception.printStackTrace();
            updateTaskStatus(TaskStatus.ERROR);
        } finally {
            currentTaskUuid = null;
        }
    }

    /**
     * Set current task uuid
     * @param currentTaskUuid Current task uuid
     */
    public synchronized void setCurrentTaskUuid(String currentTaskUuid) {
        this.currentTaskUuid = currentTaskUuid;
    }

    /**
     * Update task status
     * @param status Task status
     */
    private void updateTaskStatus(TaskStatus status) {
        TaskEntity taskEntity = entityService.findByUuid(currentTaskUuid, TaskEntity.class);
        if (taskEntity != null) {
            taskEntity.setStatus(status);
            if (status == TaskStatus.FINISHED || status == TaskStatus.ERROR || status == TaskStatus.ABORTED) {
                taskEntity.setLastFinishTime(new Date());
            }
            if (status == TaskStatus.RUNNING) {
                taskEntity.setLastStartTime(new Date());
            }
            entityService.save(taskEntity);
        }
    }

    /**
     * Is task running
     * @return Check - is task running
     */
    public boolean isTaskRunning() {
        return currentTaskUuid != null;
    }

    /**
     * Task execution (task logic)
     */
    protected abstract void taskExecution();


}
