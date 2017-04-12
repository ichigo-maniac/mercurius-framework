package org.mercuriusframework.facades.impl;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.entities.SolrIndexTaskEntity;
import org.mercuriusframework.entities.TaskEntity;
import org.mercuriusframework.exceptions.NoEnabledTaskException;
import org.mercuriusframework.facades.TaskFacade;
import org.mercuriusframework.providers.ApplicationContextProvider;
import org.mercuriusframework.services.ConfigurationService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.tasks.AbstractTaskRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Task facade
 */
@Service("taskFacade")
@Profile(MercuriusConstants.PROFILES.DEVELOP_PROFILE)
public class TaskFacadeImpl implements TaskFacade {

    /**
     * Unique code entity service
     */
    @Autowired
    @Qualifier("uniqueCodeEntityService")
    protected UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Configuration service
     */
    @Autowired
    @Qualifier("configurationService")
    protected ConfigurationService configurationService;

    /**
     * Run task
     * @param taskCode Task entity code
     */
    @Override
    public void runTask(String taskCode) {
        TaskEntity taskEntity = uniqueCodeEntityService.getEntityByCode(taskCode, TaskEntity.class);
        if (taskEntity instanceof SolrIndexTaskEntity &&
                !configurationService.isProfileActive(MercuriusConstants.PROFILES.SOLR_SEARCH_PROFILES)) {
            return;
        }
        if (taskEntity != null && taskEntity.getEnabled()) {
            AbstractTaskRunner taskRunner = ApplicationContextProvider.getBean(taskEntity.getTaskRunBeanName(), AbstractTaskRunner.class);
            if (taskRunner != null) {
                if (!taskRunner.isTaskRunning()) {
                    taskRunner.setCurrentTaskUuid(taskEntity.getUuid());
                    Thread thread = new Thread(taskRunner);
                    thread.start();
                } else {
                    throw new NoEnabledTaskException(taskCode);
                }
            }
        } else {
            throw new NoEnabledTaskException(taskCode);
        }
    }
}
