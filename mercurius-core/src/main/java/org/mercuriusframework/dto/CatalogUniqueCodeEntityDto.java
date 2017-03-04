package org.mercuriusframework.dto;

/**
 * Catalog entity data transfer object
 */
public class CatalogUniqueCodeEntityDto extends EntityDto {

    private static final long serialVersionUID = 8347436083115180543L;

    /**
     * Name
     */
    private String name;

    /**
     * Code (unique)
     */
    private String code;

    /**
     * Catalog
     */
    private CatalogEntityDto catalog;

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
    public CatalogEntityDto getCatalog() {
        return catalog;
    }

    /**
     * Set catalog
     * @param catalog Catalog
     */
    public void setCatalog(CatalogEntityDto catalog) {
        this.catalog = catalog;
    }
}
