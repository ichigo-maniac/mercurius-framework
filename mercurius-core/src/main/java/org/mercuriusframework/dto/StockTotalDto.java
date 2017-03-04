package org.mercuriusframework.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Stock total data transfer object
 */
public class StockTotalDto implements Serializable {

    private static final long serialVersionUID = -5583772339009004911L;

    /**
     * Unit
     */
    private UnitEntityDto unit;

    /**
     * All stocks
     */
    private List<StockEntityDto> stocks;

    /**
     * Total products count
     */
    private Long totalProductsCount;

    /**
     * Available products count
     */
    private Long availableProductsCount;

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
     * Get stocks
     * @return Stocks
     */
    public List<StockEntityDto> getStocks() {
        return stocks;
    }

    /**
     * Set stocks
     * @param stocks Stocks
     */
    public void setStocks(List<StockEntityDto> stocks) {
        this.stocks = stocks;
    }

    /**
     * Get total products count
     * @return Total products count
     */
    public Long getTotalProductsCount() {
        return totalProductsCount;
    }

    /**
     * Set total products count
     * @param totalProductsCount Total products count
     */
    public void setTotalProductsCount(Long totalProductsCount) {
        this.totalProductsCount = totalProductsCount;
    }

    /**
     * Get available products count
     * @return Available products count
     */
    public Long getAvailableProductsCount() {
        return availableProductsCount;
    }

    /**
     * Set available products count
     * @param availableProductsCount Available products count
     */
    public void setAvailableProductsCount(Long availableProductsCount) {
        this.availableProductsCount = availableProductsCount;
    }
}
