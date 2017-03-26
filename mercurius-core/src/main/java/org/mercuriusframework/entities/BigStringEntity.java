package org.mercuriusframework.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Big string entity class (for lazy load)
 */
@Entity(name = BigStringEntity.ENTITY_NAME)
@Table(name = "BIG_STRING")
public class BigStringEntity extends CatalogUniqueCodeEntity {

    private static final long serialVersionUID = 8969015900186944644L;

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
     * @param value Value
     * @param catalog Catalog
     */
    public BigStringEntity(String value, CatalogEntity catalog) {
        this.value = value;
        this.setName(ENTITY_NAME + new Date().getTime());
        this.setCatalog(catalog);
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
