package org.mercuriusframework.dto;

/**
 * Warehouse entity data transfer object
 */
public class WarehouseEntityDto extends UniqueCodeEntityDto {

    /**
     * Is a store disabled
     */
    private Boolean disabled;

    /**
     * Get "Is a store disabled"
     * @return "Is a store disabled" flag
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * Set "Is a store disabled"
     * @param disabled "Is a store disabled" flag
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
