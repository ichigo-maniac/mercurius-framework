package org.mercuriusframework.entities;

import org.mercuriusframework.enums.FacetType;

import javax.persistence.*;
import java.util.List;

/**
 * Facet entity class
 */
@Entity(name = FacetEntity.ENTITY_NAME)
@Table(name = "FACETS")
public class FacetEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = 3082937694550197925L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Facet";

    /**
     * Facet type
     */
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private FacetType facetType;
    public static final String FACET_TYPE = "facetType";

    /**
     * Categories
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "FACETS_CATEGORIES_LINKS",
            joinColumns = {
                    @JoinColumn(name = "FACET_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "CATEGORY_UUID",
                            nullable = false, updatable = false)})
    private List<CategoryEntity> categories;
    public static final String CATEGORIES = "categories";

    /**
     * Is facet for all categories
     */
    @Basic(optional = true)
    private Boolean forAllCategories;
    public static final String FOR_ALL_CATEGORIES = "forAllCategories";

    /**
     * Feature
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FEATURE_UUID", referencedColumnName = "UUID", nullable = true)
    private FeatureEntity feature;
    public static final String FEATURE = "feature";

    /**
     * Solr index field
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SOLR_INDEX_FIELD_UUID", referencedColumnName = "UUID", nullable = true)
    private SolrIndexFieldEntity solrIndexField;
    public static final String SOLR_INDEX_FIELD = "solrIndexField";

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
     * Get categories
     * @return Categories
     */
    public List<CategoryEntity> getCategories() {
        return categories;
    }

    /**
     * Set categories
     * @param categories Categories
     */
    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    /**
     * Get is facet for all categories check result
     * @return Is facet for all categories
     */
    public Boolean getForAllCategories() {
        return forAllCategories;
    }

    /**
     * Set is facet for all categories check result
     * @param forAllCategories Is facet for all categories
     */
    public void setForAllCategories(Boolean forAllCategories) {
        this.forAllCategories = forAllCategories;
    }

    /**
     * Get Feature
     * @return Feature
     */
    public FeatureEntity getFeature() {
        return feature;
    }

    /**
     * Set feature
     * @param feature feature
     */
    public void setFeature(FeatureEntity feature) {
        this.feature = feature;
    }

    /**
     * Get solr index field
     * @return Solr index field
     */
    public SolrIndexFieldEntity getSolrIndexField() {
        return solrIndexField;
    }

    /**
     * Set solr index field
     * @param solrIndexField Solr index field
     */
    public void setSolrIndexField(SolrIndexFieldEntity solrIndexField) {
        this.solrIndexField = solrIndexField;
    }
}
