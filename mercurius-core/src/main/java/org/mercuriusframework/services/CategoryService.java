package org.mercuriusframework.services;

import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.Category;

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
    List<Category> getAllCategoriesWithoutMainSuperCategory(String catalogCode);
    /**
     * Get all categories without a main super category
     * @param catalog Catalog
     * @return List of categories
     */
    List<Category> getAllCategoriesWithoutMainSuperCategory(CatalogEntity catalog);
    /**
     * Get category bread crumbs
     * @param categoryCode Category code
     * @param catalogCode Catalog code
     * @return List of categories (ordered)
     */
    List<Category> getBreadCrumbs(String categoryCode, String catalogCode);
    /**
     * Get category bread crumbs
     * @param categoryCode Category code
     * @param catalog Catalog
     * @return List of categories (ordered)
     */
    List<Category> getBreadCrumbs(String categoryCode, CatalogEntity catalog);
    /**
     * Get category bread crumbs
     * @param categoryUuid Category uid
     * @return List of categories (ordered)
     */
    List<Category> getBreadCrumbs(String categoryUuid);
    /**
     * Get category bread crumbs
     * @param category Category
     * @return List of categories (ordered)
     */
    List<Category> getBreadCrumbs(Category category);
    /**
     * Get sub-categories
     * @param categoryCode Category code
     * @param catalogCode Catalog code
     * @return List of categories
     */
    List<Category> getSubCategories(String categoryCode, String catalogCode);
    /**
     * Get sub-categories
     * @param categoryCode Category code
     * @param catalog Catalog
     * @return List of categories
     */
    List<Category> getSubCategories(String categoryCode, CatalogEntity catalog);
    /**
     * Get sub-categories
     * @param categoryUid Category uid
     * @return List of categories
     */
    List<Category> getSubCategories(String categoryUid);

}
