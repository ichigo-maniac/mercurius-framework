package org.mercuriusframework.entities;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Unit entity class
 */
@Entity
@Table(name = "UNIT")
@SequenceGenerator(name = "entity_id_gen", sequenceName = "UNIT_SEQ",
        allocationSize = 1, initialValue = 1)
public class Unit extends CatalogUniqueCodeEntity {
}
