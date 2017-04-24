package org.mercuriusframework.entities;

import org.mercuriusframework.enums.FeatureType;

import javax.persistence.*;
import java.util.List;

/**
 * Feature entity class
 */
@Entity(name = FeatureEntity.ENTITY_NAME)
@Table(name = "FEATURES")
public class FeatureEntity extends CatalogUniqueCodeEntity {

    private static final long serialVersionUID = -8068212712665197705L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Feature";

    /**
     * Feature type
     */
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private FeatureType featureType;
    public static final String FEATURE_TYPE = "featureType";

    /**
     * Dictionary type
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DICTIONARY_TYPE_UUID", referencedColumnName = "uuid", nullable = true)
    private DictionaryTypeEntity dictionaryType;
    public static final String DICTIONARY_TYPE = "dictionaryType";

    /**
     * Feature values
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = FeatureValueEntity.FEATURE, cascade = CascadeType.ALL)
    private List<FeatureValueEntity> featureValues;
    public static final String FEATURE_VALUES = "featureValues";

    /**
     * Facets
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = FacetEntity.FEATURE, cascade = CascadeType.ALL)
    private List<FacetEntity> facets;
    public static final String FACETS = "facets";

    /**
     * Include a feature in the index
     */
    @Basic(optional = true)
    private Boolean includeInIndex;

    /**
     * Solr document field name
     */
    @Basic(optional = true)
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
     * Get dictionary type
     * @return Dictionary type
     */
    public DictionaryTypeEntity getDictionaryType() {
        return dictionaryType;
    }

    /**
     * Set dictionary type
     * @param dictionaryType Dictionary type
     */
    public void setDictionaryType(DictionaryTypeEntity dictionaryType) {
        this.dictionaryType = dictionaryType;
    }

    /**
     * Get feature values
     * @return Feature values
     */
    public List<FeatureValueEntity> getFeatureValues() {
        return featureValues;
    }

    /**
     * Set feature values
     * @param featureValues Feature values
     */
    public void setFeatureValues(List<FeatureValueEntity> featureValues) {
        this.featureValues = featureValues;
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

    /**
     * Get facets
     * @return Facets
     */
    public List<FacetEntity> getFacets() {
        return facets;
    }

    /**
     * Set facets
     * @param facets Facets
     */
    public void setFacets(List<FacetEntity> facets) {
        this.facets = facets;
    }
}
