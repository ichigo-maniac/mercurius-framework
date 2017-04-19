package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Warehouse entity class
 */
@Entity(name = WarehouseEntity.ENTITY_NAME)
@Table(name = "WAREHOUSES")
public class WarehouseEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = 4082941005457125655L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Warehouse";

    /**
     * Is a warehouse enabled
     */
    @Basic(optional = true)
    private Boolean enabled;
    public static final String ENABLED = "enabled";

    /**
     * Store
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_UUID", referencedColumnName = "uuid", nullable = false)
    private StoreEntity store;
    public static final String STORE = "store";

    /**
     * Stocks
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = StockEntity.WAREHOUSE, cascade = CascadeType.ALL)
    private List<StockEntity> stocks;
    public static final String STOCKS = "stocks";

    /**
     * Get "Is a warehouse enabled"
     * @return "Is a warehouse enabled" flag
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Set "Is a warehouse enabled"
     * @param enabled "Is a warehouse enabled" flag
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Get a store
     * @return Store
     */
    public StoreEntity getStore() {
        return store;
    }

    /**
     * Set a store
     * @param store Store
     */
    public void setStore(StoreEntity store) {
        this.store = store;
    }

    /**
     * Get stocks
     * @return Stocks
     */
    public List<StockEntity> getStocks() {
        return stocks;
    }

    /**
     * Set stocks
     * @param stocks Stocks
     */
    public void setStocks(List<StockEntity> stocks) {
        this.stocks = stocks;
    }
}
