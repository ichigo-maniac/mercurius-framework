package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.entities.UnitEntity;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.services.UnitService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Unit service test
 */
public class UnitServiceTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> ALL_UNITS_UUIDS_LIST = Arrays.asList(
            "a1e2ae50-fa94-11e6-b6f6-67b357732118", "55e2ae50-fa94-11e6-abcd-67b357732118",
            "6662ae50-fa94-6666-b6f6-67b35773266"
    );
    private static final List<String> UNITS_UUIDS_LIST = Arrays.asList(
            "a1e2ae50-fa94-11e6-b6f6-67b357732118", "6662ae50-fa94-6666-b6f6-67b35773266"
    );

    /**
     * Unit service
     */
    @Autowired
    private UnitService unitService;

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
     * Method test - unitService.getAllUnits
     */
    @Test
    public void getAllUnitsTest() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<UnitEntity> units = unitService.getAllUnits(catalog);
        assertUuidListsEquals(ALL_UNITS_UUIDS_LIST, getUuids(units));
    }

    /**
     * Method test (second) - unitService.getAllUnits
     */
    @Test
    public void getAllUnitsTest2() {
        List<UnitEntity> units = unitService.getAllUnits("master_catalog");
        assertUuidListsEquals(ALL_UNITS_UUIDS_LIST, getUuids(units));
    }

    /**
     * Method test - unitService.getUnitsByProduct...
     */
    @Test
    public void getUnitsByProductTest() {
        List<UnitEntity> units = unitService.getUnitsByProductUuid("a1f0b496-fa94-11e6-b701-b31dc35d6666");
        assertUuidListsEquals(UNITS_UUIDS_LIST, getUuids(units));
    }

    /**
     * Method test (second) - unitService.getUnitsByProduct...
     */
    @Test
    public void getUnitsByProductTest2() {
        ProductEntity product = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_prince_of_tennis_01", "master_catalog", ProductEntity.class);
        List<UnitEntity> units = unitService.getUnitsByProduct(product);
        assertUuidListsEquals(UNITS_UUIDS_LIST, getUuids(units));
    }

    /**
     * Method test (third) - unitService.getUnitsByProduct...
     */
    @Test
    public void getUnitsByProductTest3() {
        List<UnitEntity> units = unitService.getUnitsByProductCode("product_prince_of_tennis_01", "master_catalog");
        assertUuidListsEquals(UNITS_UUIDS_LIST, getUuids(units));
    }

    /**
     * Method test (fourth) - unitService.getUnitsByProduct...
     */
    @Test
    public void getUnitsByProductTest4() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        List<UnitEntity> units = unitService.getUnitsByProductCode("product_prince_of_tennis_01", catalog);
        assertUuidListsEquals(UNITS_UUIDS_LIST, getUuids(units));
    }
}
