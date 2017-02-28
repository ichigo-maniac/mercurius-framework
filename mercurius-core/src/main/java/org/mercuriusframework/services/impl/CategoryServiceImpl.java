package org.mercuriusframework.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.services.query.QueryParameter;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.exceptions.DefaultCatalogPresetException;
import org.mercuriusframework.facades.CatalogFacade;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.CategoryService;
import org.mercuriusframework.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Category service
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    /**
     * Message constants
     */
    private static final String NO_CATEGORY_BY_CODE_MESSAGE = "There is no category with code \"{}\" and catalog \"{}\"";

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Entity service
     */
    @Autowired
    protected EntityService entityService;

    /**
     * Catalog unique code entity service
     */
    @Autowired
    protected CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Catalog facade
     */
    @Autowired
    protected CatalogFacade catalogFacade;

    /**
     * Get all categories without a main super category (use default catalog)
     * @return List of categories
     */
    @Override
    public List<CategoryEntity> getAllCategoriesWithoutMainSuperCategory() {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getAllCategoriesWithoutMainSuperCategory(catalog.getCode());
    }

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
     * Get category bread crumbs (use default catalog)
     * @param categoryCode Category code
     * @return List of categories (ordered)
     */
    @Override
    public List<CategoryEntity> getBreadCrumbs(String categoryCode) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getBreadCrumbs(categoryCode, catalog.getCode());
    }

    /**
     * Get category bread crumbs
     * @param categoryCode Category code
     * @param catalogCode  Catalog code
     * @return List of categories (ordered)
     */
    public List<CategoryEntity> getBreadCrumbs(String categoryCode, String catalogCode) {
        return getBreadCrumbsByCategory(catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(categoryCode, catalogCode, CategoryEntity.class,
                CategoryEntity.MAIN_SUPER_CATEGORY));
    }

    /**
     * Get category bread crumbs
     * @param categoryCode Category code
     * @param catalog      Catalog
     * @return List of categories (ordered)
     */
    public List<CategoryEntity> getBreadCrumbs(String categoryCode, CatalogEntity catalog) {
        return getBreadCrumbsByCategory(catalogUniqueCodeEntityService.getEntityByCodeAndCatalog(categoryCode, catalog, CategoryEntity.class,
                CategoryEntity.MAIN_SUPER_CATEGORY));
    }

    /**
     * Get category bread crumbs
     * @param categoryUuid Category uuid
     * @return List of categories (ordered)
     */
    public List<CategoryEntity> getBreadCrumbsByCategoryUuid(String categoryUuid) {
        return getBreadCrumbsByCategory(entityService.findByUuid(categoryUuid, CategoryEntity.class, CategoryEntity.MAIN_SUPER_CATEGORY));
    }

    /**
     * Get category bread crumbs
     * @param category Category
     * @return List of categories (ordered)
     */
    public List<CategoryEntity> getBreadCrumbsByCategory(CategoryEntity category) {
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
     * Get sub-categories (use default catalog)
     * @param categoryCode Category code
     * @return List of categories
     */
    @Override
    public List<CategoryEntity> getSubCategories(String categoryCode) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getSubCategories(categoryCode, catalog.getCode());
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
     * @param categoryUuid Category uuid
     * @return List of categories
     */
    public List<CategoryEntity> getSubCategoriesByCategoryUuid(String categoryUuid) {
        return entityService.getListResultByQuery("SELECT DISTINCT category FROM " + CategoryEntity.ENTITY_NAME + " as category " +
                "LEFT JOIN category." + CategoryEntity.SUPER_CATEGORIES + " as superCategory " +
                "WHERE superCategory." + CategoryEntity.UUID + " = :categoryUuid",
                CategoryEntity.class, new QueryParameter("categoryUuid", categoryUuid));
    }

    /**
     * Get all sub-categories (include low-level sub-categories) (use default catalog)
     * @param categoryCode Category code
     * @return List of categories
     */
    @Override
    public List<CategoryEntity> getAllSubCategories(String categoryCode) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCode(categoryCode, CategoryEntity.class);
        if (category == null) {
            LOGGER.error(NO_CATEGORY_BY_CODE_MESSAGE, categoryCode, catalog.getCode());
            return null;
        }
        return getAllSubCategoriesByCategoryUuid(category.getUuid());
    }

    /**
     * Get all sub-categories (include low-level sub-categories)
     * @param categoryCode Category code
     * @param catalogCode  Catalog code
     * @return List of categories
     */
    @Override
    public List<CategoryEntity> getAllSubCategories(String categoryCode, String catalogCode) {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(categoryCode, catalogCode, CategoryEntity.class);
        if (category == null) {
            LOGGER.error(NO_CATEGORY_BY_CODE_MESSAGE, categoryCode, catalogCode);
            return null;
        }
        return getAllSubCategoriesByCategoryUuid(category.getUuid());
    }

    /**
     * Get all sub-categories (include low-level sub-categories)
     * @param categoryCode Category code
     * @param catalog      Catalog
     * @return List of categories
     */
    @Override
    public List<CategoryEntity> getAllSubCategories(String categoryCode, CatalogEntity catalog) {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalog(categoryCode, catalog, CategoryEntity.class);
        if (category == null) {
            LOGGER.error(NO_CATEGORY_BY_CODE_MESSAGE, categoryCode, catalog.getCode());
            return null;
        }
        return getAllSubCategoriesByCategoryUuid(category.getUuid());
    }

    /**
     * Get all sub-categories (include low-level sub-categories)
     * @param categoryUuid Category uuid
     * @return List of categories
     */
    @Override
    public List<CategoryEntity> getAllSubCategoriesByCategoryUuid(String categoryUuid) {
        List<CategoryEntity> result = new CopyOnWriteArrayList<>();
        List<CategoryEntity> subCategories = getSubCategoriesByCategoryUuid(categoryUuid);
        if (!subCategories.isEmpty()) {
            result.addAll(subCategories);
            subCategories.parallelStream().forEach((subCategory) -> {
                result.addAll(getAllSubCategoriesByCategoryUuid(subCategory.getUuid()));
            });
        }
        return result;
    }
}
