package org.mercuriusframework.entities;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Unit entity class
 */
@Entity
@Table(name = "UNIT")
public class Unit extends CatalogUniqueCodeEntity {
}
