package org.mercuriusframework.listeners.impl;

import org.mercuriusframework.entities.TaskEntity;
import org.mercuriusframework.enums.TaskStatus;
import org.mercuriusframework.exceptions.MandatoryParameterNullException;
import org.mercuriusframework.exceptions.NoSuchSpecialBeanException;
import org.mercuriusframework.helpers.ApplicationContextProvider;
import org.mercuriusframework.listeners.PrePersistEntityListener;
import org.mercuriusframework.listeners.PreUpdateEntityListener;
import org.mercuriusframework.tasks.AbstractTaskRunner;
import org.springframework.stereotype.Service;

/**
 * Task entity listener
 */
@Service("taskEntityListener")
public class TaskEntityListener implements PrePersistEntityListener<TaskEntity>, PreUpdateEntityListener<TaskEntity> {

    /**
     * Get entity type
     * @return Entity type
     */
    @Override
    public Class<TaskEntity> getEntityType() {
        return TaskEntity.class;
    }

    /**
     * "Pre-update" handler
     * @param entityObject Entity object
     */
    @Override
    public void preUpdate(TaskEntity entityObject) {
        if (entityObject.getStatus() == null) {
            throw new MandatoryParameterNullException(TaskEntity.class, TaskEntity.STATUS);
        }
        validateTask(entityObject);
    }

    /**
     * "Pre-persist" handler
     * @param entityObject Entity object
     */
    @Override
    public void prePersist(TaskEntity entityObject) {
        if (entityObject.getStatus() == null) {
            entityObject.setStatus(TaskStatus.NEW);
        }
        validateTask(entityObject);
    }

    /**
     * Validate task
     * @param entityObject Entity object
     */
    private void validateTask(TaskEntity entityObject) {
        if (entityObject.getTaskRunBeanName() == null) {
            throw new MandatoryParameterNullException(TaskEntity.class, TaskEntity.TASK_RUN_BEAN_NAME);
        }
        try {
            AbstractTaskRunner taskRunnerBean = (AbstractTaskRunner) ApplicationContextProvider.getBean(
                    entityObject.getTaskRunBeanName(), AbstractTaskRunner.class);
            if (taskRunnerBean == null) {
                throw new NoSuchSpecialBeanException(entityObject.getTaskRunBeanName(), AbstractTaskRunner.class);
            }
        } catch (Exception exception) {
            throw new NoSuchSpecialBeanException(entityObject.getTaskRunBeanName(), AbstractTaskRunner.class);
        }
    }
}
