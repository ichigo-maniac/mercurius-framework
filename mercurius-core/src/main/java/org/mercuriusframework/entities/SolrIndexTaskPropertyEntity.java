package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Solr index task property entity class
 */
@Entity(name = SolrIndexTaskPropertyEntity.ENTITY_NAME)
@Table(name = "SOLR_INDEX_TASK_PROPERTY")
public class SolrIndexTaskPropertyEntity extends UniqueCodeEntity {

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "SolrIndexTaskProperty";

    /**
     * Solr collection name
     */
    @Basic(optional = false)
    private String solrCollectionName;
    public static final String SOLR_COLLECTION_NAME = "solrCollectionName";

    /**
     * Index entity name
     */
    @Basic(optional = false)
    private String indexEntityName;
    public static final String INDEX_ENTITY_NAME = "indexEntityName";

    /**
     * Query string (hql)
     */
    @Column(nullable = false, length = 2500)
    private String query;
    public static final String QUERY = "query";

    /**
     * Index fields
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SOLR_INDEX_PROPERTIES_FIELDS_LINK",
            joinColumns = {
                    @JoinColumn(name = "PROPERTY_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "FIELD_UUID",
                            nullable = false, updatable = false)})
    private List<SolrIndexFieldEntity> indexFields;
    public static final String INDEX_FIELDS = "indexFields";

    /**
     * Get solr collection name
     * @return Solr collection name
     */
    public String getSolrCollectionName() {
        return solrCollectionName;
    }

    /**
     * Set solr collection name
     * @param solrCollectionName Solr collection name
     */
    public void setSolrCollectionName(String solrCollectionName) {
        this.solrCollectionName = solrCollectionName;
    }

    /**
     * Get index entity name
     * @return Index entity name
     */
    public String getIndexEntityName() {
        return indexEntityName;
    }

    /**
     * Set index entity name
     * @param indexEntityName Index entity name
     */
    public void setIndexEntityName(String indexEntityName) {
        this.indexEntityName = indexEntityName;
    }

    /**
     * Get query string (hql)
     * @return Query string (hql)
     */
    public String getQuery() {
        return query;
    }

    /**
     * Set query string (hql)
     * @param query Query string (hql)
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Get index fields
     * @return Index fields
     */
    public List<SolrIndexFieldEntity> getIndexFields() {
        return indexFields;
    }

    /**
     * Set index fields
     * @param indexFields Index fields
     */
    public void setIndexFields(List<SolrIndexFieldEntity> indexFields) {
        this.indexFields = indexFields;
    }
}
