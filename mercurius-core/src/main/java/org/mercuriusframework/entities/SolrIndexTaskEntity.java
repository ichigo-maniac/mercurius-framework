package org.mercuriusframework.entities;

import javax.persistence.*;

/**
 * Solr index task entity
 */
@Entity(name = SolrIndexTaskEntity.ENTITY_NAME)
@DiscriminatorValue(SolrIndexTaskEntity.ENTITY_NAME)
public class SolrIndexTaskEntity extends TaskEntity {

    private static final long serialVersionUID = 5644579963134369942L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "SolrIndexTask";

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
}
