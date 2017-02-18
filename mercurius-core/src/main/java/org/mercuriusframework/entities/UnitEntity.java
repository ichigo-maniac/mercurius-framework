package org.mercuriusframework.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Unit entity class
 */
@Entity(name = UnitEntity.ENTITY_NAME)
@Table(name = "UNIT")
public class UnitEntity extends CatalogUniqueCodeEntity {
    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Unit";
}
