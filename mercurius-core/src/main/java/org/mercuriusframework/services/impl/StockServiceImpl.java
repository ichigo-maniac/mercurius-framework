package org.mercuriusframework.services.impl;

import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.entities.*;
import org.mercuriusframework.exceptions.DefaultCatalogPresetException;
import org.mercuriusframework.facades.CatalogFacade;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.StockService;
import org.mercuriusframework.services.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Stock service
 */
@Service("stockService")
public class StockServiceImpl implements StockService {

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Catalog facade
     */
    @Autowired
    @Qualifier("catalogFacade")
    protected CatalogFacade catalogFacade;

    /**
     * Get stocks by product uuid
     * @param productUuid Product uuid
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductUuid(String productUuid) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                "WHERE stock." + StockEntity.PRODUCT + "." + ProductEntity.UUID + " = :productUuid",
                StockEntity.class, new QueryParameter("productUuid", productUuid));
    }

    /**
     * Get stocks by product uuid
     * @param productUuid      Product uuid
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductUuid(String productUuid, boolean enabledWarehouse) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "WHERE stock." + StockEntity.PRODUCT + "." + ProductEntity.UUID + " = :productUuid " +
                        "AND stock." + StockEntity.WAREHOUSE + "." + WarehouseEntity.ENABLED + " = :enabledWarehouse",
                StockEntity.class, new QueryParameter("productUuid", productUuid), new QueryParameter("enabledWarehouse", enabledWarehouse));
    }

    /**
     * Get stocks by product and unit uuid
     * @param productUuid Product uuid
     * @param unitUuid    Unit uuid
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductAndUnitUuid(String productUuid, String unitUuid) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "WHERE stock." + StockEntity.PRODUCT + "." + ProductEntity.UUID + " = :productUuid " +
                        "AND unit." + UnitEntity.UUID + " = :unitUuid",
                StockEntity.class, new QueryParameter("productUuid", productUuid), new QueryParameter("unitUuid", unitUuid));
    }

    /**
     * Get stocks by product and unit uuid
     * @param productUuid      Product uuid
     * @param unitUuid         Unit uuid
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductAndUnitUuid(String productUuid, String unitUuid, boolean enabledWarehouse) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "WHERE stock." + StockEntity.PRODUCT + "." + ProductEntity.UUID + " = :productUuid " +
                        "AND unit." + UnitEntity.UUID + " = :unitUuid " +
                        "AND stock." + StockEntity.WAREHOUSE + "." + WarehouseEntity.ENABLED + " = :enabledWarehouse",
                StockEntity.class, new QueryParameter("productUuid", productUuid), new QueryParameter("unitUuid", unitUuid),
                new QueryParameter("enabledWarehouse", enabledWarehouse));
    }

    /**
     * Get stocks by product
     * @param product Product
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProduct(ProductEntity product) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "WHERE stock." + StockEntity.PRODUCT + " = :product",
                StockEntity.class, new QueryParameter("product", product));
    }

    /**
     * Get stocks by product
     * @param product          Product
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProduct(ProductEntity product, boolean enabledWarehouse) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "WHERE stock." + StockEntity.PRODUCT + " = :product " +
                        "AND stock." + StockEntity.WAREHOUSE + "." + WarehouseEntity.ENABLED + " = :enabledWarehouse",
                StockEntity.class, new QueryParameter("product", product), new QueryParameter("enabledWarehouse", enabledWarehouse));
    }

    /**
     * Get stocks by product and unit
     * @param product Product
     * @param unit    Unit
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductAndUnit(ProductEntity product, UnitEntity unit) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "WHERE stock." + StockEntity.PRODUCT + " = :product " +
                        "AND unit = :unit",
                StockEntity.class, new QueryParameter("product", product), new QueryParameter("unit", unit));
    }

    /**
     * Get stocks by product and unit
     * @param product          Product
     * @param unit             Unit
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductAndUnit(ProductEntity product, UnitEntity unit, boolean enabledWarehouse) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "WHERE stock." + StockEntity.PRODUCT + " = :product " +
                        "AND unit" + " = :unit " +
                        "AND stock." + StockEntity.WAREHOUSE + "." + WarehouseEntity.ENABLED + " = :enabledWarehouse",
                StockEntity.class, new QueryParameter("product", product), new QueryParameter("unit", unit),
                new QueryParameter("enabledWarehouse", enabledWarehouse));
    }

    /**
     * Get stocks by product code (use default catalog)
     * @param productCode Product code
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductCode(String productCode) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getStocksByProductCode(productCode, catalog.getCode());
    }

    /**
     * Get stocks by product code (use default catalog)
     * @param productCode      Product code
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductCode(String productCode, boolean enabledWarehouse) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getStocksByProductCode(productCode, catalog.getCode(), enabledWarehouse);
    }

    /**
     * Get stock by product code and unit code (use default catalog)
     * @param productCode Product code
     * @param unitCode    Unit code
     * @return Stock
     */
    @Override
    public List<StockEntity> getStocksByProductCodeAndUnitCode(String productCode, String unitCode) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getStocksByProductCodeAndUnitCode(productCode, unitCode, catalog.getCode());
    }

    /**
     * Get stock by product code and unit code (use default catalog)
     *
     * @param productCode      Product code
     * @param unitCode         Unit code
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return Stock
     */
    @Override
    public List<StockEntity> getStocksByProductCodeAndUnitCode(String productCode, String unitCode, boolean enabledWarehouse) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getStocksByProductCodeAndUnitCode(productCode, unitCode, catalog.getCode(), enabledWarehouse);
    }

    /**
     * Get stocks by product code
     * @param productCode Product code
     * @param catalogCode Catalog code
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductCode(String productCode, String catalogCode) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "LEFT JOIN stock." + StockEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE +" = :productCode " +
                        "AND product." + ProductEntity.CATALOG + "." + CatalogEntity.CODE + " = :catalogCode",
                StockEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalogCode", catalogCode));
    }

    /**
     * Get stocks by product code
     * @param productCode      Product code
     * @param catalogCode      Catalog code
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductCode(String productCode, String catalogCode, boolean enabledWarehouse) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "LEFT JOIN stock." + StockEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE +" = :productCode " +
                        "AND product." + ProductEntity.CATALOG + "." + CatalogEntity.CODE + " = :catalogCode " +
                        "AND stock." + StockEntity.WAREHOUSE + "." + WarehouseEntity.ENABLED + " = :enabledWarehouse",
                StockEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalogCode", catalogCode),
                new QueryParameter("enabledWarehouse", enabledWarehouse));
    }

    /**
     * Get stock by product code and unit code (use default catalog)
     * @param productCode Product code
     * @param unitCode    Unit code
     * @param catalogCode Catalog code
     * @return Stock
     */
    @Override
    public List<StockEntity> getStocksByProductCodeAndUnitCode(String productCode, String unitCode, String catalogCode) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "LEFT JOIN stock." + StockEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE +" = :productCode " +
                        "AND unit." + UnitEntity.CODE + " = :unitCode " +
                        "AND product." + ProductEntity.CATALOG + "." + CatalogEntity.CODE + " = :catalogCode",
                StockEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalogCode", catalogCode),
                new QueryParameter("unitCode", unitCode));
    }

    /**
     * Get stock by product code and unit code (use default catalog)
     *
     * @param productCode      Product code
     * @param unitCode         Unit code
     * @param catalogCode      Catalog code
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return Stock
     */
    @Override
    public List<StockEntity> getStocksByProductCodeAndUnitCode(String productCode, String unitCode, String catalogCode, boolean enabledWarehouse) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "LEFT JOIN stock." + StockEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE +" = :productCode " +
                        "AND unit." + UnitEntity.CODE + " = :unitCode " +
                        "AND product." + ProductEntity.CATALOG + "." + CatalogEntity.CODE + " = :catalogCode " +
                        "AND stock." + StockEntity.WAREHOUSE + "." + WarehouseEntity.ENABLED + " = :enabledWarehouse",
                StockEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalogCode", catalogCode),
                new QueryParameter("enabledWarehouse", enabledWarehouse), new QueryParameter("unitCode", unitCode));
    }

    /**
     * Get stocks by product code
     * @param productCode Product code
     * @param catalog     Catalog
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductCode(String productCode, CatalogEntity catalog) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " " +
                        "LEFT JOIN stock." + StockEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE +" = :productCode " +
                        "AND product." + ProductEntity.CATALOG + " = :catalog",
                StockEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalog", catalog));
    }

    /**
     * Get stocks by product code
     * @param productCode      Product code
     * @param catalog          Catalog
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductCode(String productCode, CatalogEntity catalog, boolean enabledWarehouse) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " " +
                        "LEFT JOIN stock." + StockEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE +" = :productCode " +
                        "AND product." + ProductEntity.CATALOG + " = :catalog " +
                        "AND stock." + StockEntity.WAREHOUSE + "." + WarehouseEntity.ENABLED + " = :enabledWarehouse",
                StockEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalog", catalog),
                new QueryParameter("enabledWarehouse", enabledWarehouse));
    }

    /**
     * Get stock by product code and unit code (use default catalog)
     * @param productCode Product code
     * @param unitCode    Unit code
     * @param catalog     Catalog
     * @return Stock
     */
    @Override
    public List<StockEntity> getStocksByProductCodeAndUnitCode(String productCode, String unitCode, CatalogEntity catalog) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "LEFT JOIN stock." + StockEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE +" = :productCode " +
                        "AND unit." + UnitEntity.CODE + " = :unitCode " +
                        "AND product." + ProductEntity.CATALOG + " = :catalog",
                StockEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalog", catalog),
                new QueryParameter("unitCode", unitCode));
    }

    /**
     * Get stock by product code and unit code (use default catalog)
     * @param productCode      Product code
     * @param unitCode         Unit code
     * @param catalog          Catalog
     * @param enabledWarehouse Is stock's warehouse enabled
     * @return Stock
     */
    @Override
    public List<StockEntity> getStocksByProductCodeAndUnitCode(String productCode, String unitCode, CatalogEntity catalog, boolean enabledWarehouse) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " as unit " +
                        "LEFT JOIN stock." + StockEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE +" = :productCode " +
                        "AND unit." + UnitEntity.CODE + " = :unitCode " +
                        "AND product." + ProductEntity.CATALOG + " = :catalog " +
                        "AND stock." + StockEntity.WAREHOUSE + "." + WarehouseEntity.ENABLED + " = :enabledWarehouse",
                StockEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalog", catalog),
                new QueryParameter("enabledWarehouse", enabledWarehouse), new QueryParameter("unitCode", unitCode));
    }
}
