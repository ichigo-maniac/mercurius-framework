package org.mercuriusframework.facades.impl;

import org.mercuriusframework.entities.TaskEntity;
import org.mercuriusframework.facades.TaskFacade;
import org.mercuriusframework.helpers.ApplicationContextProvider;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.tasks.AbstractTaskRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Task facade
 */
@Service("taskFacade")
public class TaskFacadeImpl implements TaskFacade {

    /**
     * Unique code entity service
     */
    @Autowired
    @Qualifier("uniqueCodeEntityService")
    protected UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Run task
     * @param taskCode Task entity code
     */
    @Override
    public void runTask(String taskCode) {
        TaskEntity taskEntity = uniqueCodeEntityService.getEntityByCode(taskCode, TaskEntity.class);
        if (taskEntity != null && taskEntity.getEnabled()) {
            AbstractTaskRunner taskRunner = ApplicationContextProvider.getBean(taskEntity.getTaskRunBeanName(), AbstractTaskRunner.class);
            if (taskRunner != null) {
                if (!taskRunner.isTaskRunning()) {
                    taskRunner.setCurrentTaskUuid(taskEntity.getUuid());
                    Thread thread = new Thread(taskRunner);
                    thread.start();
                } else {

                }
            }
        } else {

        }
    }
}
