package org.mercuriusframework.dto;

import java.util.List;

/**
 * Product entity data transfer object
 */
public class ProductEntityDto extends CatalogUniqueCodeEntityDto {

    /**
     * Description
     */
    private String description;

    /**
     * Short name
     */
    private String shortName;

    /**
     * Bread crumbs
     */
    private List<CategoryEntityDto> breadCrumbs;

    /**
     * Get description
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description
     * @param description Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get bread crumbs
     * @return Bread crumbs
     */
    public List<CategoryEntityDto> getBreadCrumbs() {
        return breadCrumbs;
    }

    /**
     * Set bread crumbs
     * @param breadCrumbs Bread crumbs
     */
    public void setBreadCrumbs(List<CategoryEntityDto> breadCrumbs) {
        this.breadCrumbs = breadCrumbs;
    }

    /**
     * Get short name
     * @return Short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Set short name
     * @param shortName Short name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
