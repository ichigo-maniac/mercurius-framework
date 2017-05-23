package org.mercuriusframework.dto;

import java.util.List;

/**
 * Category data transfer object
 */
public class CategoryEntityDto extends CatalogUniqueCodeEntityDto {

    private static final long serialVersionUID = -5276196213038282233L;

    /**
     * Description
     */
    private String description;

    /**
     * Built url by bread crumbs
     */
    private String builtUrl;

    /**
     * Priority
     */
    private Integer priority;

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

    /**
     * Get priority
     * @return Priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Set priority
     * @param priority Priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
