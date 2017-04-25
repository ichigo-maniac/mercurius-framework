package org.mercuriusframework.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.entities.FacetEntity;
import org.mercuriusframework.exceptions.DefaultCatalogPresetException;
import org.mercuriusframework.facades.CatalogFacade;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.FacetService;
import org.mercuriusframework.services.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Facet service
 */
@Service("facetService")
public class FacetServiceImpl implements FacetService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Catalog facade
     */
    @Autowired
    @Qualifier("catalogFacade")
    protected CatalogFacade catalogFacade;

    /**
     * Catalog unique code entity service
     */
    @Autowired
    @Qualifier("catalogUniqueCodeEntityService")
    protected CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Get facets by category
     * @param category Category
     * @return Facets
     */
    @Override
    public List<FacetEntity> getFacetsByCategory(CategoryEntity category) {
        return entityService.getListResultByQuery("SELECT facet FROM " + FacetEntity.ENTITY_NAME + " as facet " +
                        "WHERE :category IN facet." + FacetEntity.CATEGORIES,
                FacetEntity.class, new QueryParameter("category", category));
    }

    /**
     * @param categoryUuid Category uuid
     * @return Facets
     */
    @Override
    public List<FacetEntity> getFacetsByCategoryUuid(String categoryUuid) {
        CategoryEntity category = entityService.findByUuid(categoryUuid, CategoryEntity.class);
        if (category == null) {
            return Collections.emptyList();
        }
        return getFacetsByCategory(category);
    }

    /**
     * Get facets by category code (use default catalog)
     * @param categoryCode Category code
     * @return Facets
     */
    @Override
    public List<FacetEntity> getFacetsByCategoryCode(String categoryCode) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(categoryCode, catalog.getCode(), CategoryEntity.class);
        if (category == null) {
            return Collections.emptyList();
        }
        return getFacetsByCategory(category);
    }

    /**
     * Get facets by category code and catalog code
     * @param categoryCode Category code
     * @param catalogCode  Catalog code
     * @return Facets
     */
    @Override
    public List<FacetEntity> getFacetsByCategoryCode(String categoryCode, String catalogCode) {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(categoryCode, catalogCode, CategoryEntity.class);
        if (category == null) {
            return Collections.emptyList();
        }
        return getFacetsByCategory(category);
    }

    /**
     * Get facets by category code and catalog
     * @param categoryCode Category code
     * @param catalog      Catalog
     * @return Facets
     */
    @Override
    public List<FacetEntity> getFacetsByCategoryCode(String categoryCode, CatalogEntity catalog) {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalog(categoryCode, catalog, CategoryEntity.class);
        if (category == null) {
            return Collections.emptyList();
        }
        return getFacetsByCategory(category);
    }
}
