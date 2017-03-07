package org.mercuriusframework.entities;

import javax.persistence.*;

/**
 * Entity with unique code inside catalog class
 */
@MappedSuperclass
public abstract class CatalogUniqueCodeEntity extends AbstractEntity {

    private static final long serialVersionUID = -4880745943114820892L;

    /**
     * Name
     */
    @Basic(optional = false)
    private String name;
    public static final String NAME = "name";

    /**
     * Code (unique)
     */
    @Basic(optional = false)
    private String code;
    public static final String CODE = "code";

    /**
     * Catalog
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATALOG_UUID", referencedColumnName = "uuid", nullable = false)
    private CatalogEntity catalog;
    public static final String CATALOG = "catalog";


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
    public CatalogEntity getCatalog() {
        return catalog;
    }

    /**
     * Set catalog
     * @param catalog Catalog
     */
    public void setCatalog(CatalogEntity catalog) {
        this.catalog = catalog;
    }
}
