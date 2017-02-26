package org.mercuriusframework.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Big string entity class (for lazy load)
 */
@Entity(name = BigStringEntity.ENTITY_NAME)
@Table(name = "BIG_STRING")
public class BigStringEntity extends CatalogUniqueCodeEntity {

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "BigString";

    /**
     * Big string value
     */
    @Lob
    @Type(type = "org.hibernate.type.StringClobType")
    @Basic(optional = false)
    @Column(name = "text_value")
    private String value;
    public static final String VALUE = "value";

    /**
     * Default constructor
     */
    public BigStringEntity() {
    }

    /**
     * Constructor
     * @param value Text value
     * @param name Name
     * @param catalog Catalog
     */
    public BigStringEntity(String value, String name, CatalogEntity catalog) {
        this.value = value;
        this.setName(name);
        this.setCatalog(catalog);
    }

    /**
     * Get big string value
     * @return Big string value
     */
    public String getValue() {
        return value;
    }

    /**
     * Set Big string value
     * @param value Big string value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
