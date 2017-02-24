package org.mercuriusframework.facades;

import org.mercuriusframework.dto.CatalogEntityDto;
import javax.servlet.http.HttpServletRequest;

/**
 * Catalog facade interface
 */
public interface CatalogFacade {

    /**
     * Get default catalog
     * @return Catalog data transfer object
     */
    CatalogEntityDto getDefaultCatalog();

    /**
     * Get default catalog
     * @param request Http-request
     * @return Catalog data transfer object
     */
    CatalogEntityDto getDefaultCatalog(HttpServletRequest request);

    /**
     * Set default catalog
     * @param catalog Catalog data transfer object
     */
    void setDefaultCatalog(CatalogEntityDto catalog);

    /**
     * Set default catalog
     * @param request Http-request
     * @param catalog Catalog data transfer object
     */
    void setDefaultCatalog(HttpServletRequest request, CatalogEntityDto catalog);
}
