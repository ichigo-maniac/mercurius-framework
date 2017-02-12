package org.mercuriusframework.services.impl;

import org.mercuriusframework.dto.QueryParameter;
import org.mercuriusframework.entities.Catalog;
import org.mercuriusframework.entities.Category;
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
    public List<Category> getAllCategoriesWithoutMainSuperCategory(String catalogCode) {
        return entityService.getListResultByQuery("SELECT category FROM Category as category " +
                "WHERE category.mainSuperCategory IS NULL AND category.catalog.code = :catalogCode",
                Category.class, new QueryParameter("catalogCode", catalogCode));
    }

    /**
     * Get all categories without a main super category
     *
     * @param catalog Catalog
     * @return List of categories
     */
    public List<Category> getAllCategoriesWithoutMainSuperCategory(Catalog catalog) {
        return entityService.getListResultByQuery("SELECT category FROM Category as category " +
                        "WHERE category.mainSuperCategory IS NULL AND category.catalog = :catalog",
                Category.class, new QueryParameter("catalog", catalog));
    }

    /**
     * Get category bread crumbs
     *
     * @param categoryCode Category code
     * @param catalogCode  Catalog code
     * @return List of categories (ordered)
     */
    public List<Category> getBreadCrumbs(String categoryCode, String catalogCode) {
        return getBreadCrumbs(catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(categoryCode, catalogCode, Category.class, "mainSuperCategory"));
    }

    /**
     * Get category bread crumbs
     *
     * @param categoryCode Category code
     * @param catalog      Catalog
     * @return List of categories (ordered)
     */
    public List<Category> getBreadCrumbs(String categoryCode, Catalog catalog) {
        return getBreadCrumbs(catalogUniqueCodeEntityService.getEntityByCodeAndCatalog(categoryCode, catalog, Category.class, "mainSuperCategory"));
    }

    /**
     * Get category bread crumbs
     *
     * @param categoryUuid Category uuid
     * @return List of categories (ordered)
     */
    public List<Category> getBreadCrumbs(String categoryUuid) {
        return getBreadCrumbs(entityService.findByUuid(categoryUuid, Category.class, "mainSuperCategory"));
    }

    /**
     * Get category bread crumbs
     *
     * @param category Category
     * @return List of categories (ordered)
     */
    public List<Category> getBreadCrumbs(Category category) {
        ArrayList<Category> breadCrumbs = new ArrayList<Category>();
        breadCrumbs.add(category);
        /** Build bread crumbs */
        Category currentCategory = entityService.findByUuid(category.getUuid(), Category.class, "mainSuperCategory");
        while (currentCategory.getMainSuperCategory() != null) {
            currentCategory = entityService.findByUuid(currentCategory.getUuid(), Category.class, "mainSuperCategory");
            currentCategory = currentCategory.getMainSuperCategory();
            breadCrumbs.add(currentCategory);
        }
        breadCrumbs.trimToSize();
        Collections.reverse(breadCrumbs);
        return breadCrumbs;
    }

    /**
     * Get sub-categories
     *
     * @param categoryCode Category code
     * @param catalogCode  Catalog code
     * @return List of categories
     */
    public List<Category> getSubCategories(String categoryCode, String catalogCode) {
        return entityService.getListResultByQuery("SELECT DISTINCT category FROM Category as category " +
                "LEFT JOIN category.superCategories as superCategory " +
                "WHERE superCategory.catalog.code = :catalogCode AND superCategory.code = :categoryCode",
                Category.class, new QueryParameter("catalogCode", catalogCode), new QueryParameter("categoryCode", categoryCode));
    }

    /**
     * Get sub-categories
     *
     * @param categoryCode Category code
     * @param catalog      Catalog
     * @return List of categories
     */
    public List<Category> getSubCategories(String categoryCode, Catalog catalog) {
        return entityService.getListResultByQuery("SELECT DISTINCT category FROM Category as category " +
                "LEFT JOIN category.superCategories as superCategory " +
                "WHERE superCategory.catalog = :catalog AND superCategory.code = :categoryCode",
                Category.class, new QueryParameter("catalog", catalog), new QueryParameter("categoryCode", categoryCode));
    }

    /**
     * Get sub-categories
     *
     * @param categoryUid Category uid
     * @return List of categories
     */
    public List<Category> getSubCategories(String categoryUid) {
        return entityService.getListResultByQuery("SELECT DISTINCT category FROM Category as category " +
                "LEFT JOIN category.superCategories as superCategory " +
                "WHERE superCategory.uuid = :categoryUid",
                Category.class, new QueryParameter("categoryUid", categoryUid));
    }
}
