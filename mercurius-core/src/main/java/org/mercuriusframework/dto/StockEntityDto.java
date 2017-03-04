package org.mercuriusframework.dto;

/**
 * Stock entity data transfer object
 */
public class StockEntityDto extends UniqueCodeEntityDto {

    private static final long serialVersionUID = 2544787815421384693L;

    /**
     * Is a stock enabled
     */
    private Boolean enabled;

    /**
     * Unit
     */
    private UnitEntityDto unit;

    /**
     * Get "Is a stock enabled"
     * @return "Is a stock enabled" flag
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Set "Is a stock enabled"
     * @param enabled "Is a stock enabled" flag
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Get unit
     * @return Unit
     */
    public UnitEntityDto getUnit() {
        return unit;
    }

    /**
     * Set unit
     * @param unit Unit
     */
    public void setUnit(UnitEntityDto unit) {
        this.unit = unit;
    }
}
