package org.mercuriusframework.services;

import org.mercuriusframework.entities.*;

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
     * Get stocks by product uuid and warehouses
     * @param productUuid Product uuid
     * @param warehouses Warehouses
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductUuidAndWarehouses(String productUuid, List<WarehouseEntity> warehouses);

    /**
     * Get stocks by product and unit uuid
     * @param productUuid Product uuid
     * @param unitUuid Unit uuid
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductAndUnitUuid(String productUuid, String unitUuid);

    /**
     * Get stocks by product and unit uuid
     * @param productUuid Product uuid
     * @param unitUuid Unit uuid
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductAndUnitUuid(String productUuid, String unitUuid, boolean enabledWarehouse);

    /**
     * Get stocks by product uuid, unit uuid and warehouses
     * @param productUuid Product uuid
     * @param unitUuid Unit uuid
     * @param warehouses Warehouses
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductAndUnitUuidAndWarehouses(String productUuid, String unitUuid, List<WarehouseEntity> warehouses);

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
     * Get stocks by product and warehouses
     * @param product Product
     * @param warehouses Warehouses
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductAndWarehouses(ProductEntity product, List<WarehouseEntity> warehouses);

    /**
     * Get stocks by product and unit
     * @param product Product
     * @param unit Unit
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductAndUnit(ProductEntity product, UnitEntity unit);

    /**
     * Get stocks by product and unit
     * @param product Product
     * @param unit Unit
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductAndUnit(ProductEntity product, UnitEntity unit, boolean enabledWarehouse);

    /**
     * Get stocks by product and unit and warehouses
     * @param product Product
     * @param unit Unit
     * @param warehouses Warehouses
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductAndUnitAndWarehouses(ProductEntity product, UnitEntity unit, List<WarehouseEntity> warehouses);

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
     * Get stocks by product code and warehouses (use default catalog)
     * @param productCode Product code
     * @param warehouses Warehouses
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductCodeAndWarehouses(String productCode, List<WarehouseEntity> warehouses);

    /**
     * Get stock by product code and unit code (use default catalog)
     * @param productCode Product code
     * @param unitCode Unit code
     * @return Stock
     */
    List<StockEntity> getStocksByProductCodeAndUnitCode(String productCode, String unitCode);

    /**
     * Get stock by product code and unit code (use default catalog)
     * @param productCode Product code
     * @param unitCode Unit code
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return Stock
     */
    List<StockEntity> getStocksByProductCodeAndUnitCode(String productCode, String unitCode, boolean enabledWarehouse);

    /**
     * Get stock by product code and unit code and warehouses (use default catalog)
     * @param productCode Product code
     * @param unitCode Unit code
     * @param warehouses Warehouses
     * @return Stock
     */
    List<StockEntity> getStocksByProductCodeAndUnitCodeAndWarehouses(String productCode, String unitCode, List<WarehouseEntity> warehouses);

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
     * Get stocks by product code and warehouses
     * @param productCode Product code
     * @param catalogCode Catalog code
     * @param warehouses Warehouses
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductCodeAndWarehouses(String productCode, String catalogCode, List<WarehouseEntity> warehouses);

    /**
     * Get stock by product code and unit code
     * @param productCode Product code
     * @param unitCode Unit code
     * @param catalogCode Catalog code
     * @return Stock
     */
    List<StockEntity> getStocksByProductCodeAndUnitCode(String productCode, String unitCode, String catalogCode);

    /**
     * Get stock by product code and unit code
     * @param productCode Product code
     * @param unitCode Unit code
     * @param catalogCode Catalog code
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return Stock
     */
    List<StockEntity> getStocksByProductCodeAndUnitCode(String productCode, String unitCode, String catalogCode, boolean enabledWarehouse);

    /**
     * Get stock by product code and unit code and warehouses
     * @param productCode Product code
     * @param unitCode Unit code
     * @param catalogCode Catalog code
     * @param warehouses Warehouses
     * @return Stock
     */
    List<StockEntity> getStocksByProductCodeAndUnitCodeAndWarehouses(String productCode, String unitCode, String catalogCode, List<WarehouseEntity> warehouses);

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

    /**
     * Get stocks by product code and warehouses
     * @param productCode Product code
     * @param catalog Catalog
     * @param warehouses Warehouses
     * @return List of stocks
     */
    List<StockEntity> getStocksByProductCodeAndWarehouses(String productCode, CatalogEntity catalog, List<WarehouseEntity> warehouses);

    /**
     * Get stock by product code and unit code
     * @param productCode Product code
     * @param unitCode Unit code
     * @param catalog Catalog
     * @return Stock
     */
    List<StockEntity> getStocksByProductCodeAndUnitCode(String productCode, String unitCode, CatalogEntity catalog);

    /**
     * Get stock by product code and unit code
     * @param productCode Product code
     * @param unitCode Unit code
     * @param catalog Catalog
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return Stock
     */
    List<StockEntity> getStocksByProductCodeAndUnitCode(String productCode, String unitCode, CatalogEntity catalog, boolean enabledWarehouse);

    /**
     * Get stock by product code and unit code and warehouses
     * @param productCode Product code
     * @param unitCode Unit code
     * @param catalog Catalog
     * @param warehouses Warehouses
     * @return Stock
     */
    List<StockEntity> getStocksByProductCodeAndUnitCodeAndWarehouses(String productCode, String unitCode, CatalogEntity catalog, List<WarehouseEntity> warehouses);
}
