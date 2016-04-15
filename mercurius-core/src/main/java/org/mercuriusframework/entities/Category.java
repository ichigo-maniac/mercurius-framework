package org.mercuriusframework.entities;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Category entity class
 */
@Entity
@Table(name = "CATEGORY")
@SequenceGenerator(name = "entity_id_gen", sequenceName = "CATEGORY_SEQ",
        allocationSize = 1, initialValue = 1)
public class Category extends CatalogUniqueCodeEntity {

}
