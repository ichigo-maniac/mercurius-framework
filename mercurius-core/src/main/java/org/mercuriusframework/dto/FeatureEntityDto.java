package org.mercuriusframework.dto;

import org.mercuriusframework.enums.FeatureType;

/**
 * Feature entity data transfer object
 */
public class FeatureEntityDto extends CatalogUniqueCodeEntityDto {

    private static final long serialVersionUID = 6862681302524349596L;

    /**
     * Feature type
     */
    private FeatureType featureType;

    /**
     * Include a feature in tje index
     */
    private Boolean includeInIndex;

    /**
     * Solr document field name
     */
    private String solrDocumentFieldName;

    /**
     * Get feature type
     * @return Feature type
     */
    public FeatureType getFeatureType() {
        return featureType;
    }

    /**
     * Set feature type
     * @param featureType Feature type
     */
    public void setFeatureType(FeatureType featureType) {
        this.featureType = featureType;
    }

    /**
     * Get include a feature in the index flag
     * @return Include a feature in the index flag
     */
    public Boolean getIncludeInIndex() {
        return includeInIndex;
    }

    /**
     * Set include a feature in the index flag
     * @param includeInIndex Include a feature in the index flag
     */
    public void setIncludeInIndex(Boolean includeInIndex) {
        this.includeInIndex = includeInIndex;
    }

    /**
     * Get Solr document field name
     * @return Solr document field name
     */
    public String getSolrDocumentFieldName() {
        return solrDocumentFieldName;
    }

    /**
     * Set Solr document field name
     * @param solrDocumentFieldName Solr document field name
     */
    public void setSolrDocumentFieldName(String solrDocumentFieldName) {
        this.solrDocumentFieldName = solrDocumentFieldName;
    }
}
