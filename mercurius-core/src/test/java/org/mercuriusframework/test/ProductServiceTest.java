package org.mercuriusframework.test;

import org.junit.Test;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.services.ProductService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.services.query.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Product service test
 */
public class ProductServiceTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> ALL_PRODUCTS_UUIDS_LIST = Arrays.asList(
            "a1f05e10-fa94-11e6-b6ff-bf2400ed613a", "a1f0970e-fa94-11e6-b700-c3c4039ff350",
            "a1f0b496-fa94-11e6-b701-b31dc35d1050", "a1f1016c-fa94-11e6-b704-cb129d9d0314"
    );

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Product service
     */
    @Autowired
    private ProductService productService;

    /**
     * Method test - productService.getAllProductByCategory
     */
    @Test
    public void getAllProductByCategoryTest() {
        PageableResult<ProductEntity> result = productService.getAllProductByCategoryUuid("4aa6bb9c-f065-11e6-9dad-9b2db2c47a0f", 0, 20);
        assertUuidListsEquals(ALL_PRODUCTS_UUIDS_LIST, getUuids(result.getEntries()));
    }

    /**
     * Method test (second) - productService.getAllProductByCategory
     */
    @Test
    public void getAllProductByCategoryTest2() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        PageableResult<ProductEntity> result = productService.getAllProductByCategory("main_category", catalog, 0, 20);
        assertUuidListsEquals(ALL_PRODUCTS_UUIDS_LIST, getUuids(result.getEntries()));
    }

    /**
     * Method test (third) - productService.getAllProductByCategory
     */
    @Test
    public void getAllProductByCategoryTest3() {
        PageableResult<ProductEntity> result = productService.getAllProductByCategory("main_category", "master_catalog", 0, 20);
        assertUuidListsEquals(ALL_PRODUCTS_UUIDS_LIST, getUuids(result.getEntries()));
    }
}
