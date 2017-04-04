package org.mercuriusframework.services.impl;

import org.mercuriusframework.entities.SolrIndexTaskEntity;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.SolrIndexTaskService;
import org.mercuriusframework.services.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Solr index task service
 */
@Service("solrIndexTaskService")
public class SolrIndexTaskServiceImpl implements SolrIndexTaskService {

    /**
     * Entity service
      */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Get all tasks
     * @return List of tasks
     */
    @Override
    public List<SolrIndexTaskEntity> getAllTasks() {
        return entityService.getListResultByQuery("SELECT task FROM " + SolrIndexTaskEntity.ENTITY_NAME + " as task",
                SolrIndexTaskEntity.class);
    }

    /**
     * Get tasks
     * @param enabled Enabled flag
     * @return List of tasks
     */
    @Override
    public List<SolrIndexTaskEntity> getTasks(boolean enabled) {
        return entityService.getListResultByQuery("SELECT task FROM " + SolrIndexTaskEntity.ENTITY_NAME + " as task " +
                "WHERE task." + SolrIndexTaskEntity.ENABLED + " = :enabledFlag",
                SolrIndexTaskEntity.class,
                new QueryParameter("enabledFlag", enabled));
    }
}
