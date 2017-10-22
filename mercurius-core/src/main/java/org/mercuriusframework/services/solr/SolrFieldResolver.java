package org.mercuriusframework.services.solr;

/**
 * Solr field resolver
 */
public interface SolrFieldResolver {

    /**
     * Resolve field
     * @return Field name
     */
    String resolveField();
}
