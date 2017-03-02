package org.mercuriusframework.services.impl;

import org.mercuriusframework.entities.StoreEntity;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.StoreService;
import org.mercuriusframework.services.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Store service
 */
@Service("storeService")
public class StoreServiceImpl implements StoreService {

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Get all stores
     * @return List of stores
     */
    @Override
    public List<StoreEntity> getStores() {
        return entityService.getListResultByQuery("SELECT DISTINCT store FROM " + StoreEntity.ENTITY_NAME + " as store ",
                StoreEntity.class);
    }

    /**
     * Get stores
     * @param disabled Is stores disabled
     * @return List of stores
     */
    @Override
    public List<StoreEntity> getStores(boolean disabled) {
        return entityService.getListResultByQuery("SELECT DISTINCT store FROM " + StoreEntity.ENTITY_NAME + " as store " +
                "WHERE store." + StoreEntity.DISABLED + " = :disableFlag",
                StoreEntity.class, new QueryParameter("disableFlag", disabled));
    }
}
