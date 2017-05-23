package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.CategoryEntityConverter;
import org.mercuriusframework.dto.CategoryEntityDto;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.enums.CategoryLoadOptions;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Category entity converter test
 */
public class CategoryEntityConverterTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> ORDERED_BREAD_CRUMBS_UUIDS_LIST = Arrays.asList(
            "4aa6bb9c-f065-11e6-9dad-9b2db2c47a0f", "4aa6fb20-f065-11e6-9daf-a334a56d0d4c", "1116fb20-f065-11e6-9daf-a334a56d2222"
    );

    /**
     * Category entity converter
     */
    @Autowired
    private CategoryEntityConverter categoryEntityConverter;

    /**
     * Catalog unique code entity service
     */
    @Autowired
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Method test - categoryEntityConverter.convert
     */
    @Test
    public void convertTest() {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("fantasy_manga", "master_catalog", CategoryEntity.class);
        CategoryEntityDto categoryDto = categoryEntityConverter.convert(category);
        assertEquals(categoryDto.getCode().equals("fantasy_manga") && categoryDto.getName().equals("Fantasy manga") &&
                categoryDto.getUuid().equals("1116fb20-f065-11e6-9daf-a334a56d2222") && categoryDto.getPriority() == 5 &&
                categoryDto.getBuiltUrl().equals("/main_category/manga/fantasy_manga"), true
        );

    }

    /**
     * Method test (second) - categoryEntityConverter.convert
     */
    @Test
    public void convertTest2() {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("fantasy_manga", "master_catalog",
                CategoryEntity.class, CategoryEntity.DESCRIPTION);
        CategoryEntityDto categoryDto = categoryEntityConverter.convert(category, CategoryLoadOptions.DESCRIPTION);
        assertEquals(categoryDto.getCode().equals("fantasy_manga") && categoryDto.getName().equals("Fantasy manga") &&
                        categoryDto.getUuid().equals("1116fb20-f065-11e6-9daf-a334a56d2222") && categoryDto.getPriority() == 5 &&
                        categoryDto.getBuiltUrl().equals("/main_category/manga/fantasy_manga"), true
        );
        assertEquals(categoryDto.getDescription().equals("Test description - category"), true);
    }

    /**
     * Method test (third) - categoryEntityConverter.convert
     */
    @Test
    public void convertTest3() {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("fantasy_manga", "master_catalog",
                CategoryEntity.class, CategoryEntity.DESCRIPTION);
        CategoryEntityDto categoryDto = categoryEntityConverter.convert(category, CategoryLoadOptions.DESCRIPTION, CategoryLoadOptions.BREAD_CRUMBS);
        assertEquals(categoryDto.getCode().equals("fantasy_manga") && categoryDto.getName().equals("Fantasy manga") &&
                        categoryDto.getUuid().equals("1116fb20-f065-11e6-9daf-a334a56d2222") && categoryDto.getPriority() == 5 &&
                        categoryDto.getBuiltUrl().equals("/main_category/manga/fantasy_manga"), true
        );
        assertEquals(categoryDto.getDescription().equals("Test description - category"), true);
        assertOrderedUuidListsEquals(ORDERED_BREAD_CRUMBS_UUIDS_LIST, getUuidsFromDtos(categoryDto.getBreadCrumbs()));
    }
}
