package org.mercuriusframework.facades.impl;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.dto.StoreEntityDto;
import org.mercuriusframework.facades.StoreFacade;
import org.mercuriusframework.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Store facade
 */
@Service("storeFacade")
public class StoreFacadeImpl implements StoreFacade {

    /**
     * Session service
     */
    @Autowired
    @Qualifier("sessionService")
    protected SessionService sessionService;

    /**
     * Set current store
     * @param store Store
     */
    @Override
    public void setCurrentStore(StoreEntityDto store) {
        sessionService.setSessionAttribute(MercuriusConstants.SESSION_ATTRIBUTES.CURRENT_STORE, store);
    }

    /**
     * Set current store
     * @param store   Store
     * @param request Http-request
     */
    @Override
    public void setCurrentStore(HttpServletRequest request, StoreEntityDto store) {
        sessionService.setSessionAttribute(request, MercuriusConstants.SESSION_ATTRIBUTES.CURRENT_STORE, store);
    }

    /**
     * Get current store
     * @return Store
     */
    @Override
    public StoreEntityDto getCurrentStore() {
        return (StoreEntityDto) sessionService.getSessionAttribute(MercuriusConstants.SESSION_ATTRIBUTES.CURRENT_STORE);
    }

    /**
     * Get current store
     * @param request Http-request
     * @return Store
     */
    @Override
    public StoreEntityDto getCurrentStore(HttpServletRequest request) {
        return (StoreEntityDto) sessionService.getSessionAttribute(request, MercuriusConstants.SESSION_ATTRIBUTES.CURRENT_STORE);
    }
}
