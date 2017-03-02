package org.mercuriusframework.facades;

import org.mercuriusframework.dto.StoreEntityDto;
import javax.servlet.http.HttpServletRequest;

/**
 * Store facade interface
 */
public interface StoreFacade {

    /**
     * Set current store
     * @param store Store data transfer object
     */
    void setCurrentStore(StoreEntityDto store);

    /**
     * Set current store
     * @param store Store data transfer object
     * @param request Http-request
     */
    void setCurrentStore(HttpServletRequest request, StoreEntityDto store);

    /**
     * Get current store
     * @return Store data transfer object
     */
    StoreEntityDto getCurrentStore();

    /**
     * Get current store
     * @param request Http-request
     * @return Store data transfer object
     */
    StoreEntityDto getCurrentStore(HttpServletRequest request);
}
