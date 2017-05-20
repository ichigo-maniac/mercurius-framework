package org.mercuriusframework.dto;

import org.mercuriusframework.enums.FacetType;
import org.mercuriusframework.enums.SolrCriteriaValueType;

import java.util.List;

/**
 * Facet entity data transfer object
 */
public class FacetEntityDto extends UniqueCodeEntityDto {

    private static final long serialVersionUID = 8840337980679791485L;

    /**
     * Facet type
     */
    private FacetType facetType;

    /**
     * Available values
     */
    private List<DictionaryItemEntityDto> availableValues;

    /**
     * Solr document field name
     */
    private String solrDocumentFieldName;

    /**
     * Solr criteria value type
     */
    private SolrCriteriaValueType solrCriteriaValueType;

    /**
     * Get facet type
     * @return Facet type
     */
    public FacetType getFacetType() {
        return facetType;
    }

    /**
     * Set facet type
     * @param facetType Facet type
     */
    public void setFacetType(FacetType facetType) {
        this.facetType = facetType;
    }

    /**
     * Get available values
     * @return Available values
     */
    public List<DictionaryItemEntityDto> getAvailableValues() {
        return availableValues;
    }

    /**
     * Set available values
     * @param availableValues Available values
     */
    public void setAvailableValues(List<DictionaryItemEntityDto> availableValues) {
        this.availableValues = availableValues;
    }

    /**
     * Get solr document field name
     * @return Solr document field name
     */
    public String getSolrDocumentFieldName() {
        return solrDocumentFieldName;
    }

    /**
     * Set solr document field name
     * @param solrDocumentFieldName Solr document field name
     */
    public void setSolrDocumentFieldName(String solrDocumentFieldName) {
        this.solrDocumentFieldName = solrDocumentFieldName;
    }

    /**
     * Get solr criteria value type
     * @return Solr criteria value type
     */
    public SolrCriteriaValueType getSolrCriteriaValueType() {
        return solrCriteriaValueType;
    }

    /**
     * Set solr criteria value type
     * @param solrCriteriaValueType Solr criteria value type
     */
    public void setSolrCriteriaValueType(SolrCriteriaValueType solrCriteriaValueType) {
        this.solrCriteriaValueType = solrCriteriaValueType;
    }
}
