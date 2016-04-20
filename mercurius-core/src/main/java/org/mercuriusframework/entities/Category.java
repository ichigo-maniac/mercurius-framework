package org.mercuriusframework.entities;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Category entity class
 */
@Entity
@Table(name = "CATEGORY")
public class Category extends CatalogUniqueCodeEntity {

}
