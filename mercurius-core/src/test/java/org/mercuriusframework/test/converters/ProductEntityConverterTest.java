package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.ProductEntityConverter;
import org.mercuriusframework.dto.ProductEntityDto;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.enums.ProductLoadOptions;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

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
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_04", "master_catalog",
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
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_04", "master_catalog",
                ProductEntity.class, ProductEntity.DESCRIPTION, ProductEntity.MAIN_CATEGORY);
        ProductEntityDto productDto = productEntityConverter.convert(product, ProductLoadOptions.DESCRIPTION, ProductLoadOptions.BREAD_CRUMBS);
        assertEquals(productDto.getCode().equals("product_sao_04") && productDto.getName().equals("Sword Art Online vol. 04") &&
                productDto.getShortName().equals("Reki Kawahara") &&
                productDto.getUuid().equals("a1f1016c-fa94-11e6-b704-cb129d9d0314")
                , true);
        assertEquals(productDto.getDescription().equals("Test description - product"), true);
        assertOrderedUuidListsEquals(ORDERED_BREAD_CRUMBS_UUIDS_LIST, getUuidsFromDtos(productDto.getBreadCrumbs()));
    }
}
