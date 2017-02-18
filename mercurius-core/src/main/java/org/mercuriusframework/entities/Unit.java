package org.mercuriusframework.entities;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Unit entity class
 */
@Entity(name = Unit.ENTITY_NAME)
@Table(name = "UNIT")
public class Unit extends CatalogUniqueCodeEntity {
    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Unit";
}
