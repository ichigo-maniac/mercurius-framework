package org.mercuriusframework.entities;

import org.mercuriusframework.enums.SolrSortDirectionType;
import javax.persistence.*;

/**
 * Solr sort entity class
 */
@Entity(name = SolrSortEntity.ENTITY_NAME)
@Table(name = "SOLR_SORTS")
public class SolrSortEntity extends UniqueCodeEntity {

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "SolrSort";

    /**
     * Solr search resolver
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOLR_SEARCH_RESOLVER_UUID", referencedColumnName = "uuid", nullable = false)
    private SolrSearchResolverEntity solrSearchResolver;
    public static final String SOLR_SEARCH_RESOLVER = "solrSearchResolver";

    /**
     * Solr field
     */
    @Basic(optional = true)
    private String solrField;
    public static final String SOLR_FIELD = "solrField";

    /**
     * Solr field resolver bean name
     */
    @Basic(optional = true)
    private String solrFieldResolver;
    public static final String SOLR_FIELD_RESOLVER = "solrFieldResolver";

    /**
     * Direction type
     */
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private SolrSortDirectionType directionType;
    public static final String DIRECTION_TYPE = "directionType";

    /**
     * Get solr search resolver
     * @return Solr search resolver
     */
    public SolrSearchResolverEntity getSolrSearchResolver() {
        return solrSearchResolver;
    }

    /**
     * Set solr search resolver
     * @param solrSearchResolver Solr search resolver
     */
    public void setSolrSearchResolver(SolrSearchResolverEntity solrSearchResolver) {
        this.solrSearchResolver = solrSearchResolver;
    }

    /**
     * Get solr field
     * @return Solr field
     */
    public String getSolrField() {
        return solrField;
    }

    /**
     * Set solr field
     * @param solrField Solr field
     */
    public void setSolrField(String solrField) {
        this.solrField = solrField;
    }

    /**
     * Get solr field resolver bean name
     * @returnSolr field resolver bean name
     */
    public String getSolrFieldResolver() {
        return solrFieldResolver;
    }

    /**
     * Set solr field resolver bean name
     * @param solrFieldResolver Solr field resolver bean name
     */
    public void setSolrFieldResolver(String solrFieldResolver) {
        this.solrFieldResolver = solrFieldResolver;
    }

    /**
     * Get direction type
     * @return Direction type
     */
    public SolrSortDirectionType getDirectionType() {
        return directionType;
    }

    /**
     * Set direction type
     * @param directionType Direction type
     */
    public void setDirectionType(SolrSortDirectionType directionType) {
        this.directionType = directionType;
    }
}
