package org.mercuriusframework.dto;

import java.util.List;

/**
 * Store entity data transfer object
 */
public class StoreEntityDto extends UniqueCodeEntityDto {

    /**
     * Is a store disabled
     */
    private Boolean disabled;

    /**
     * Warehouses
     */
    private List<WarehouseEntityDto> warehouses;

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

    /**
     * Get warehouses
     * @return List of warehouses
     */
    public List<WarehouseEntityDto> getWarehouses() {
        return warehouses;
    }

    /**
     * Set warehouses
     * @param warehouses List of warehouses
     */
    public void setWarehouses(List<WarehouseEntityDto> warehouses) {
        this.warehouses = warehouses;
    }
}
