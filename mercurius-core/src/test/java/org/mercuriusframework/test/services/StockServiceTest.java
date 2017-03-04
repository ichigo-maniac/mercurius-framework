package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.entities.StockEntity;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.StockService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Stock service test
 */
public class StockServiceTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> ALL_STOCKS_UUIDS_LIST = Arrays.asList(
            "12345e10-1a94-22e6-b6ff-abd422223333", "12345e10-1594-22e6-b6ff-abd422555333",
            "12345e10-0000-22e6-b6ff-abd422223333"
    );

    private static final List<String> ALL_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST = Arrays.asList(
            "12345e10-1a94-22e6-b6ff-abd422223333", "12345e10-1594-22e6-b6ff-abd422555333"
    );

    /**
     * Stock service
     */
    @Autowired
    private StockService stockService;

    /**
     * Catalog unique code entity service
     */
    @Autowired
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - stockService.getStocksByProduct...
     */
    @Test
    public void getStocksByProductTest() {
        List<StockEntity> stocks = stockService.getStocksByProductUuid("a1f0b496-fa94-11e6-b701-b31dc35d6666");
        assertUuidListsEquals(ALL_STOCKS_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test - stockService.getStocksByProduct...
     */
    @Test
    public void getStocksByProductTest11() {
        List<StockEntity> stocks = stockService.getStocksByProductUuid("a1f0b496-fa94-11e6-b701-b31dc35d6666", true);
        assertUuidListsEquals(ALL_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test (second) - stockService.getStocksByProduct...
     */
    @Test
    public void getStocksByProductTest2() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_prince_of_tennis_01", "master_catalog", ProductEntity.class);
        List<StockEntity> stocks = stockService.getStocksByProduct(product);
        assertUuidListsEquals(ALL_STOCKS_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test (second) - stockService.getStocksByProduct...
     */
    @Test
    public void getStocksByProductTest22() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_prince_of_tennis_01", "master_catalog", ProductEntity.class);
        List<StockEntity> stocks = stockService.getStocksByProduct(product, true);
        assertUuidListsEquals(ALL_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test (third)- stockService.getStocksByProduct...
     */
    @Test
    public void getStocksByProductTest3() {
        List<StockEntity> stocks = stockService.getStocksByProductCode("product_prince_of_tennis_01", "master_catalog");
        assertUuidListsEquals(ALL_STOCKS_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test (third)- stockService.getStocksByProduct...
     */
    @Test
    public void getStocksByProductTest33() {
        List<StockEntity> stocks = stockService.getStocksByProductCode("product_prince_of_tennis_01", "master_catalog", true);
        assertUuidListsEquals(ALL_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test (fourth) - stockService.getStocksByProduct...
     */
    @Test
    public void getStocksByProductTest4() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<StockEntity> stocks = stockService.getStocksByProductCode("product_prince_of_tennis_01", catalog);
        assertUuidListsEquals(ALL_STOCKS_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test (fourth) - stockService.getStocksByProduct...
     */
    @Test
    public void getStocksByProductTest44() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<StockEntity> stocks = stockService.getStocksByProductCode("product_prince_of_tennis_01", catalog, true);
        assertUuidListsEquals(ALL_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST, getUuids(stocks));
    }
}
