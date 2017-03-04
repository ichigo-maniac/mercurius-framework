package org.mercuriusframework.dto;

import java.util.List;
import java.util.Map;

/**
 * Product entity data transfer object
 */
public class ProductEntityDto extends CatalogUniqueCodeEntityDto {

    private static final long serialVersionUID = 1512587198530878098L;

    /**
     * Description
     */
    private String description;

    /**
     * Short name
     */
    private String shortName;

    /**
     * Bread crumbs
     */
    private List<CategoryEntityDto> breadCrumbs;

    /**
     * Stocks
     */
    private List<StockEntityDto> stocks;

    /**
     * Stocks map
     */
    private Map<UnitEntityDto, List<StockTotalDto>> stocksMap;

    /**
     * Default stock (for default set unit)
     */
    private StockTotalDto defaultStock;

    /**
     * Get description
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description
     * @param description Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get bread crumbs
     * @return Bread crumbs
     */
    public List<CategoryEntityDto> getBreadCrumbs() {
        return breadCrumbs;
    }

    /**
     * Set bread crumbs
     * @param breadCrumbs Bread crumbs
     */
    public void setBreadCrumbs(List<CategoryEntityDto> breadCrumbs) {
        this.breadCrumbs = breadCrumbs;
    }

    /**
     * Get short name
     * @return Short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Set short name
     * @param shortName Short name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
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
     * Get stocks map
     * @return Stocks map
     */
    public Map<UnitEntityDto, List<StockTotalDto>> getStocksMap() {
        return stocksMap;
    }

    /**
     * Set stocks map
     * @param stocksMap Stocks map
     */
    public void setStocksMap(Map<UnitEntityDto, List<StockTotalDto>> stocksMap) {
        this.stocksMap = stocksMap;
    }

    /**
     * Get default stock (for default set unit)
     * @return
     */
    public StockTotalDto getDefaultStock() {
        return defaultStock;
    }

    /**
     * Set default stock (for default set unit)
     * @param defaultStock Default stock
     */
    public void setDefaultStock(StockTotalDto defaultStock) {
        this.defaultStock = defaultStock;
    }
}
