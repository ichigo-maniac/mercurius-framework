package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.ProductEntityConverter;
import org.mercuriusframework.dto.ProductEntityDto;
import org.mercuriusframework.dto.StockTotalDto;
import org.mercuriusframework.dto.UnitEntityDto;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.enums.ProductLoadOptions;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Product entity converter test
 */
public class ProductEntityConverterTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> ORDERED_BREAD_CRUMBS_UUIDS_LIST = Arrays.asList(
            "4aa6bb9c-f065-11e6-9dad-9b2db2c47a0f", "4aa6fb20-f065-11e6-9daf-a334a56d0d4c", "1116fb20-f065-11e6-9daf-a334a56d2222"
    );

    private static final List<String> ENABLED_STOCKS_UUIDS_LIST = Arrays.asList(
            "12345e10-1a94-22e6-b6ff-abd422223333", "12345e10-1594-22e6-b6ff-abd422555333",
            "12345e10-0000-22e6-4444-abd422555333"
    );

    private static final List<String> PIECES_STOCKS_UUIDS_LIST = Arrays.asList(
            "12345e10-1a94-22e6-b6ff-abd422223333"
    );

    private static final List<String> BOXES_STOCKS_UUIDS_LIST = Arrays.asList(
            "12345e10-1594-22e6-b6ff-abd422555333", "12345e10-0000-22e6-4444-abd422555333"
    );

    private static final List<String> FEATURE_VALUES_UUIDS_LIST = Arrays.asList(
            "12345e10-1a94-22e6-0000-abd000223330", "12345e10-1a94-22e6-0000-777000223337",
            "00045e10-1a94-22e6-0000-abd000223111", "11115e0-1a94-22e6-0000-ccbd000223111",
            "11115e0-1a94-22e6-53a5-ccbd000223111"
    );


    /**
     * Product entity converter
     */
    @Autowired
    private ProductEntityConverter productEntityConverter;

    /**
     * Catalog unique code entity service
     */
    @Autowired
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Method test - productEntityConverter.convert
     */
    @Test
    public void convertTest() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_04", "master_catalog", ProductEntity.class);
        ProductEntityDto productDto = productEntityConverter.convert(product);
        assertEquals(productDto.getCode().equals("product_sao_04") && productDto.getName().equals("Sword Art Online vol. 04") &&
                productDto.getShortName().equals("Reki Kawahara") &&
                productDto.getUuid().equals("a1f1016c-fa94-11e6-b704-cb129d9d0314")
                , true);
    }

    /**
     * Method test - productEntityConverter.convert
     */
    @Test
    public void convertTest2() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("product_sao_04", "master_catalog",
                ProductEntity.class, ProductEntity.DESCRIPTION);
        ProductEntityDto productDto = productEntityConverter.convert(product, ProductLoadOptions.DESCRIPTION);
        assertEquals(productDto.getCode().equals("product_sao_04") && productDto.getName().equals("Sword Art Online vol. 04") &&
                productDto.getShortName().equals("Reki Kawahara") &&
                productDto.getUuid().equals("a1f1016c-fa94-11e6-b704-cb129d9d0314")
                , true);
        assertEquals(productDto.getDescription().equals("Test description - product"), true);
    }

    /**
     * Method test - productEntityConverter.convert
     */
    @Test
    public void convertTest3() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("product_sao_04", "master_catalog",
                ProductEntity.class, ProductEntity.DESCRIPTION, ProductEntity.MAIN_CATEGORY);
        ProductEntityDto productDto = productEntityConverter.convert(product, ProductLoadOptions.DESCRIPTION, ProductLoadOptions.BREAD_CRUMBS);
        assertEquals(productDto.getCode().equals("product_sao_04") && productDto.getName().equals("Sword Art Online vol. 04") &&
                productDto.getShortName().equals("Reki Kawahara") &&
                productDto.getUuid().equals("a1f1016c-fa94-11e6-b704-cb129d9d0314")
                , true);
        assertEquals(productDto.getDescription().equals("Test description - product"), true);
        assertOrderedUuidListsEquals(ORDERED_BREAD_CRUMBS_UUIDS_LIST, getUuidsFromDtos(productDto.getBreadCrumbs()));
    }

    /**
     * Method test - productEntityConverter.convert
     */
    @Test
    public void convertTest4() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("product_prince_of_tennis_01", "master_catalog",
                ProductEntity.class, ProductEntity.DESCRIPTION, ProductEntity.MAIN_CATEGORY);
        ProductEntityDto productDto = productEntityConverter.convert(product, ProductLoadOptions.ALL_STOCKS);
        assertUuidListsEquals(ENABLED_STOCKS_UUIDS_LIST, getUuidsFromDtos(productDto.getStocks()));
        for (Map.Entry<UnitEntityDto, StockTotalDto> entry : productDto.getStocksMap().entrySet()) {
            if (entry.getKey().getCode().equals("pieces")) {
                assertUuidListsEquals(PIECES_STOCKS_UUIDS_LIST, getUuidsFromDtos(entry.getValue().getStocks()));
                assertEquals(entry.getValue().getTotalProductsCount() ==  15l &&
                        entry.getValue().getAvailableProductsCount() ==  15l, true);
            }
            if (entry.getKey().getCode().equals("boxes")) {
                assertUuidListsEquals(BOXES_STOCKS_UUIDS_LIST, getUuidsFromDtos(entry.getValue().getStocks()));
                assertEquals(entry.getValue().getTotalProductsCount() ==  8l &&
                        entry.getValue().getAvailableProductsCount() ==  4l, true);
            }
        }
    }

    /**
     * Method test - productEntityConverter.convert
     */
    @Test
    public void convertTest5() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("product_sao_01", "master_catalog",
                ProductEntity.class, ProductEntity.FEATURE_VALUES);
        ProductEntityDto productDto = productEntityConverter.convert(product, ProductLoadOptions.FEATURE_VALUES);
        assertEquals(productDto.getCode().equals("product_sao_01") && productDto.getName().equals("Sword Art Online vol. 01") &&
                        productDto.getShortName() == null &&
                        productDto.getUuid().equals("a1f05e10-fa94-11e6-b6ff-bf2400ed613a")
                , true);

        assertOrderedUuidListsEquals(FEATURE_VALUES_UUIDS_LIST, getUuidsFromDtos(productDto.getFeatureValues()));
    }
}
