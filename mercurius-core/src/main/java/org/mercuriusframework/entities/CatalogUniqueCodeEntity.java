package org.mercuriusframework.entities;

import javax.persistence.*;

/**
 * Entity with unique code inside catalog class
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CatalogUniqueCodeEntity extends AbstractEntity {
    /**
     * Name
     */
    @Basic(optional = false)
    private String name;
    /**
     * Code (unique)
     */
    @Basic(optional = false)
    private String code;
    /**
     * Catalog
     */
    /**
     * Main unit
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATALOG_ID", referencedColumnName = "id", nullable = false)
    private Catalog catalog;

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

    /**
     * Get code
     * @return Code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set code
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get catalog
     * @return Catalog
     */
    public Catalog getCatalog() {
        return catalog;
    }

    /**
     * Set catalog
     * @param catalog Catalog
     */
    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
