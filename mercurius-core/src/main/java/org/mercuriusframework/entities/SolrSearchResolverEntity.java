package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Solr search resolver entity class
 */
@Entity(name = SolrSearchResolverEntity.ENTITY_NAME)
@Table(name = "SOLR_SEARCH_RESOLVERS")
public class SolrSearchResolverEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = -1774888047395626339L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "SolrSearchResolver";

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
     * Page size
     */
    @Basic(optional = false)
    private Integer pageSize;
    public static final String PAGE_SIZE = "pageSize";

    /**
     * Text search fields
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SOLR_SEARCH_RESOLVERS_TEXT_FIELDS_LINKS",
            joinColumns = {
                    @JoinColumn(name = "RESOLVER_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "FIELD_UUID",
                            nullable = false, updatable = false)})
    private List<SolrIndexFieldEntity> textSearchFields;
    public static final String TEXT_SEARCH_FIELDS = "textSearchFields";

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
     * Get page size
     * @return Page size
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Set page size
     * @param pageSize Page size
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Get text search fields
     * @return Text search fields
     */
    public List<SolrIndexFieldEntity> getTextSearchFields() {
        return textSearchFields;
    }

    /**
     * Set text search fields
     * @param textSearchFields Text search fields
     */
    public void setTextSearchFields(List<SolrIndexFieldEntity> textSearchFields) {
        this.textSearchFields = textSearchFields;
    }
}
