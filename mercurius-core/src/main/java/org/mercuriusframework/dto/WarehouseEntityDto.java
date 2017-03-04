package org.mercuriusframework.dto;

/**
 * Warehouse entity data transfer object
 */
public class WarehouseEntityDto extends UniqueCodeEntityDto {

    /**
     * Is a store enabled
     */
    private Boolean enabled;

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
}
