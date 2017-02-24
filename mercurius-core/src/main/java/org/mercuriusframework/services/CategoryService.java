package org.mercuriusframework.services;

import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CategoryEntity;

import java.util.List;

/**
 * Category service interface
 */
public interface CategoryService {

    /**
     * Get all categories without a main super category (use default catalog)
     * @return List of categories
     */
    List<CategoryEntity> getAllCategoriesWithoutMainSuperCategory();

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
     * Get category bread crumbs (use default catalog)
     * @param categoryCode Category code
     * @return List of categories (ordered)
     */
    List<CategoryEntity> getBreadCrumbs(String categoryCode);

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
    List<CategoryEntity> getBreadCrumbsByCategoryUuid(String categoryUuid);

    /**
     * Get category bread crumbs
     * @param category Category
     * @return List of categories (ordered)
     */
    List<CategoryEntity> getBreadCrumbsByCategory(CategoryEntity category);

    /**
     * Get sub-categories (use default catalog)
     * @param categoryCode Category code
     * @return List of categories
     */
    List<CategoryEntity> getSubCategories(String categoryCode);

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
     * @param categoryUuid Category uuid
     * @return List of categories
     */
    List<CategoryEntity> getSubCategoriesByCategoryUuid(String categoryUuid);

    /**
     * Get all sub-categories (include low-level sub-categories) (use default catalog)
     * @param categoryCode Category code
     * @return List of categories
     */
    List<CategoryEntity> getAllSubCategories(String categoryCode);

    /**
     * Get all sub-categories (include low-level sub-categories)
     * @param categoryCode Category code
     * @param catalogCode Catalog code
     * @return List of categories
     */
    List<CategoryEntity> getAllSubCategories(String categoryCode, String catalogCode);

    /**
     * Get all sub-categories (include low-level sub-categories)
     * @param categoryCode Category code
     * @param catalog Catalog
     * @return List of categories
     */
    List<CategoryEntity> getAllSubCategories(String categoryCode, CatalogEntity catalog);

    /**
     * Get all sub-categories (include low-level sub-categories)
     * @param categoryUuid Category uuid
     * @return List of categories
     */
    List<CategoryEntity> getAllSubCategoriesByCategoryUuid(String categoryUuid);

}
