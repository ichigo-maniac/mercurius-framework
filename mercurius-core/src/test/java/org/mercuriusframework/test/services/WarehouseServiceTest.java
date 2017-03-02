package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.entities.StoreEntity;
import org.mercuriusframework.entities.WarehouseEntity;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.services.WarehouseService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Warehouse service test
 */
public class WarehouseServiceTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> ALL_WAREHOUSES_UUIDS_LIST = Arrays.asList(
            "12345e10-fa94-22e6-b6ff-abd400ed1234", "12345e10-fa94-22e6-b6ff-abd400ed2222",
            "12345e10-fa94-22e6-b6ff-abd400ed3333"
    );
    private static final List<String> ENABLED_WAREHOUSES_UUIDS_LIST = Arrays.asList(
            "12345e10-fa94-22e6-b6ff-abd400ed1234", "12345e10-fa94-22e6-b6ff-abd400ed2222"
    );

    /**
     * Warehouse service
     */
    @Autowired
    private WarehouseService warehouseService;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - warehouseService.getWarehousesBy...
     */
    @Test
    public void getWarehousesByTest() {
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreUuid("12345e10-fa94-11e6-b6ff-bf2400ed1234");
        assertUuidListsEquals(ALL_WAREHOUSES_UUIDS_LIST, getUuids(warehouses));
    }

    /**
     * Method test (second) - warehouseService.getWarehousesBy...
     */
    @Test
    public void getWarehousesByTest2() {
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreUuid("12345e10-fa94-11e6-b6ff-bf2400ed1234", false);
        assertUuidListsEquals(ENABLED_WAREHOUSES_UUIDS_LIST, getUuids(warehouses));
    }

    /**
     * Method test (third) - warehouseService.getWarehousesBy...
     */
    @Test
    public void getWarehousesByTest3() {
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreCode("default_store");
        assertUuidListsEquals(ALL_WAREHOUSES_UUIDS_LIST, getUuids(warehouses));
    }

    /**
     * Method test (fourth) - warehouseService.getWarehousesBy...
     */
    @Test
    public void getWarehousesByTest4() {
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreCode("default_store", false);
        assertUuidListsEquals(ENABLED_WAREHOUSES_UUIDS_LIST, getUuids(warehouses));
    }

    /**
     * Method test (fifth) - warehouseService.getWarehousesBy...
     */
    @Test
    public void getWarehousesByTest5() {
        StoreEntity store = uniqueCodeEntityService.getEntityByCode("default_store", StoreEntity.class);
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStore(store);
        assertUuidListsEquals(ALL_WAREHOUSES_UUIDS_LIST, getUuids(warehouses));
    }

    /**
     * Method test (sixth) - warehouseService.getWarehousesBy...
     */
    @Test
    public void getWarehousesByTest6() {
        StoreEntity store = uniqueCodeEntityService.getEntityByCode("default_store", StoreEntity.class);
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStore(store, false);
        assertUuidListsEquals(ENABLED_WAREHOUSES_UUIDS_LIST, getUuids(warehouses));
    }
}
