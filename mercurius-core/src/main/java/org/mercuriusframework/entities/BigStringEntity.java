package org.mercuriusframework.entities;

import org.hibernate.annotations.Type;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

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
    private String value;
    public static final String VALUE = "value";

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
