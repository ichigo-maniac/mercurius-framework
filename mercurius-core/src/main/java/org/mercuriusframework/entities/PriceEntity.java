package org.mercuriusframework.entities;

import javax.persistence.*;

/**
 * Price entity class
 */
@Entity(name = PriceEntity.ENTITY_NAME)
@Table(name = "PRICE")
public class PriceEntity extends CatalogUniqueCodeEntity {

    private static final long serialVersionUID = -1152130277804010010L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Price";

    /**
     * Unit
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "UNIT_UUID", referencedColumnName = "uuid", nullable = false)
    private UnitEntity unit;
    public static final String UNIT = "unit";

    /**
     * Product
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_UUID", referencedColumnName = "uuid", nullable = false)
    private ProductEntity product;
    public static final String PRODUCT = "product";

    /**
     * Price value
     */
    @Basic(optional = false)
    private Double priceValue;
    public static final String PRICE_VALUE = "priceValue";

    /**
     * Currency
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENCY_UUID", referencedColumnName = "uuid", nullable = false)
    private CurrencyEntity currency;
    public static final String CURRENCY = "currency";

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
     * Get price value
     * @return Price value
     */
    public Double getPriceValue() {
        return priceValue;
    }

    /**
     * Set price value
     * @param priceValue Price value
     */
    public void setPriceValue(Double priceValue) {
        this.priceValue = priceValue;
    }

    /**
     * Get currency
     * @return Currency
     */
    public CurrencyEntity getCurrency() {
        return currency;
    }

    /**
     * Set currency
     * @param currency Currency
     */
    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }
}
