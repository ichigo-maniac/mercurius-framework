package org.mercuriusframework.dto;

import java.util.List;

/**
 * Store entity data transfer object
 */
public class StoreEntityDto extends UniqueCodeEntityDto {

    private static final long serialVersionUID = -5870110846350796493L;

    /**
     * Is a store enabled
     */
    private Boolean enabled;

    /**
     * Warehouses
     */
    private List<WarehouseEntityDto> warehouses;

    /**
     * Get "Is a store enabled"
     * @return "Is a store enabled" flag
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Set "Is a store enabled"
     * @param enabled "Is a store enabled" flag
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
