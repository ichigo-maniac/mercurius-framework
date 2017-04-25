package org.mercuriusframework.services;

import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.entities.FacetEntity;

import java.util.List;

/**
 * Facet service interface
 */
public interface FacetService {

    /**
     * Get facets by category
     * @param category Category
     * @return Facets
     */
    List<FacetEntity> getFacetsByCategory(CategoryEntity category);

    /**
     * Get facets by category uuid
     * @param categoryUuid Category uuid
     * @return Facets
     */
    List<FacetEntity> getFacetsByCategoryUuid(String categoryUuid);

    /**
     * Get facets by category code (use default catalog)
     * @param categoryCode Category code
     * @return Facets
     */
    List<FacetEntity> getFacetsByCategoryCode(String categoryCode);

    /**
     * Get facets by category code and catalog code
     * @param categoryCode Category code
     * @param catalogCode Catalog code
     * @return Facets
     */
    List<FacetEntity> getFacetsByCategoryCode(String categoryCode, String catalogCode);

    /**
     * Get facets by category code and catalog
     * @param categoryCode Category code
     * @param catalog Catalog
     * @return Facets
     */
    List<FacetEntity> getFacetsByCategoryCode(String categoryCode, CatalogEntity catalog);
}
