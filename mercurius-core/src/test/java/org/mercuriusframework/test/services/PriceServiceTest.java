package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.entities.*;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.PriceService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Price service test
 */
public class PriceServiceTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> ALL_PRICES_UUIDS_LIST = Arrays.asList(
            "a7478e10-2345-11e6-5372-bf2400ed613a", "a7478e10-fa94-0045-5372-bf2400ed613a",
            "07478e10-fa94-11e6-5372-bf2111ed613u", "a7478e10-7423-11e6-5372-bf240073725a"
    );

    private static final List<String> PIECES_PRICES_UUIDS_LIST = Arrays.asList(
            "a7478e10-2345-11e6-5372-bf2400ed613a", "07478e10-fa94-11e6-5372-bf2111ed613u"
    );

    private static final List<String> USD_PRICES_UUIDS_LIST = Arrays.asList(
            "07478e10-fa94-11e6-5372-bf2111ed613u", "a7478e10-7423-11e6-5372-bf240073725a"
    );

    private static final List<String> PIECES_RUB_PRICES_UUIDS_LIST = Arrays.asList(
            "a7478e10-2345-11e6-5372-bf2400ed613a"
    );

    /**
     * Price service
     */
    @Autowired
    private PriceService priceService;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Catalog unique code entity service
     */
    @Autowired
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Method test - priceService.getPricesByProduct...
     */
    @Test
    public void getPricesByProductTest() {
        List<PriceEntity> prices = priceService.getPricesByProductUuid("a1f1016c-fa94-11e6-b704-cb129d9d0314");
        assertUuidListsEquals(ALL_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProduct...
     */
    @Test
    public void getPricesByProductTest2() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_04", "master_catalog", ProductEntity.class);
        List<PriceEntity> prices = priceService.getPricesByProduct(product);
        assertUuidListsEquals(ALL_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProduct...
     */
    @Test
    public void getPricesByProductTest3() {
        List<PriceEntity> prices = priceService.getPricesByProductCode("product_sao_04", "master_catalog");
        assertUuidListsEquals(ALL_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProduct...
     */
    @Test
    public void getPricesByProductTest4() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<PriceEntity> prices = priceService.getPricesByProductCode("product_sao_04", catalog);
        assertUuidListsEquals(ALL_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProductAndUnit...
     */
    @Test
    public void getPricesByProductAndUnitTest() {
        List<PriceEntity> prices = priceService.getPricesByProductUuidAndUnitUuid("a1f1016c-fa94-11e6-b704-cb129d9d0314", "a1e2ae50-fa94-11e6-b6f6-67b357732118");
        assertUuidListsEquals(PIECES_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProductAndUnit...
     */
    @Test
    public void getPricesByProductAndUnitTest2() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_04", "master_catalog", ProductEntity.class);
        UnitEntity unit = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("pieces", "master_catalog", UnitEntity.class);
        List<PriceEntity> prices = priceService.getPricesByProductAndUnit(product, unit);
        assertUuidListsEquals(PIECES_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProductAndUnit...
     */
    @Test
    public void getPricesByProductAndUnitTest3() {
        List<PriceEntity> prices = priceService.getPricesByProductCodeAndUnitCode("product_sao_04", "pieces", "master_catalog");
        assertUuidListsEquals(PIECES_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProductAndUnit...
     */
    @Test
    public void getPricesByProductAndUnitTest4() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<PriceEntity> prices = priceService.getPricesByProductCodeAndUnitCode("product_sao_04", "pieces", catalog);
        assertUuidListsEquals(PIECES_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProductAndUnitAndCurrency
     */
    @Test
    public void getPricesByProductAndUnitAndCurrencyTest() {
        List<PriceEntity> prices = priceService.getPricesByProductUuidAndUnitUuidAndCurrencyUuid("a1f1016c-fa94-11e6-b704-cb129d9d0314",
                "a1e2ae50-fa94-11e6-b6f6-67b357732118", "4a9b636e-f065-11e6-1123-0000def2f3a6");
        assertUuidListsEquals(PIECES_RUB_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProductAndUnitAndCurrency
     */
    @Test
    public void getPricesByProductAndUnitAndCurrencyTest2() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_04", "master_catalog", ProductEntity.class);
        UnitEntity unit = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("pieces", "master_catalog", UnitEntity.class);
        CurrencyEntity currency = uniqueCodeEntityService.getEntityByCode("rub", CurrencyEntity.class);
        List<PriceEntity> prices = priceService.getPricesByProductAndUnitAndCurrency(product, unit, currency);
        assertUuidListsEquals(PIECES_RUB_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProductAndUnitAndCurrency
     */
    @Test
    public void getPricesByProductAndUnitAndCurrencyTest3() {
        List<PriceEntity> prices = priceService.getPricesByProductCodeAndUnitCodeAndCurrencyCode("product_sao_04", "pieces",
                "rub", "master_catalog");
        assertUuidListsEquals(PIECES_RUB_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProductAndUnitAndCurrency
     */
    @Test
    public void getPricesByProductAndUnitAndCurrencyTest4() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<PriceEntity> prices = priceService.getPricesByProductCodeAndUnitCodeAndCurrencyCode("product_sao_04", "pieces", "rub", catalog);
        assertUuidListsEquals(PIECES_RUB_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProductAndCurrency...
     */
    @Test
    public void getPricesByProductAndCurrencyTest() {
        List<PriceEntity> prices = priceService.getPricesByProductUuidAndCurrencyUuid("a1f1016c-fa94-11e6-b704-cb129d9d0314",
                "4a9b636e-2222-11e6-7584-0000def2fa9b");
        assertUuidListsEquals(USD_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProductAndCurrency...
     */
    @Test
    public void getPricesByProductAndCurrencyTest2() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_04", "master_catalog", ProductEntity.class);
        CurrencyEntity currency = uniqueCodeEntityService.getEntityByCode("usd", CurrencyEntity.class);
        List<PriceEntity> prices = priceService.getPricesByProductAndCurrency(product, currency);
        assertUuidListsEquals(USD_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProductAndCurrency...
     */
    @Test
    public void getPricesByProductAndCurrencyTest3() {
        List<PriceEntity> prices = priceService.getPricesByProductCodeAndCurrencyCode("product_sao_04", "usd", "master_catalog");
        assertUuidListsEquals(USD_PRICES_UUIDS_LIST, getUuids(prices));
    }

    /**
     * Method test - priceService.getPricesByProductAndCurrency...
     */
    @Test
    public void getPricesByProductAndCurrencyTest4() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<PriceEntity> prices = priceService.getPricesByProductCodeAndCurrencyCode("product_sao_04", "usd", catalog);
        assertUuidListsEquals(USD_PRICES_UUIDS_LIST, getUuids(prices));
    }


}
