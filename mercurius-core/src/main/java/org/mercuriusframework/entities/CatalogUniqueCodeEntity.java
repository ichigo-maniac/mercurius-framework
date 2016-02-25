package org.mercuriusframework.entities;

/**
 * Entity with unique code inside catalog class
 */
public abstract class CatalogUniqueCodeEntity extends AbstractEntity {
    /**
     * Code (unique)
     */
    private String code;
    /**
     * Catalog
     */
    private Catalog catalog;

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
