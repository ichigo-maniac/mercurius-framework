package org.mercuriusframework.services;

import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CategoryEntity;

import java.util.List;

/**
 * Category service interface
 */
public interface CategoryService {
    /**
     * Get all categories without a main super category
     * @param catalogCode Catalog code
     * @return List of categories
     */
    List<CategoryEntity> getAllCategoriesWithoutMainSuperCategory(String catalogCode);
    /**
     * Get all categories without a main super category
     * @param catalog Catalog
     * @return List of categories
     */
    List<CategoryEntity> getAllCategoriesWithoutMainSuperCategory(CatalogEntity catalog);
    /**
     * Get category bread crumbs
     * @param categoryCode Category code
     * @param catalogCode Catalog code
     * @return List of categories (ordered)
     */
    List<CategoryEntity> getBreadCrumbs(String categoryCode, String catalogCode);
    /**
     * Get category bread crumbs
     * @param categoryCode Category code
     * @param catalog Catalog
     * @return List of categories (ordered)
     */
    List<CategoryEntity> getBreadCrumbs(String categoryCode, CatalogEntity catalog);
    /**
     * Get category bread crumbs
     * @param categoryUuid Category uid
     * @return List of categories (ordered)
     */
    List<CategoryEntity> getBreadCrumbs(String categoryUuid);
    /**
     * Get category bread crumbs
     * @param category Category
     * @return List of categories (ordered)
     */
    List<CategoryEntity> getBreadCrumbs(CategoryEntity category);
    /**
     * Get sub-categories
     * @param categoryCode Category code
     * @param catalogCode Catalog code
     * @return List of categories
     */
    List<CategoryEntity> getSubCategories(String categoryCode, String catalogCode);
    /**
     * Get sub-categories
     * @param categoryCode Category code
     * @param catalog Catalog
     * @return List of categories
     */
    List<CategoryEntity> getSubCategories(String categoryCode, CatalogEntity catalog);
    /**
     * Get sub-categories
     * @param categoryUid Category uid
     * @return List of categories
     */
    List<CategoryEntity> getSubCategories(String categoryUid);

}
