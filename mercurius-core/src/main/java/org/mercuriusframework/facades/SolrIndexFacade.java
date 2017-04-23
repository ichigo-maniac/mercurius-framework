package org.mercuriusframework.facades;

import org.mercuriusframework.entities.SolrIndexTaskPropertyEntity;

/**
 * Solr index facade interface
 */
public interface SolrIndexFacade {
    /**
     * Index by solr index property
     * @param solrIndexProperty Solr index task property
     */
    void indexBySolrIndexProperty(SolrIndexTaskPropertyEntity solrIndexProperty);
}
