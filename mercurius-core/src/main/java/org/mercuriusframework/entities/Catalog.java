package org.mercuriusframework.entities;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Catalog entity class
 */
@Entity
@Table(name = "CATALOG")
@SequenceGenerator(name = "entity_id_gen", sequenceName = "CATALOG_SEQ",
        allocationSize = 1, initialValue = 1)
public class Catalog extends UniqueCodeEntity {
    /**
     * Name
     */
    private String name;

    /**
     * Get name
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }
}
