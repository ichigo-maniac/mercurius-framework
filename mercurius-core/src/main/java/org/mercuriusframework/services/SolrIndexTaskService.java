package org.mercuriusframework.services;

import org.mercuriusframework.entities.SolrIndexTaskEntity;

import java.util.List;

/**
 * Solr undex task service interface
 */
public interface SolrIndexTaskService {

    /**
     * Get all tasks
     * @return List of tasks
     */
    List<SolrIndexTaskEntity> getAllTasks();

    /**
     * Get tasks
     * @param enabled Enabled flag
     * @return List of tasks
     */
    List<SolrIndexTaskEntity> getTasks(boolean enabled);
}
