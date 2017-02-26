package org.mercuriusframework.facades.impl;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.facades.CatalogFacade;
import org.mercuriusframework.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Catalog facade implementation
 */
@Service("catalogFacade")
public class CatalogFacadeImpl implements CatalogFacade {

    /**
     * Session service
     */
    @Autowired
    protected SessionService sessionService;

    /**
     * Get default catalog
     * @return Catalog data transfer object
     */
    @Override
    public CatalogEntityDto getDefaultCatalog() {
        return (CatalogEntityDto) sessionService.getSessionAttribute(MercuriusConstants.SESSION_ATTRIBUTES.DEFAULT_CATALOG);
    }

    /**
     * Get default catalog
     *
     * @param request Http-request
     * @return Catalog data transfer object
     */
    @Override
    public CatalogEntityDto getDefaultCatalog(HttpServletRequest request) {
        return (CatalogEntityDto) sessionService.getSessionAttribute(request, MercuriusConstants.SESSION_ATTRIBUTES.DEFAULT_CATALOG);
    }

    /**
     * Set default catalog
     * @param catalog Catalog data transfer object
     */
    @Override
    public void setDefaultCatalog(CatalogEntityDto catalog) {
        sessionService.setSessionAttribute(MercuriusConstants.SESSION_ATTRIBUTES.DEFAULT_CATALOG, catalog);
    }

    /**
     * Set default catalog
     * @param request Http-request
     * @param catalog Catalog data transfer object
     */
    @Override
    public void setDefaultCatalog(HttpServletRequest request, CatalogEntityDto catalog) {
        sessionService.setSessionAttribute(request, MercuriusConstants.SESSION_ATTRIBUTES.DEFAULT_CATALOG, catalog);
    }
}
