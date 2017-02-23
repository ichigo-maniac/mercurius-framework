package org.mercuriusframework.dto;

import java.util.List;

/**
 * Category data transfer object
 */
public class CategoryEntityDto extends CatalogUniqueCodeEntityDto {
    /**
     * Built url by bread crumbs
     */
    private String builtUrl;

    /**
     * Bread crumbs
     */
    private List<CategoryEntityDto> breadCrumbs;

    /**
     * Get built url
     * @return Built url
     */
    public String getBuiltUrl() {
        return builtUrl;
    }

    /**
     * Set built url
     * @param builtUrl Built url
     */
    public void setBuiltUrl(String builtUrl) {
        this.builtUrl = builtUrl;
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
}
