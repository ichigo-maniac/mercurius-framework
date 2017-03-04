package org.mercuriusframework.dto;

/**
 * Warehouse entity data transfer object
 */
public class WarehouseEntityDto extends UniqueCodeEntityDto {

    private static final long serialVersionUID = 8542026491370013340L;

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
