package org.mercuriusframework.facades;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.entities.SolrSortEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.facades.solr.SolrCriteriaParameter;
import org.mercuriusframework.services.query.PageableResult;

/**
 * Search facade interface
 */
public interface SolrSearchFacade {
    /**
     * Search indexed data
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery Text query
     * @param parameters Solr criteria parameters
     * @param page Current page
     * @param solrSortCode Solr sort code
     * @param fetchFields Fetch fields
     * @return Pageable result
     */
    PageableResult search(String solrSearchResolverCode, String textQuery, SolrCriteriaParameter[] parameters,
                          Integer page, String solrSortCode, String... fetchFields);

    /**
     * Search indexed data
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery Text query
     * @param parameters Solr criteria parameters
     * @param page Current page
     * @param solrSort Solr sort
     * @param fetchFields Fetch fields
     * @return Pageable result
     */
    PageableResult search(String solrSearchResolverCode, String textQuery, SolrCriteriaParameter[] parameters,
                          Integer page, SolrSortEntity solrSort, String... fetchFields);

    /**
     * Search indexed data
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery Text query
     * @param parameters Solr criteria parameters
     * @param page Current page
     * @param solrSortCode Solr sort code
     * @param converter Converter
     * @param loadOptions Load options
     * @param fetchFields Fetch fields
     * @return Pageable result
     */
    PageableResult search(String solrSearchResolverCode, String textQuery, SolrCriteriaParameter[] parameters,
                          Integer page, String solrSortCode, Converter converter,
                          LoadOptions[] loadOptions, String... fetchFields);

    /**
     * Search indexed data
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery Text query
     * @param parameters Solr criteria parameters
     * @param page Current page
     * @param solrSort Solr sort
     * @param converter Converter
     * @param loadOptions Load options
     * @param fetchFields Fetch fields
     * @return Pageable result
     */
    PageableResult search(String solrSearchResolverCode, String textQuery, SolrCriteriaParameter[] parameters,
                          Integer page, SolrSortEntity solrSort, Converter converter,
                          LoadOptions[] loadOptions, String... fetchFields);

    /**
     * Return documents count
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery Text query
     * @param parameters Solr criteria parameters
     * @return Documents count
     */
    Long count(String solrSearchResolverCode, String textQuery, SolrCriteriaParameter... parameters);
}
