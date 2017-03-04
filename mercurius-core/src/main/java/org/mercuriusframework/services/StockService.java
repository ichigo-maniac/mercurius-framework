package org.mercuriusframework.services;

import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.entities.StockEntity;
import java.util.List;

/**
 * Stock service interface
 */
public interface StockService {

    /**
     * Get stocks by product uuid
     * @param productUuid Product uuid
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductUuid(String productUuid);

    /**
     * Get stocks by product uuid
     * @param productUuid Product uuid
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductUuid(String productUuid, boolean enabledWarehouse);

    /**
     * Get stocks by product
     * @param product Product
     * @return List of stocks
     */
    List<StockEntity> getStocksByProduct(ProductEntity product);

    /**
     * Get stocks by product
     * @param product Product
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    List<StockEntity> getStocksByProduct(ProductEntity product, boolean enabledWarehouse);

    /**
     * Get stocks by product code (use default catalog)
     * @param productCode Product code
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductCode(String productCode);

    /**
     * Get stocks by product code (use default catalog)
     * @param productCode Product code
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductCode(String productCode, boolean enabledWarehouse);

    /**
     * Get stocks by product code
     * @param productCode Product code
     * @param catalogCode Catalog code
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductCode(String productCode, String catalogCode);

    /**
     * Get stocks by product code
     * @param productCode Product code
     * @param catalogCode Catalog code
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductCode(String productCode, String catalogCode, boolean enabledWarehouse);

    /**
     * Get stocks by product code
     * @param productCode Product code
     * @param catalog Catalog
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductCode(String productCode, CatalogEntity catalog);

    /**
     * Get stocks by product code
     * @param productCode Product code
     * @param catalog Catalog
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductCode(String productCode, CatalogEntity catalog, boolean enabledWarehouse);
}
