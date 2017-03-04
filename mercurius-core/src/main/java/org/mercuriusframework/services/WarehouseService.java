package org.mercuriusframework.services;

import org.mercuriusframework.entities.StoreEntity;
import org.mercuriusframework.entities.WarehouseEntity;

import java.util.List;

/**
 * Warehouse service interface
 */
public interface WarehouseService {

    /**
     * Get warehouses (use set current store)
     * @return List of warehoused
     */
    List<WarehouseEntity> getWarehouses();

    /**
     * Get warehouses (use set current store)
     * @param enabled Is warehouse enabled
     * @return List of warehoused
     */
    List<WarehouseEntity> getWarehouses(boolean enabled);
    /**
     * Get warehouses
     * @param storeUuid Store uuid
     * @return List of warehoused
     */
    List<WarehouseEntity> getWarehousesByStoreUuid(String storeUuid);

    /**
     * Get warehouses
     * @param storeUuid Store uuid
     * @param enabled Is warehouse enabled
     * @return List of warehoused
     */
    List<WarehouseEntity> getWarehousesByStoreUuid(String storeUuid, boolean enabled);

    /**
     * Get warehouses
     * @param storeCode Store code
     * @return List of warehoused
     */
    List<WarehouseEntity> getWarehousesByStoreCode(String storeCode);

    /**
     * Get warehouses
     * @param storeCode Store code
     * @param enabled Is warehouse enabled
     * @return List of warehoused
     */
    List<WarehouseEntity> getWarehousesByStoreCode(String storeCode, boolean enabled);

    /**
     * Get warehouses
     * @param store Store
     * @return List of warehoused
     */
    List<WarehouseEntity> getWarehousesByStore(StoreEntity store);

    /**
     * Get warehouses
     * @param store Store
     *  @param enabled Is warehouse enabled
     * @return List of warehoused
     */
    List<WarehouseEntity> getWarehousesByStore(StoreEntity store, boolean enabled);
}
