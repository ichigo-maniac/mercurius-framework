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
     * Solr index property
     */
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "SOLR_INDEX_PROPERTY_UUID")
    private SolrIndexTaskPropertyEntity indexProperty;
    public static final String INDEX_PROPERTY = "indexProperty";

    /**
     * Get solr index property
     * @return solr index property
     */
    public SolrIndexTaskPropertyEntity getIndexProperty() {
        return indexProperty;
    }

    /**
     * Set solr index property
     * @param indexProperty solr index property
     */
    public void setIndexProperty(SolrIndexTaskPropertyEntity indexProperty) {
        this.indexProperty = indexProperty;
    }
}
