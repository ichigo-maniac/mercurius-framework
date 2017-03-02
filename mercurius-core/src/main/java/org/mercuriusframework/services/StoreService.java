package org.mercuriusframework.services;

import org.mercuriusframework.entities.StoreEntity;
import java.util.List;

/**
 * Store service interface
 */
public interface StoreService {

    /**
     * Get all stores
     * @return List of stores
     */
    List<StoreEntity> getStores();

    /**
     * Get stores
     * @param disabled Is stores disabled
     * @return List of stores
     */
    List<StoreEntity> getStores(boolean disabled);
}
