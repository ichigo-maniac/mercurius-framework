package org.mercuriusframework.dto;

/**
 * Category data transfer object
 */
public class CategoryEntityDto extends CatalogUniqueCodeEntityDto {
    /**
     * Built url by bread crumbs
     */
    private String builtUrl;

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
}
