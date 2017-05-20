package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.entities.FacetEntity;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.FacetService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Facet service test
 */
public class FacetServiceTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> CATEGORY_FACETS_UUIDS_LIST = Arrays.asList(
            "1111b636e-f065-11e6-5322-836adef2f3a", "1111b636e-f065-11e6-5322-836adef2234"
    );

    private static final List<String> ALL_CATEGORIES_FACETS_UUIDS_LIST = Arrays.asList(
            "1111b636e-f065-11e6-5322-836adef2234"
    );

    /**
     * Facet service
     */
    @Autowired
    private FacetService facetService;

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
     * Method test - facetService.getFacetsFolAllCategories
     */
    @Test
    public void getFacetsFolAllCategoriesTest() {
        List<FacetEntity> facets = facetService.getFacetsFolAllCategories();
        assertUuidListsEquals(getUuids(facets), ALL_CATEGORIES_FACETS_UUIDS_LIST);
    }

    /**
     * Method test - facetService.getFacetsByCategory
     */
    @Test
    public void getFacetsByCategoryTest() {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(
                "fantasy_manga", "master_catalog", CategoryEntity.class);
        List<FacetEntity> facets = facetService.getFacetsByCategory(category);
        assertUuidListsEquals(getUuids(facets), CATEGORY_FACETS_UUIDS_LIST);
    }

    /**
     * Method test - facetService.getFacetsByCategory
     */
    @Test
    public void getFacetsByCategoryTest2() {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(
                "fantasy_manga", "master_catalog", CategoryEntity.class);
        List<FacetEntity> facets = facetService.getFacetsByCategoryUuid(category.getUuid());
        assertUuidListsEquals(getUuids(facets), CATEGORY_FACETS_UUIDS_LIST);
    }

    /**
     * Method test - facetService.getFacetsByCategory
     */
    @Test
    public void getFacetsByCategoryTest3() {
        List<FacetEntity> facets = facetService.getFacetsByCategoryCode("fantasy_manga", "master_catalog");
        assertUuidListsEquals(getUuids(facets), CATEGORY_FACETS_UUIDS_LIST);
    }

    /**
     * Method test - facetService.getFacetsByCategory
     */
    @Test
    public void getFacetsByCategoryTest4() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<FacetEntity> facets = facetService.getFacetsByCategoryCode("fantasy_manga", catalog);
        assertUuidListsEquals(getUuids(facets), CATEGORY_FACETS_UUIDS_LIST);
    }

}
