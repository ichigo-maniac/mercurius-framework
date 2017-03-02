package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Warehouse entity class
 */
@Entity(name = WarehouseEntity.ENTITY_NAME)
@Table(name = "WAREHOUSE")
public class WarehouseEntity extends UniqueCodeEntity {

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Warehouse";

    /**
     * Is a warehouse disabled
     */
    @Basic(optional = true)
    private Boolean disabled;
    public static final String DISABLED = "disabled";

    /**
     * Store
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "STORE_UUID", referencedColumnName = "uuid", nullable = false)
    private StoreEntity store;
    public static final String STORE = "store";

    /**
     * Stocks
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = StockEntity.WAREHOUSE)
    private List<StockEntity> stocks;
    public static final String STOCKS = "stocks";

    /**
     * Get "Is a warehouse disabled"
     * @return "Is a warehouse disabled" flag
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * Set "Is a warehouse disabled"
     * @param disabled "Is a warehouse disabled" flag
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
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
