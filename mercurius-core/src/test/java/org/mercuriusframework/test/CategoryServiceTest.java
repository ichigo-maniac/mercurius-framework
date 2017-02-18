package org.mercuriusframework.test;

import org.junit.Test;
import org.mercuriusframework.entities.Catalog;
import org.mercuriusframework.entities.Category;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.CategoryService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Category service test
 */
public class CategoryServiceTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> SUB_CATEGORIES_UUIDS_LIST = Arrays.asList(
        "4aa6e108-f065-11e6-9dae-774f2dfc5358", "4aa6fb20-f065-11e6-9daf-a334a56d0d4c"
    );
    private static final List<String> ORDERED_BREAD_CRUMBS_UUIDS_LIST = Arrays.asList(
        "4aa6bb9c-f065-11e6-9dad-9b2db2c47a0f", "4aa6fb20-f065-11e6-9daf-a334a56d0d4c", "1116fb20-f065-11e6-9daf-a334a56d0111"
    );

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
     * Category service
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Method test - categoryService.getAllCategoriesWithoutMainSuperCategory
     */
    @Test
    public void getAllCategoriesWithoutMainSuperCategoryTest() {
        List<Category> categories = categoryService.getAllCategoriesWithoutMainSuperCategory("master_catalog");
        assertEquals(categories.size() == 1 && categories.get(0).getUuid().equals("4aa6bb9c-f065-11e6-9dad-9b2db2c47a0f"), true);
    }

    /**
     * Method test (second)- categoryService.getAllCategoriesWithoutMainSuperCategory
     */
    @Test
    public void getAllCategoriesWithoutMainSuperCategoryTest2() {
        Catalog catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", Catalog.class);
        List<Category> categories = categoryService.getAllCategoriesWithoutMainSuperCategory(catalog);
        assertEquals(categories.size() == 1 && categories.get(0).getUuid().equals("4aa6bb9c-f065-11e6-9dad-9b2db2c47a0f"), true);
    }

    /**
     * Method test - categoryService.getBreadCrumbs
     */
    @Test
    public void getBreadCrumbsTest() {
        Category category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("spokon_manga", "master_catalog", Category.class);
        List<Category> breadCrumbs = categoryService.getBreadCrumbs(category);
        assertOrderedUuidListsEquals(getUuids(breadCrumbs), ORDERED_BREAD_CRUMBS_UUIDS_LIST);
    }

    /**
     * Method test (second) - categoryService.getBreadCrumbs
     */
    @Test
    public void getBreadCrumbsTest2() {
        List<Category> breadCrumbs = categoryService.getBreadCrumbs("1116fb20-f065-11e6-9daf-a334a56d0111");
        assertOrderedUuidListsEquals(getUuids(breadCrumbs), ORDERED_BREAD_CRUMBS_UUIDS_LIST);
    }

    /**
     * Method test (third) - categoryService.getBreadCrumbs
     */
    @Test
    public void getBreadCrumbsTest3() {
        List<Category> breadCrumbs = categoryService.getBreadCrumbs("spokon_manga", "master_catalog");
        assertOrderedUuidListsEquals(getUuids(breadCrumbs), ORDERED_BREAD_CRUMBS_UUIDS_LIST);
    }

    /**
     * Method test (fourth) - categoryService.getBreadCrumbs
     */
    @Test
    public void getBreadCrumbsTest4() {
        Catalog catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", Catalog.class);
        List<Category> breadCrumbs = categoryService.getBreadCrumbs("spokon_manga", catalog);
        assertOrderedUuidListsEquals(getUuids(breadCrumbs), ORDERED_BREAD_CRUMBS_UUIDS_LIST);
    }


    /**
     * Method test - categoryService.getSubCategories
     */
    @Test
    public void getSubCategoriesTest() {
        Catalog catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", Catalog.class);
        List<Category> categories = categoryService.getSubCategories("main_category", catalog);
        assertUuidListsEquals(SUB_CATEGORIES_UUIDS_LIST, getUuids(categories));
    }

    /**
     * Method test (second) - categoryService.getSubCategories
     */
    @Test
    public void getSubCategoriesTest2() {
        List<Category> categories = categoryService.getSubCategories("main_category", "master_catalog");
        assertUuidListsEquals(SUB_CATEGORIES_UUIDS_LIST, getUuids(categories));assertUuidListsEquals(SUB_CATEGORIES_UUIDS_LIST, getUuids(categories));
    }

    /**
     * Method test (third) - categoryService.getSubCategories
     */
    @Test
    public void getSubCategoriesTest3() {
        List<Category> categories = categoryService.getSubCategories("4aa6bb9c-f065-11e6-9dad-9b2db2c47a0f");
        assertUuidListsEquals(SUB_CATEGORIES_UUIDS_LIST, getUuids(categories));
    }

}