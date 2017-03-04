package org.mercuriusframework.services.impl;

import org.mercuriusframework.dto.StoreEntityDto;
import org.mercuriusframework.entities.StoreEntity;
import org.mercuriusframework.entities.WarehouseEntity;
import org.mercuriusframework.exceptions.CurrentStorePresetException;
import org.mercuriusframework.facades.StoreFacade;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.WarehouseService;
import org.mercuriusframework.services.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Warehouse service
 */
@Service("warehouseService")
public class WarehouseServiceImpl implements WarehouseService {

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Store facade
     */
    @Autowired
    @Qualifier("storeFacade")
    protected StoreFacade storeFacade;

    /**
     * Get warehouses (use set current store)
     * @return List of warehoused
     */
    @Override
    public List<WarehouseEntity> getWarehouses() {
        StoreEntityDto store = storeFacade.getCurrentStore();
        if (store == null) {
            throw new CurrentStorePresetException();
        }
        return getWarehousesByStoreUuid(store.getUuid());
    }

    /**
     * Get warehouses (use set current store)
     * @param enabled Is warehouse enabled
     * @return List of warehoused
     */
    @Override
    public List<WarehouseEntity> getWarehouses(boolean enabled) {
        StoreEntityDto store = storeFacade.getCurrentStore();
        if (store == null) {
            throw new CurrentStorePresetException();
        }
        return getWarehousesByStoreUuid(store.getUuid(), enabled);
    }

    /**
     * Get warehouses
     * @param storeUuid Store uuid
     * @return List of warehoused
     */
    @Override
    public List<WarehouseEntity> getWarehousesByStoreUuid(String storeUuid) {
        return entityService.getListResultByQuery("SELECT DISTINCT warehouse FROM " + WarehouseEntity.ENTITY_NAME + " as warehouse " +
                "WHERE warehouse." + WarehouseEntity.STORE + "." + StoreEntity.UUID + " = :storeUuid",
                WarehouseEntity.class, new QueryParameter("storeUuid", storeUuid));
    }

    /**
     * Get warehouses
     * @param storeUuid Store uuid
     * @param enabled  Is warehouse enabled
     * @return List of warehoused
     */
    @Override
    public List<WarehouseEntity> getWarehousesByStoreUuid(String storeUuid, boolean enabled) {
        return entityService.getListResultByQuery("SELECT DISTINCT warehouse FROM " + WarehouseEntity.ENTITY_NAME + " as warehouse " +
                        "WHERE warehouse." + WarehouseEntity.STORE + "." + StoreEntity.UUID + " = :storeUuid " +
                        "AND warehouse." + WarehouseEntity.ENABLED + " = :enabled",
                WarehouseEntity.class, new QueryParameter("storeUuid", storeUuid), new QueryParameter("enabled", enabled));
    }

    /**
     * Get warehouses
     * @param storeCode Store code
     * @return List of warehoused
     */
    @Override
    public List<WarehouseEntity> getWarehousesByStoreCode(String storeCode) {
        return entityService.getListResultByQuery("SELECT DISTINCT warehouse FROM " + WarehouseEntity.ENTITY_NAME + " as warehouse " +
                        "WHERE warehouse." + WarehouseEntity.STORE + "." + StoreEntity.CODE + " = :storeCode",
                WarehouseEntity.class, new QueryParameter("storeCode", storeCode));
    }

    /**
     * Get warehouses
     * @param storeCode Store code
     * @param enabled  Is warehouse enabled
     * @return List of warehoused
     */
    @Override
    public List<WarehouseEntity> getWarehousesByStoreCode(String storeCode, boolean enabled) {
        return entityService.getListResultByQuery("SELECT DISTINCT warehouse FROM " + WarehouseEntity.ENTITY_NAME + " as warehouse " +
                        "WHERE warehouse." + WarehouseEntity.STORE + "." + StoreEntity.CODE + " = :storeCode " +
                        "AND warehouse." + WarehouseEntity.ENABLED + " = :enabled",
                WarehouseEntity.class, new QueryParameter("storeCode", storeCode), new QueryParameter("enabled", enabled));
    }

    /**
     * Get warehouses
     * @param store Store
     * @return List of warehoused
     */
    @Override
    public List<WarehouseEntity> getWarehousesByStore(StoreEntity store) {
        return entityService.getListResultByQuery("SELECT DISTINCT warehouse FROM " + WarehouseEntity.ENTITY_NAME + " as warehouse " +
                        "WHERE warehouse." + WarehouseEntity.STORE + " = :store",
                WarehouseEntity.class, new QueryParameter("store", store));
    }

    /**
     * Get warehouses
     * @param store    Store
     * @param enabled Is warehouse enabled
     * @return List of warehoused
     */
    @Override
    public List<WarehouseEntity> getWarehousesByStore(StoreEntity store, boolean enabled) {
        return entityService.getListResultByQuery("SELECT DISTINCT warehouse FROM " + WarehouseEntity.ENTITY_NAME + " as warehouse " +
                        "WHERE warehouse." + WarehouseEntity.STORE + " = :store " +
                        "AND warehouse." + WarehouseEntity.ENABLED + " = :enabled",
                WarehouseEntity.class, new QueryParameter("store", store), new QueryParameter("enabled", enabled));
    }
}
