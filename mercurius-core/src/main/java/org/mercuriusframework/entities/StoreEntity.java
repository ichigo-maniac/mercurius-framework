package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Store entity class
 */
@Entity(name = StoreEntity.ENTITY_NAME)
@Table(name = "STORE")
public class StoreEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = -1603142104877065483L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Store";

    /**
     * Is a store enabled
     */
    @Basic(optional = true)
    private Boolean enabled;
    public static final String ENABLED = "enabled";

    /**
     * Warehouses
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = WarehouseEntity.STORE)
    private List<WarehouseEntity> warehouses;
    public static final String WAREHOUSES = "warehouses";

    /**
     * Currencies
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "STORES_CURRENCIES_LINK",
            joinColumns = {
                    @JoinColumn(name = "STORE_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "CURRENCY_UUID",
                            nullable = false, updatable = false)})
    private List<CurrencyEntity> currencies;
    public static final String CURRENCIES = "currencies";

    /**
     * Default currency
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEFAULT_CURRENCY_UUID", referencedColumnName = "UUID")
    private CurrencyEntity defaultCurrency;
    public static final String DEFAULT_CURRENCY = "defaultCurrency";

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
     * @return Warehouses
     */
    public List<WarehouseEntity> getWarehouses() {
        return warehouses;
    }

    /**
     * Set warehouses
     * @param warehouses Warehouses
     */
    public void setWarehouses(List<WarehouseEntity> warehouses) {
        this.warehouses = warehouses;
    }

    /**
     * Get currencies
     * @return Currencies
     */
    public List<CurrencyEntity> getCurrencies() {
        return currencies;
    }

    /**
     * Set currencies
     * @param currencies Currencies
     */
    public void setCurrencies(List<CurrencyEntity> currencies) {
        this.currencies = currencies;
    }

    /**
     * Get default currency
     * @return Default currency
     */
    public CurrencyEntity getDefaultCurrency() {
        return defaultCurrency;
    }

    /**
     * Set default currency
     * @param defaultCurrency Default currency
     */
    public void setDefaultCurrency(CurrencyEntity defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
}
