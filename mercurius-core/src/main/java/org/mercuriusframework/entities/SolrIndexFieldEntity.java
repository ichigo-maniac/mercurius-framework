package org.mercuriusframework.entities;

import javax.persistence.*;

/**
 * Solr index field entity class
 */
@Entity(name = SolrIndexFieldEntity.ENTITY_NAME)
@Table(name = "SOLR_INDEX_FIELD")
public class SolrIndexFieldEntity extends UniqueCodeEntity {

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
     * Solr index property
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOLR_INDEX_PROPERTY_UUID", referencedColumnName = "uuid", nullable = false)
    private SolrIndexTaskPropertyEntity solrIndexProperty;
    public static final String SOLR_INDEX_PROPERTY = "solrIndexProperty";

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
     * Get solr index property
     * @return Solr index property
     */
    public SolrIndexTaskPropertyEntity getSolrIndexProperty() {
        return solrIndexProperty;
    }

    /**
     * Set solr index property
     * @param solrIndexProperty Solr index property
     */
    public void setSolrIndexProperty(SolrIndexTaskPropertyEntity solrIndexProperty) {
        this.solrIndexProperty = solrIndexProperty;
    }
}
