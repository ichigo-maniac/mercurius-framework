package org.mercuriusframework.dto;

/**
 * Product entity data transfer object
 */
public class ProductEntityDto extends CatalogUniqueCodeEntityDto {

    /**
     * Short name
     */
    private String shortName;

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
