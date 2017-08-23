package org.mercuriusframework.services;

import org.mercuriusframework.entities.SolrSearchResolverEntity;
import org.mercuriusframework.entities.SolrSortEntity;

import java.util.List;

/**
 * Solr sort service interface
 */
public interface SolrSortService {

    /**
     * Get solr sorts by solr search resolver
     * @param resolverUuid Solr search resolver uuid
     * @return List of solr sorts
     */
    List<SolrSortEntity> getSortsBySolrSearchResolverUuid(String resolverUuid);

    /**
     * Get solr sorts by solr search resolver
     * @param resolverCode Solr search resolver code
     * @return List of solr sorts
     */
    List<SolrSortEntity> getSortsBySolrSearchResolverCode(String resolverCode);

    /**
     * Get solr sorts by solr search resolver
     * @param resolver Solr search resolver
     * @return List of solr sorts
     */
    List<SolrSortEntity> getSortsBySolrSearchResolver(SolrSearchResolverEntity resolver);
}
