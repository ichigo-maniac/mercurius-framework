package org.mercuriusframework.entities;

import javax.persistence.*;

/**
 * Stock entity class
 */
@Entity(name = StockEntity.ENTITY_NAME)
@Table(name = "STOCK")
public class StockEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = 7058020107791400365L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Stock";

    /**
     * Is a stock enabled
     */
    @Basic(optional = true)
    private Boolean enabled;
    public static final String ENABLED = "enabled";

    /**
     * Product
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_UUID", referencedColumnName = "uuid", nullable = false)
    private ProductEntity product;
    public static final String PRODUCT = "product";

    /**
     * Unit
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIT_UUID", referencedColumnName = "uuid", nullable = false)
    private UnitEntity unit;
    public static final String UNIT = "unit";

    /**
     * Product's count
     */
    @Basic(optional = false)
    @Column(name = "product_count")
    private Long count;
    public static final String COUNT = "count";

    /**
     * Warehouse
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_UUID", referencedColumnName = "uuid", nullable = false)
    private WarehouseEntity warehouse;
    public static final String WAREHOUSE = "warehouse";

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
     * Get a product
     * @return Product
     */
    public ProductEntity getProduct() {
        return product;
    }

    /**
     * Set a product
     * @param product Product
     */
    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    /**
     * Get unit
     * @return Unit
     */
    public UnitEntity getUnit() {
        return unit;
    }

    /**
     * Set unit
     * @param unit Unit
     */
    public void setUnit(UnitEntity unit) {
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

    /**
     * Get a warehouse
     * @return Warehouse
     */
    public WarehouseEntity getWarehouse() {
        return warehouse;
    }

    /**
     * Set a warehouse
     * @param warehouse Warehouse
     */
    public void setWarehouse(WarehouseEntity warehouse) {
        this.warehouse = warehouse;
    }
}
