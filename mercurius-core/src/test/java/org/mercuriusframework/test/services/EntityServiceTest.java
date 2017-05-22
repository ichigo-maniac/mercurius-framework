package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.converters.impl.CategoryEntityConverter;
import org.mercuriusframework.dto.CategoryEntityDto;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.entities.FeatureEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.enums.CriteriaValueType;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.services.query.CriteriaParameter;
import org.mercuriusframework.services.query.CriteriaValue;
import org.mercuriusframework.services.query.PageableResult;
import org.mercuriusframework.services.query.QueryParameter;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Entity service test
 */
public class EntityServiceTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> PRODUCTS_UUIDS_LIST = Arrays.asList(
            "a1f05e10-fa94-11e6-b6ff-bf2400ed613a", "a1f0970e-fa94-11e6-b700-c3c4039ff350"
    );

    private static final List<String> CATEGORIES_UUIDS_LIST = Arrays.asList(
            "4aa6e108-f065-11e6-9dae-774f2dfc5358", "4aa6fb20-f065-11e6-9daf-a334a56d0d4c"
    );

    /**
     * Entity service
     */
    @Autowired
    private EntityService entityService;

    /**
     * Catalog unique code entity service
     */
    @Autowired
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Category entity converter
     */
    @Autowired
    private CategoryEntityConverter categoryEntityConverter;

    /**
     * Unique code service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - entityService.findByUuid
     */
    @Test
    public void findByUuidTest() {
        FeatureEntity feature = entityService.findByUuid("55501250-1111-5311-2252-bf2400ed613a", FeatureEntity.class,
                FeatureEntity.DICTIONARY_TYPE);
        assertEquals(feature.getCode().equals("country") && feature.getDictionaryType().getCode().equals("dictionary_country"), true);
    }

    /**
     * Method test - entityService.findByUuids
     */
    @Test
    public void findByUuidsTest() {
        List<String> uuids = new ArrayList<>(2);
        uuids.add("a1f05e10-fa94-11e6-b6ff-bf2400ed613a");
        uuids.add("a1f0970e-fa94-11e6-b700-c3c4039ff350");
        List<ProductEntity> products = entityService.findByUuids(uuids, ProductEntity.class, ProductEntity.MAIN_UNIT);
        assertEquals(products.get(0).getMainUnit().getCode().equals("pieces"), true);
        assertUuidListsEquals(PRODUCTS_UUIDS_LIST, getUuids(products));
    }

    /**
     * Method test - entityService.getListResultByQuery
     */
    @Test
    public void getListResultByQueryTest() {
        List<CategoryEntity> categories = entityService.getListResultByQuery("SELECT category FROM " + CategoryEntity.ENTITY_NAME + " as category " +
                        "WHERE category." + CategoryEntity.MAIN_SUPER_CATEGORY + "." + CategoryEntity.CODE + " = :categoryCode",
                CategoryEntity.class, new QueryParameter("categoryCode", "main_category"));
        assertUuidListsEquals(CATEGORIES_UUIDS_LIST, getUuids(categories));
    }

    /**
     * Method test - entityService.getSingleResultByQuery
     */
    @Test
    public void getSingleResultByQueryTest() {
        CategoryEntity category = entityService.getSingleResultByQuery("SELECT category FROM " + CategoryEntity.ENTITY_NAME + " as category " +
                        "WHERE category." + CategoryEntity.MAIN_SUPER_CATEGORY + "." + CategoryEntity.CODE + " = :superCategoryCode " +
                        "AND category." + CategoryEntity.CODE + " = :categoryCode",
                CategoryEntity.class,
                new QueryParameter("superCategoryCode", "main_category"), new QueryParameter("categoryCode", "manga"));
        assertEquals("4aa6fb20-f065-11e6-9daf-a334a56d0d4c".equals(category.getUuid()), true);
    }

    /**
     * Method test - entityService.getPageableResultByQueries
     */
    @Test
    public void getPageableResultByQueriesTest() {
        PageableResult<CategoryEntityDto> pageableResult = entityService.getPageableResultByQueries(
                categoryEntityConverter, new LoadOptions[]{},
                "SELECT category FROM " + CategoryEntity.ENTITY_NAME + " as category " +
                        "WHERE category." + CategoryEntity.MAIN_SUPER_CATEGORY + "." + CategoryEntity.CODE + " = :categoryCode",
                "SELECT count(category) FROM " + CategoryEntity.ENTITY_NAME + " as category " +
                        "WHERE category." + CategoryEntity.MAIN_SUPER_CATEGORY + "." + CategoryEntity.CODE + " = :categoryCode",
                0, 20,
                CategoryEntity.class, new QueryParameter("categoryCode", "main_category"));
        assertEquals(pageableResult.getPagesCount(), new Integer(1));
        assertEquals(pageableResult.getTotalEntriesCount(), new Integer(2));
        assertUuidListsEquals(CATEGORIES_UUIDS_LIST, getUuidsFromDtos(pageableResult.getEntries()));
    }

    /**
     * Method test - entityService.getPageableResultByQueries
     */
    @Test
    public void getPageableResultByQueriesTest2() {
        PageableResult<CategoryEntity> pageableResult = entityService.getPageableResultByQueries("SELECT category FROM " + CategoryEntity.ENTITY_NAME + " as category " +
                        "WHERE category." + CategoryEntity.MAIN_SUPER_CATEGORY + "." + CategoryEntity.CODE + " = :categoryCode",
                "SELECT count(category) FROM " + CategoryEntity.ENTITY_NAME + " as category " +
                        "WHERE category." + CategoryEntity.MAIN_SUPER_CATEGORY + "." + CategoryEntity.CODE + " = :categoryCode",
                0, 20,
                CategoryEntity.class, new QueryParameter("categoryCode", "main_category"));
        assertEquals(pageableResult.getPagesCount(), new Integer(1));
        assertEquals(pageableResult.getTotalEntriesCount(), new Integer(2));
        assertUuidListsEquals(CATEGORIES_UUIDS_LIST, getUuids(pageableResult.getEntries()));
    }

    /**
     * Method test - entityService.getListResultByCriteria
     */
    @Test
    public void getListResultByCriteriaTest() {
        CategoryEntity mainCategory = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("main_category", "master_catalog", CategoryEntity.class);
        List<CategoryEntity> categories = entityService.getListResultByCriteria(CategoryEntity.class, new String[]{CategoryEntity.MAIN_SUPER_CATEGORY},
                new CriteriaParameter(CategoryEntity.MAIN_SUPER_CATEGORY, new CriteriaValue(CriteriaValueType.EQUAL, mainCategory)));
        assertUuidListsEquals(CATEGORIES_UUIDS_LIST, getUuids(categories));
    }

    /**
     * Method test - entityService.getCountByCriteria
     */
    @Test
    public void getCountByCriteriaTest() {
        CategoryEntity mainCategory = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("main_category", "master_catalog", CategoryEntity.class);
        Long count = entityService.getCountByCriteria(CategoryEntity.class,
                new CriteriaParameter(CategoryEntity.MAIN_SUPER_CATEGORY, new CriteriaValue(CriteriaValueType.EQUAL, mainCategory)));
        assertEquals(CATEGORIES_UUIDS_LIST.size(), count.intValue());
    }

    /**
     * Method test - entityService.getPageableResultByCriteria
     */
    @Test
    public void getPageableResultByCriteriaTest() {
        CategoryEntity mainCategory = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("main_category", "master_catalog", CategoryEntity.class);
        PageableResult<CategoryEntity> pageableResult = entityService.getPageableResultByCriteria(0, 20, new String[]{CategoryEntity.PRODUCTS},
                CategoryEntity.class, new CriteriaParameter(CategoryEntity.MAIN_SUPER_CATEGORY, new CriteriaValue(CriteriaValueType.EQUAL, mainCategory)));
        assertEquals(pageableResult.getPagesCount(), new Integer(1));
        assertEquals(pageableResult.getTotalEntriesCount(), new Integer(2));
        assertUuidListsEquals(CATEGORIES_UUIDS_LIST, getUuids(pageableResult.getEntries()));
    }

    /**
     * Method test - entityService.getPageableResultByCriteria
     */
    @Test
    public void getPageableResultByCriteriaTest2() {
        CategoryEntity mainCategory = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("main_category", "master_catalog", CategoryEntity.class);
        PageableResult<CategoryEntityDto> pageableResult = entityService.getPageableResultByCriteria(
                categoryEntityConverter, new LoadOptions[]{},
                0, 20, new String[]{CategoryEntity.PRODUCTS},
                CategoryEntity.class, new CriteriaParameter(CategoryEntity.MAIN_SUPER_CATEGORY, new CriteriaValue(CriteriaValueType.EQUAL, mainCategory)));
        assertEquals(pageableResult.getPagesCount(), new Integer(1));
        assertEquals(pageableResult.getTotalEntriesCount(), new Integer(2));
        assertUuidListsEquals(CATEGORIES_UUIDS_LIST, getUuidsFromDtos(pageableResult.getEntries()));
    }

    /**
     * Method test - entityService.save
     */
    @Test
    public void saveTest() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCatalog(uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class));
        entityService.save(productEntity);
        CategoryEntity mainCategory = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("main_category", "master_catalog", CategoryEntity.class);
        productEntity.setName("test");
        productEntity.setMainCategory(mainCategory);
        productEntity.setCategories(new HashSet<>(Arrays.asList(mainCategory)));
        entityService.save(productEntity);
    }

    /**
     * Method test - entityService.delete
     */
    @Test
    public void deleteTest() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCatalog(uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class));
        productEntity.setName("test2");
        CategoryEntity mainCategory = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("main_category", "master_catalog", CategoryEntity.class);
        productEntity.setMainCategory(mainCategory);
        productEntity.setCategories(new HashSet<>(Arrays.asList(mainCategory)));
        entityService.save(productEntity);
        entityService.delete(productEntity);
    }

    /**
     * Method test - entityService.delete
     */
    @Test
    public void deleteTest2() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCatalog(uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class));
        productEntity.setName("test3");
        CategoryEntity mainCategory = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("main_category", "master_catalog", CategoryEntity.class);
        productEntity.setMainCategory(mainCategory);
        productEntity.setCategories(new HashSet<>(Arrays.asList(mainCategory)));
        entityService.save(productEntity);
        entityService.delete(productEntity.getUuid(), ProductEntity.class);
    }

}
