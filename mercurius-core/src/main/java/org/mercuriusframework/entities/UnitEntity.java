package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Unit entity class
 */
@Entity(name = UnitEntity.ENTITY_NAME)
@Table(name = "UNITS")
public class UnitEntity extends CatalogUniqueCodeEntity {

    private static final long serialVersionUID = 3206380992490096733L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Unit";

    /**
     *  Products
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRODUCTS_UNITS_LINKS",
            joinColumns = {
                    @JoinColumn(name = "UNIT_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "PRODUCT_UUID",
                            nullable = false, updatable = false)})
    private Set<ProductEntity> products;
    public static final String PRODUCTS = "products";

    /**
     * Stocks
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = StockEntity.UNIT, cascade = CascadeType.ALL)
    private List<StockEntity> stocks;
    public static final String STOCKS = "stocks";

    /**
     * Prices
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = PriceEntity.UNIT, cascade = CascadeType.ALL)
    private List<PriceEntity> prices;
    public static final String PRICES = "prices";

    /**
     * Get products
     * @return Products
     */
    public Set<ProductEntity> getProducts() {
        return products;
    }

    /**
     * Set products
     * @param products Products
     */
    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
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

    /**
     * Get prices
     * @return Prices
     */
    public List<PriceEntity> getPrices() {
        return prices;
    }

    /**
     * Set prices
     * @param prices Prices
     */
    public void setPrices(List<PriceEntity> prices) {
        this.prices = prices;
    }
}
