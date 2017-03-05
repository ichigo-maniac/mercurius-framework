package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.entities.*;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.StockService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.services.WarehouseService;
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
            "12345e10-0000-22e6-b6ff-abd422223333", "12345e10-0000-22e6-4444-abd422555333"
    );

    private static final List<String> PIECES_STOCKS_UUIDS_LIST = Arrays.asList(
            "12345e10-1a94-22e6-b6ff-abd422223333", "12345e10-0000-22e6-b6ff-abd422223333"
    );

    private static final List<String> ALL_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST = Arrays.asList(
            "12345e10-1a94-22e6-b6ff-abd422223333", "12345e10-1594-22e6-b6ff-abd422555333",
            "12345e10-0000-22e6-4444-abd422555333"
    );

    private static final List<String> PIECES_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST = Arrays.asList(
            "12345e10-1a94-22e6-b6ff-abd422223333"
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
     * Warehouse service
     */
    @Autowired
    private WarehouseService warehouseService;

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
     * Method test - stockService.getStocksByProduct...
     */
    @Test
    public void getStocksByProductTest111() {
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreCode("default_store");
        List<StockEntity> stocks = stockService.getStocksByProductUuidAndWarehouses("a1f0b496-fa94-11e6-b701-b31dc35d6666", warehouses);
        assertUuidListsEquals(ALL_STOCKS_UUIDS_LIST, getUuids(stocks));
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
     * Method test (second) - stockService.getStocksByProduct...
     */
    @Test
    public void getStocksByProductTest222() {
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreCode("default_store", true);
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_prince_of_tennis_01", "master_catalog", ProductEntity.class);
        List<StockEntity> stocks = stockService.getStocksByProductAndWarehouses(product, warehouses);
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
     * Method test (third)- stockService.getStocksByProduct...
     */
    @Test
    public void getStocksByProductTest333() {
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreCode("default_store", true);
        List<StockEntity> stocks = stockService.getStocksByProductCodeAndWarehouses("product_prince_of_tennis_01", "master_catalog", warehouses);
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

    /**
     * Method test (fourth) - stockService.getStocksByProduct...
     */
    @Test
    public void getStocksByProductTest444() {
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreCode("default_store", true);
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<StockEntity> stocks = stockService.getStocksByProductCodeAndWarehouses("product_prince_of_tennis_01", catalog, warehouses);
        assertUuidListsEquals(ALL_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test - stockService.getStocksByProductAndUnit...
     */
    @Test
    public void getStocksByProductAndUnitTest() {
        List<StockEntity> stocks = stockService.getStocksByProductAndUnitUuid("a1f0b496-fa94-11e6-b701-b31dc35d6666",
                "a1e2ae50-fa94-11e6-b6f6-67b357732118");
        assertUuidListsEquals(PIECES_STOCKS_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test - stockService.getStocksByProductAndUnit...
     */
    @Test
    public void getStocksByProductAndUnitTest11() {
        List<StockEntity> stocks = stockService.getStocksByProductAndUnitUuid("a1f0b496-fa94-11e6-b701-b31dc35d6666",
                "a1e2ae50-fa94-11e6-b6f6-67b357732118", true);
        assertUuidListsEquals(PIECES_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test - stockService.getStocksByProductAndUnit...
     */
    @Test
    public void getStocksByProductAndUnitTest111() {
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreCode("default_store");
        List<StockEntity> stocks = stockService.getStocksByProductAndUnitUuidAndWarehouses("a1f0b496-fa94-11e6-b701-b31dc35d6666",
                "a1e2ae50-fa94-11e6-b6f6-67b357732118", warehouses);
        assertUuidListsEquals(PIECES_STOCKS_UUIDS_LIST, getUuids(stocks));
    }


    /**
     * Method test - stockService.getStocksByProductAndUnit...
     */
    @Test
    public void getStocksByProductAndUnitTest2() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_prince_of_tennis_01", "master_catalog", ProductEntity.class);
        UnitEntity unit = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("pieces", "master_catalog", UnitEntity.class);
        List<StockEntity> stocks = stockService.getStocksByProductAndUnit(product, unit);
        assertUuidListsEquals(PIECES_STOCKS_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test - stockService.getStocksByProductAndUnit...
     */
    @Test
    public void getStocksByProductAndUnitTest22() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_prince_of_tennis_01", "master_catalog", ProductEntity.class);
        UnitEntity unit = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("pieces", "master_catalog", UnitEntity.class);
        List<StockEntity> stocks = stockService.getStocksByProductAndUnit(product, unit, true);
        assertUuidListsEquals(PIECES_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test - stockService.getStocksByProductAndUnit...
     */
    @Test
    public void getStocksByProductAndUnitTest222() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_prince_of_tennis_01", "master_catalog", ProductEntity.class);
        UnitEntity unit = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("pieces", "master_catalog", UnitEntity.class);
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreCode("default_store");
        List<StockEntity> stocks = stockService.getStocksByProductAndUnitAndWarehouses(product, unit, warehouses);
        assertUuidListsEquals(PIECES_STOCKS_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test - stockService.getStocksByProductAndUnit...
     */
    @Test
    public void getStocksByProductAndUnitTest3() {
        List<StockEntity> stocks = stockService.getStocksByProductCodeAndUnitCode("product_prince_of_tennis_01", "pieces",
                "master_catalog");
        assertUuidListsEquals(PIECES_STOCKS_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test - stockService.getStocksByProductAndUnit...
     */
    @Test
    public void getStocksByProductAndUnitTest33() {
        List<StockEntity> stocks = stockService.getStocksByProductCodeAndUnitCode("product_prince_of_tennis_01", "pieces",
                "master_catalog", true);
        assertUuidListsEquals(PIECES_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test - stockService.getStocksByProductAndUnit...
     */
    @Test
    public void getStocksByProductAndUnitTest333() {
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreCode("default_store", true);
        List<StockEntity> stocks = stockService.getStocksByProductCodeAndUnitCodeAndWarehouses("product_prince_of_tennis_01", "pieces",
                "master_catalog", warehouses);
        assertUuidListsEquals(PIECES_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test - stockService.getStocksByProductAndUnit...
     */
    @Test
    public void getStocksByProductAndUnitTest4() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<StockEntity> stocks = stockService.getStocksByProductCodeAndUnitCode("product_prince_of_tennis_01", "pieces",
                catalog);
        assertUuidListsEquals(PIECES_STOCKS_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test - stockService.getStocksByProductAndUnit...
     */
    @Test
    public void getStocksByProductAndUnitTest44() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<StockEntity> stocks = stockService.getStocksByProductCodeAndUnitCode("product_prince_of_tennis_01", "pieces",
                catalog, true);
        assertUuidListsEquals(PIECES_STOCKS_FROM_ENABLE_WAREHOUSES_UUIDS_LIST, getUuids(stocks));
    }

    /**
     * Method test - stockService.getStocksByProductAndUnit...
     */
    @Test
    public void getStocksByProductAndUnitTest444() {
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreCode("default_store");
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<StockEntity> stocks = stockService.getStocksByProductCodeAndUnitCodeAndWarehouses("product_prince_of_tennis_01", "pieces",
                catalog, warehouses);
        assertUuidListsEquals(PIECES_STOCKS_UUIDS_LIST, getUuids(stocks));
    }
}
