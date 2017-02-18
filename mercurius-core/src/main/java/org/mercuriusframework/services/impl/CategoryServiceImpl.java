package org.mercuriusframework.services.impl;

import org.mercuriusframework.dto.QueryParameter;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.CategoryService;
import org.mercuriusframework.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Category service
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    /**
     * Entity service
     */
    @Autowired
    private EntityService entityService;
    /**
     * Catalog unique code entity service
     */
    @Autowired
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Get all categories without a main super category
     * @param catalogCode Catalog code
     * @return List of categories
     */
    public List<CategoryEntity> getAllCategoriesWithoutMainSuperCategory(String catalogCode) {
        return entityService.getListResultByQuery("SELECT DISTINCT category FROM " + CategoryEntity.ENTITY_NAME + " as category " +
                "WHERE category." + CategoryEntity.MAIN_SUPER_CATEGORY + " IS NULL " +
                "AND category." + CategoryEntity.CATALOG + "." + CatalogEntity.CODE + " = :catalogCode",
                CategoryEntity.class, new QueryParameter("catalogCode", catalogCode));
    }

    /**
     * Get all categories without a main super category
     * @param catalog Catalog
     * @return List of categories
     */
    public List<CategoryEntity> getAllCategoriesWithoutMainSuperCategory(CatalogEntity catalog) {
        return entityService.getListResultByQuery("SELECT DISTINCT category FROM " + CategoryEntity.ENTITY_NAME +" as category " +
                        "WHERE category." + CategoryEntity.MAIN_SUPER_CATEGORY + " IS NULL " +
                        "AND category." + CategoryEntity.CATALOG + " = :catalog",
                CategoryEntity.class, new QueryParameter("catalog", catalog));
    }

    /**
     * Get category bread crumbs
     * @param categoryCode Category code
     * @param catalogCode  Catalog code
     * @return List of categories (ordered)
     */
    public List<CategoryEntity> getBreadCrumbs(String categoryCode, String catalogCode) {
        return getBreadCrumbs(catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(categoryCode, catalogCode, CategoryEntity.class,
                CategoryEntity.MAIN_SUPER_CATEGORY));
    }

    /**
     * Get category bread crumbs
     * @param categoryCode Category code
     * @param catalog      Catalog
     * @return List of categories (ordered)
     */
    public List<CategoryEntity> getBreadCrumbs(String categoryCode, CatalogEntity catalog) {
        return getBreadCrumbs(catalogUniqueCodeEntityService.getEntityByCodeAndCatalog(categoryCode, catalog, CategoryEntity.class,
                CategoryEntity.MAIN_SUPER_CATEGORY));
    }

    /**
     * Get category bread crumbs
     *
     * @param categoryUuid Category uuid
     * @return List of categories (ordered)
     */
    public List<CategoryEntity> getBreadCrumbs(String categoryUuid) {
        return getBreadCrumbs(entityService.findByUuid(categoryUuid, CategoryEntity.class, CategoryEntity.MAIN_SUPER_CATEGORY));
    }

    /**
     * Get category bread crumbs
     * @param category Category
     * @return List of categories (ordered)
     */
    public List<CategoryEntity> getBreadCrumbs(CategoryEntity category) {
        ArrayList<CategoryEntity> breadCrumbs = new ArrayList<CategoryEntity>();
        breadCrumbs.add(category);
        /** Build bread crumbs */
        CategoryEntity currentCategory = entityService.findByUuid(category.getUuid(), CategoryEntity.class, CategoryEntity.MAIN_SUPER_CATEGORY);
        while (currentCategory.getMainSuperCategory() != null) {
            currentCategory = entityService.findByUuid(currentCategory.getUuid(), CategoryEntity.class, CategoryEntity.MAIN_SUPER_CATEGORY);
            currentCategory = currentCategory.getMainSuperCategory();
            breadCrumbs.add(currentCategory);
        }
        breadCrumbs.trimToSize();
        Collections.reverse(breadCrumbs);
        return breadCrumbs;
    }

    /**
     * Get sub-categories
     * @param categoryCode Category code
     * @param catalogCode  Catalog code
     * @return List of categories
     */
    public List<CategoryEntity> getSubCategories(String categoryCode, String catalogCode) {
        return entityService.getListResultByQuery("SELECT DISTINCT category FROM " + CategoryEntity.ENTITY_NAME + " as category " +
                "LEFT JOIN category." + CategoryEntity.SUPER_CATEGORIES + " as superCategory " +
                "WHERE superCategory." + CategoryEntity.CATALOG + ".code = :catalogCode " +
                "AND superCategory." + CategoryEntity.CODE + " = :categoryCode",
                CategoryEntity.class, new QueryParameter("catalogCode", catalogCode), new QueryParameter("categoryCode", categoryCode));
    }

    /**
     * Get sub-categories
     * @param categoryCode Category code
     * @param catalog      Catalog
     * @return List of categories
     */
    public List<CategoryEntity> getSubCategories(String categoryCode, CatalogEntity catalog) {
        return entityService.getListResultByQuery("SELECT DISTINCT category FROM " + CategoryEntity.ENTITY_NAME + " as category " +
                "LEFT JOIN category." + CategoryEntity.SUPER_CATEGORIES + " as superCategory " +
                "WHERE superCategory." + CategoryEntity.CATALOG + " = :catalog " +
                "AND superCategory." + CategoryEntity.CODE + " = :categoryCode",
                CategoryEntity.class, new QueryParameter("catalog", catalog), new QueryParameter("categoryCode", categoryCode));
    }

    /**
     * Get sub-categories
     *
     * @param categoryUid Category uid
     * @return List of categories
     */
    public List<CategoryEntity> getSubCategories(String categoryUid) {
        return entityService.getListResultByQuery("SELECT DISTINCT category FROM " + CategoryEntity.ENTITY_NAME + " as category " +
                "LEFT JOIN category." + CategoryEntity.SUPER_CATEGORIES + " as superCategory " +
                "WHERE superCategory." + CategoryEntity.UUID + " = :categoryUid",
                CategoryEntity.class, new QueryParameter("categoryUid", categoryUid));
    }
}
