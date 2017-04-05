package org.mercuriusframework.listeners.impl;

import org.mercuriusframework.entities.SolrIndexTaskEntity;
import org.mercuriusframework.exceptions.MandatoryParameterNullException;
import org.mercuriusframework.exceptions.NoSuchSolrIndexTaskRunnerBeanException;
import org.mercuriusframework.helpers.ApplicationContextProvider;
import org.mercuriusframework.listeners.PrePersistEntityListener;
import org.mercuriusframework.listeners.PreUpdateEntityListener;
import org.mercuriusframework.tasks.SolrIndexTaskRunner;
import org.springframework.stereotype.Service;

/**
 * Solr index task entity listener
 */
@Service("solrIndexTaskEntityListener")
public class SolrIndexTaskEntityListener implements PrePersistEntityListener<SolrIndexTaskEntity>, PreUpdateEntityListener<SolrIndexTaskEntity> {

    /**
     * Get entity type
     * @return Entity type
     */
    @Override
    public Class<SolrIndexTaskEntity> getEntityType() {
        return SolrIndexTaskEntity.class;
    }

    /**
     * "Pre-update" handler
     * @param entityObject Entity object
     */
    @Override
    public void preUpdate(SolrIndexTaskEntity entityObject) {
        validateSolrTask(entityObject);
    }

    /**
     * "Pre-persist" handler
     * @param entityObject Entity object
     */
    @Override
    public void prePersist(SolrIndexTaskEntity entityObject) {
        validateSolrTask(entityObject);
    }

    /**
     * Validate solr index task
     * @param entityObject Solr index task object
     */
    private void validateSolrTask(SolrIndexTaskEntity entityObject) {
        if (entityObject.getTaskRunBeanName() == null) {
            throw new MandatoryParameterNullException(SolrIndexTaskEntity.class, SolrIndexTaskEntity.TASK_RUN_BEAN_NAME);
        }
        try {
            SolrIndexTaskRunner taskRunnerBean = (SolrIndexTaskRunner) ApplicationContextProvider.getBean(
                    entityObject.getTaskRunBeanName(), SolrIndexTaskRunner.class);
            if (taskRunnerBean == null) {
                throw new NoSuchSolrIndexTaskRunnerBeanException(entityObject.getTaskRunBeanName());
            }
        } catch (Exception exception) {
            throw new NoSuchSolrIndexTaskRunnerBeanException(entityObject.getTaskRunBeanName());
        }
    }
}
