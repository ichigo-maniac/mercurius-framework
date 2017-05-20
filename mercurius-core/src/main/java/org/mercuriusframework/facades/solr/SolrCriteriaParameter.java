package org.mercuriusframework.facades.solr;

import org.mercuriusframework.enums.SolrCriteriaValueType;

/**
 * Solr criteria parameter
 */
public class SolrCriteriaParameter {

    /**
     * Solr field
     */
    private String solrField;

    /**
     * Parameter value
     */
    private Object value;

    /**
     * Parameter type
     */
    private SolrCriteriaValueType type;

    /**
     * Constructor
     * @param solrField Solr field
     * @param value Value
     * @param type Type
     */
    public SolrCriteriaParameter(String solrField, Object value, SolrCriteriaValueType type) {
        this.solrField = solrField;
        this.value = value;
        this.type = type;
    }

    /**
     * Get solr field
     * @return Solr field
     */
    public String getSolrField() {
        return solrField;
    }

    /**
     * Get parameter value
     * @return Parameter value
     */
    public Object getValue() {
        return value;
    }

    /**
     * Get parameter type
     * @return Parameter type
     */
    public SolrCriteriaValueType getType() {
        return type;
    }
}
