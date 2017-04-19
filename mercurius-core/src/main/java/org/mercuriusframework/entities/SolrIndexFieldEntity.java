package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Solr index field entity class
 */
@Entity(name = SolrIndexFieldEntity.ENTITY_NAME)
@Table(name = "SOLR_INDEX_FIELDS")
public class SolrIndexFieldEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = 4195367163211127367L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "SolrIndexField";

    /**
     * Solr document field name
     */
    @Basic(optional = false)
    private String solrDocumentFieldName;
    public static final String SOLR_DOCUMENT_FIELD_NAME = "solrDocumentFieldName";

    /**
     * Entity field name
     */
    @Basic(optional = false)
    private String entityFieldName;
    public static final String ENTITY_FIELD_NAME = "entityFieldName";

    /**
     * Solr field converter bean name
     */
    @Basic(optional = true)
    private String solrFieldConverterBeanName;
    public static final String SOLR_FIELD_CONVERTER_BEAN_NAME = "solrFieldConverterBeanName";

    /**
     * Case insensitive
     */
    @Basic(optional = true)
    private Boolean caseInsensitive;
    public static final String CASE_INSENSITIVE = "caseInsensitive";

    /**
     * Solr index properties
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SOLR_INDEX_PROPERTIES_FIELDS_LINKS",
            joinColumns = {
                    @JoinColumn(name = "FIELD_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "PROPERTY_UUID",
                            nullable = false, updatable = false)})
    private List<SolrIndexTaskPropertyEntity> indexProperties;
    public static final String INDEX_PROPERTIES = "indexProperties";

    /**
     * Solr search resolvers
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SOLR_INDEX_PROPERTIES_FIELDS_LINKS",
            joinColumns = {
                    @JoinColumn(name = "FIELD_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "RESOLVER_UUID",
                            nullable = false, updatable = false)})
    private List<SolrSearchResolverEntity> searchResolvers;

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
     * Get entity field name
     * @return Entity field name
     */
    public String getEntityFieldName() {
        return entityFieldName;
    }

    /**
     * Set entity field name
     * @param entityFieldName Entity field name
     */
    public void setEntityFieldName(String entityFieldName) {
        this.entityFieldName = entityFieldName;
    }

    /**
     * Get solr field converter bean name
     * @return Solr field converter bean name
     */
    public String getSolrFieldConverterBeanName() {
        return solrFieldConverterBeanName;
    }

    /**
     * Set solr field converter bean name
     * @param solrFieldConverterBeanName Solr field converter bean name
     */
    public void setSolrFieldConverterBeanName(String solrFieldConverterBeanName) {
        this.solrFieldConverterBeanName = solrFieldConverterBeanName;
    }

    /**
     * Get case insensitive flag
     * @return Case insensitive flag
     */
    public Boolean getCaseInsensitive() {
        return caseInsensitive;
    }

    /**
     * Set case insensitive flag
     * @param caseInsensitive case insensitive flag
     */
    public void setCaseInsensitive(Boolean caseInsensitive) {
        this.caseInsensitive = caseInsensitive;
    }

    /**
     * Get solr index properties
     * @return Solr index properties
     */
    public List<SolrIndexTaskPropertyEntity> getIndexProperties() {
        return indexProperties;
    }

    /**
     * Set solr index properties
     * @param indexProperties Solr index properties
     */
    public void setIndexProperties(List<SolrIndexTaskPropertyEntity> indexProperties) {
        this.indexProperties = indexProperties;
    }

    /**
     * Get Solr search resolvers
     * @return Solr search resolvers
     */
    public List<SolrSearchResolverEntity> getSearchResolvers() {
        return searchResolvers;
    }

    /**
     * Set Solr search resolvers
     * @param searchResolvers Solr search resolvers
     */
    public void setSearchResolvers(List<SolrSearchResolverEntity> searchResolvers) {
        this.searchResolvers = searchResolvers;
    }
}
