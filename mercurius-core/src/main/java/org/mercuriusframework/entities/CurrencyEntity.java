package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Currency entity class
 */
@Entity(name = CurrencyEntity.ENTITY_NAME)
@Table(name = "CURRENCIES")
public class CurrencyEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = 3331676321261038286L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Currency";

    /**
     * Symbol
     */
    @Basic(optional = false)
    private String symbol;
    public static final String SYMBOL = "symbol";

    /**
     * Stores
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "STORES_CURRENCIES_LINKS",
            joinColumns = {
                    @JoinColumn(name = "CURRENCY_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "STORE_UUID",
                            nullable = false, updatable = false)})
    private List<StoreEntity> stores;
    public static final String STORES = "stores";

    /**
     * Get symbol
     * @return Symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Set symbol
     * @param symbol Symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Get stores
     * @return Stores
     */
    public List<StoreEntity> getStores() {
        return stores;
    }

    /**
     * Set stores
     * @param stores Stores
     */
    public void setStores(List<StoreEntity> stores) {
        this.stores = stores;
    }
}
