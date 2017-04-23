package org.mercuriusframework.tasks;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.entities.SolrIndexTaskEntity;
import org.mercuriusframework.entities.SolrIndexTaskPropertyEntity;
import org.mercuriusframework.facades.SolrIndexFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Solr index task runner
 */
@Service("solrIndexTaskRunner")
@Profile(MercuriusConstants.PROFILES.SOLR_SEARCH_PROFILES)
public class SolrIndexTaskRunner extends AbstractTaskRunner {

    /**
     * Solr index facade
     */
    @Autowired
    @Qualifier("solrIndexFacade")
    protected SolrIndexFacade solrIndexFacade;

    /**
     * Task execution (task logic)
     */
    @Override
    protected void taskExecution() {
        /** Load task object */
        SolrIndexTaskEntity solrIndexTaskEntity = entityService.findByUuid(currentTaskUuid, SolrIndexTaskEntity.class);
        SolrIndexTaskPropertyEntity indexProperty = entityService.findByUuid(solrIndexTaskEntity.getIndexProperty().getUuid(),
                SolrIndexTaskPropertyEntity.class, SolrIndexTaskPropertyEntity.INDEX_FIELDS);
        solrIndexFacade.indexBySolrIndexProperty(indexProperty);
    }
}
