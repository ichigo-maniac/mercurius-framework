package org.mercuriusframework.test;

import org.junit.Test;
import org.mercuriusframework.entities.Catalog;
import org.mercuriusframework.entities.Category;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Catalog unique code entity service test
 */
public class CatalogUniqueCodeEntityServiceTest extends AbstractTest {

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
     * Method test - catalogUniqueCodeEntityService.getEntityByCodeAndCatalog
     */
    @Test
    public void getEntityByCodeAndCatalogTest() {
        Catalog catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", Catalog.class);
        Category category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalog("manga", catalog, Category.class, "mainSuperCategory");
        assertEquals(
                category.getUuid().equals("4aa6fb20-f065-11e6-9daf-a334a56d0d4c")
                && category.getMainSuperCategory().getUuid().equals("4aa6bb9c-f065-11e6-9dad-9b2db2c47a0f"), true);
    }

    /**
     * Method test - catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode
     */
    @Test
    public void getEntityByCodeAndCatalogCodeTest() {
        Category category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("bluray", "master_catalog", Category.class, "mainSuperCategory");
        assertEquals(
                category.getUuid().equals("4aa6e108-f065-11e6-9dae-774f2dfc5358")
                        && category.getMainSuperCategory().getUuid().equals("4aa6bb9c-f065-11e6-9dad-9b2db2c47a0f"), true);
    }

}
