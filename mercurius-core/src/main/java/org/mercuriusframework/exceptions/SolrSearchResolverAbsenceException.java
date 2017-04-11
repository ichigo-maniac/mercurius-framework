package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

/**
 * Solr search resolver code
 */
public class SolrSearchResolverAbsenceException extends RuntimeException {

    /**
     * Constructor
     * @param resolverCode Solr search resolver code
     */
    public SolrSearchResolverAbsenceException(String resolverCode) {
        super("SolrSearchResolver with code \"" + StringHelper.escapeHTML(resolverCode) + "\" doesn't exist");
    }
}
