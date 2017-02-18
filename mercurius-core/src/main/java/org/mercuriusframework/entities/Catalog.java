package org.mercuriusframework.entities;

import javax.persistence.*;

/**
 * Catalog entity class
 */
@Entity(name = Catalog.ENTITY_NAME)
@Table(name = "SHOP_CATALOG")
public class Catalog extends UniqueCodeEntity {
    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Catalog";
}
