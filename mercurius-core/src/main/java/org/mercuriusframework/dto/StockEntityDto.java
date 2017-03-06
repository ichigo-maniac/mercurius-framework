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
     * Product's count
     */
    private Long count;

    /**
     * Product
     */
    private ProductEntityDto product;

    /**
     * Warehouse
     */
    private WarehouseEntityDto warehouse;

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
     * Get product
     * @return Product
     */
    public ProductEntityDto getProduct() {
        return product;
    }

    /**
     * Set product
     * @param product Product
     */
    public void setProduct(ProductEntityDto product) {
        this.product = product;
    }

    /**
     * Get warehouse
     * @return Warehouse
     */
    public WarehouseEntityDto getWarehouse() {
        return warehouse;
    }

    /**
     * Set warehouse
     * @param warehouse Warehouse
     */
    public void setWarehouse(WarehouseEntityDto warehouse) {
        this.warehouse = warehouse;
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

    /**
     * Get product's count
     * @return Product's count
     */
    public Long getCount() {
        return count;
    }

    /**
     * Set product's count
     * @param count Product's count
     */
    public void setCount(Long count) {
        this.count = count;
    }

}
