package org.mercuriusframework.entities;

import javax.persistence.*;

/**
 * Catalog entity class
 */
@Entity(name = CatalogEntity.ENTITY_NAME)
@Table(name = "SHOP_CATALOGS")
public class CatalogEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = -1426507420797305899L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Catalog";
}
