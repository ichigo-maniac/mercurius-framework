package org.mercuriusframework.services.impl;

import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.entities.StockEntity;
import org.mercuriusframework.entities.WarehouseEntity;
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
                "LEFT JOIN FETCH stock." + StockEntity.UNIT + " " +
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
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " " +
                        "WHERE stock." + StockEntity.PRODUCT + "." + ProductEntity.UUID + " = :productUuid " +
                        "AND stock." + StockEntity.WAREHOUSE + "." + WarehouseEntity.ENABLED + " = :enabledWarehouse",
                StockEntity.class, new QueryParameter("productUuid", productUuid), new QueryParameter("enabledWarehouse", enabledWarehouse));
    }

    /**
     * Get stocks by product
     * @param product Product
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProduct(ProductEntity product) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " " +
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
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " " +
                        "WHERE stock." + StockEntity.PRODUCT + " = :product " +
                        "AND stock." + StockEntity.WAREHOUSE + "." + WarehouseEntity.ENABLED + " = :enabledWarehouse",
                StockEntity.class, new QueryParameter("product", product), new QueryParameter("enabledWarehouse", enabledWarehouse));
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
     * Get stocks by product code
     * @param productCode Product code
     * @param catalogCode Catalog code
     * @return List of stocks
     */
    @Override
    public List<StockEntity> getStocksByProductCode(String productCode, String catalogCode) {
        return entityService.getListResultByQuery("SELECT stock FROM " + StockEntity.ENTITY_NAME + " as stock " +
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " " +
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
                        "LEFT JOIN FETCH stock." + StockEntity.UNIT + " " +
                        "LEFT JOIN stock." + StockEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE +" = :productCode " +
                        "AND product." + ProductEntity.CATALOG + "." + CatalogEntity.CODE + " = :catalogCode " +
                        "AND stock." + StockEntity.WAREHOUSE + "." + WarehouseEntity.ENABLED + " = :enabledWarehouse",
                StockEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalogCode", catalogCode),
                new QueryParameter("enabledWarehouse", enabledWarehouse));
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
}
