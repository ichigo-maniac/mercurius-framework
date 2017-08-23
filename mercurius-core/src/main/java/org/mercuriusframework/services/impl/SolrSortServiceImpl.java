package org.mercuriusframework.services.impl;

import org.mercuriusframework.entities.SolrSearchResolverEntity;
import org.mercuriusframework.entities.SolrSortEntity;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.SolrSortService;
import org.mercuriusframework.services.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Solr sort service
 */
@Service("solrSortService")
public class SolrSortServiceImpl implements SolrSortService {

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Get solr sorts by solr search resolver
     * @param resolverUuid Solr search resolver uuid
     * @return List of solr sorts
     */
    @Override
    public List<SolrSortEntity> getSortsBySolrSearchResolverUuid(String resolverUuid) {
        return entityService.getListResultByQuery("SELECT solrSort FROM " + SolrSortEntity.ENTITY_NAME + " as solrSort " +
                "WHERE solrSort." + SolrSortEntity.SOLR_SEARCH_RESOLVER + "." + SolrSearchResolverEntity.UUID + " = :resolverUuid",
                SolrSortEntity.class, new QueryParameter("resolverUuid", resolverUuid));
    }

    /**
     * Get solr sorts by solr search resolver
     * @param resolverCode Solr search resolver code
     * @return List of solr sorts
     */
    @Override
    public List<SolrSortEntity> getSortsBySolrSearchResolverCode(String resolverCode) {
        return entityService.getListResultByQuery("SELECT solrSort FROM " + SolrSortEntity.ENTITY_NAME + " as solrSort " +
                        "WHERE solrSort." + SolrSortEntity.SOLR_SEARCH_RESOLVER + "." + SolrSearchResolverEntity.CODE + " = :resolverCode",
                SolrSortEntity.class, new QueryParameter("resolverCode", resolverCode));
    }

    /**
     * Get solr sorts by solr search resolver
     * @param resolver Solr search resolver
     * @return List of solr sorts
     */
    @Override
    public List<SolrSortEntity> getSortsBySolrSearchResolver(SolrSearchResolverEntity resolver) {
        return entityService.getListResultByQuery("SELECT solrSort FROM " + SolrSortEntity.ENTITY_NAME + " as solrSort " +
                        "WHERE solrSort." + SolrSortEntity.SOLR_SEARCH_RESOLVER + " = :resolver",
                SolrSortEntity.class, new QueryParameter("resolver", resolver));
    }
}
