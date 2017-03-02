package org.mercuriusframework.entities;

import javax.persistence.*;

/**
 * Stock entity class
 */
@Entity(name = StockEntity.ENTITY_NAME)
@Table(name = "STOCK")
public class StockEntity extends UniqueCodeEntity {

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Stock";

    /**
     * Product
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
    private Long count;
    public static final String COUNT = "count";

    /**
     * Warehouse
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "WAREHOUSE_UUID", referencedColumnName = "uuid", nullable = false)
    private WarehouseEntity warehouse;
    public static final String WAREHOUSE = "warehouse";

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
