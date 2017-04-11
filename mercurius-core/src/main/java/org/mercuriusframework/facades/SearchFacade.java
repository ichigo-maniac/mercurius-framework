package org.mercuriusframework.facades;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.services.query.PageableResult;

/**
 * Search facade interface
 */
public interface SearchFacade {

    /**
     * Search indexed data
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery Text query
     * @param page Current page
     * @param fetchFields Fetch fields
     * @return Pageable result
     */
    PageableResult search(String solrSearchResolverCode, String textQuery, Integer page, String... fetchFields);

    /**
     * Search indexed data
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery Text query
     * @param page Current page
     * @param converter Converter
     * @param loadOptions Load options
     * @param fetchFields Fetch fields
     * @return Pageable result
     */
    PageableResult search(String solrSearchResolverCode, String textQuery, Integer page, Converter converter,
                          LoadOptions[] loadOptions, String... fetchFields);
}
