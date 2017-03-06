package org.mercuriusframework.dto;

/**
 * Price entity data transfer object
 */
public class PriceEntityDto extends CatalogUniqueCodeEntityDto {

    private static final long serialVersionUID = -5842289601848808822L;

    /**
     * Unit
     */
    private UnitEntityDto unit;

    /**
     * Product
     */
    private ProductEntityDto product;

    /**
     * Price value
     */
    private Double priceValue;

    /**
     * Currency
     */
    private CurrencyEntityDto currency;

    /**
     * Get a product
     * @return Product
     */
    public ProductEntityDto getProduct() {
        return product;
    }

    /**
     * Set a product
     * @param product Product
     */
    public void setProduct(ProductEntityDto product) {
        this.product = product;
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
    public CurrencyEntityDto getCurrency() {
        return currency;
    }

    /**
     * Set currency
     * @param currency Currency
     */
    public void setCurrency(CurrencyEntityDto currency) {
        this.currency = currency;
    }
}
