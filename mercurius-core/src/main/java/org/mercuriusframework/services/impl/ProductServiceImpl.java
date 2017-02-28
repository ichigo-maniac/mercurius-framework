package org.mercuriusframework.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.exceptions.DefaultCatalogPresetException;
import org.mercuriusframework.facades.CatalogFacade;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.CategoryService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.ProductService;
import org.mercuriusframework.services.query.PageableResult;
import org.mercuriusframework.services.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Product service
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    /**
     * Message constants
     */
    private static final String NO_CATEGORY_BY_CODE_MESSAGE = "There is no category with code \"{}\" and catalog \"{}\"";
    private static final String NO_CATEGORY_BY_UUID_MESSAGE = "There is no category with uuid \"{}\"";

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
     * Category service
     */
    @Autowired
    protected CategoryService categoryService;

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
     * Get products (use default catalog)
     * @param categoryCode Category code
     * @return List of products
     */
    @Override
    public List<ProductEntity> getProductsByCategory(String categoryCode) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCode(categoryCode, CategoryEntity.class);
        if (category == null) {
            LOGGER.error(NO_CATEGORY_BY_CODE_MESSAGE, categoryCode, catalog.getCode());
            return null;
        }
        return getProductsByCategoryUuid(category.getUuid());
    }

    /**
     * Get products (use default catalog)
     * @param categoryCode Category code
     * @param currentPage  Current page
     * @param pageSize     Page size
     * @return Pageable products container
     */
    @Override
    public PageableResult<ProductEntity> getProductsByCategory(String categoryCode, Integer currentPage, Integer pageSize) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCode(categoryCode, CategoryEntity.class);
        if (category == null) {
            LOGGER.error(NO_CATEGORY_BY_CODE_MESSAGE, categoryCode, catalog.getCode());
            return null;
        }
        return getProductsByCategoryUuid(category.getUuid(), currentPage, pageSize);
    }

    /**
     * Get all products (include low-level categories' products)
     * @param categoryCode Category code
     * @param catalogCode  Catalog code
     * @return List of products
     */
    @Override
    public List<ProductEntity> getProductsByCategory(String categoryCode, String catalogCode) {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(categoryCode, catalogCode, CategoryEntity.class);
        if (category == null) {
            LOGGER.error(NO_CATEGORY_BY_CODE_MESSAGE, categoryCode, catalogCode);
            return null;
        }
        return getProductsByCategoryUuid(category.getUuid());
    }

    /**
     * Get products
     * @param categoryCode Category code
     * @param catalogCode  Catalog code
     * @param currentPage  Current page
     * @param pageSize     Page size
     * @return Pageable products container
     */
    @Override
    public PageableResult<ProductEntity> getProductsByCategory(String categoryCode, String catalogCode, Integer currentPage, Integer pageSize) {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(categoryCode, catalogCode, CategoryEntity.class);
        if (category == null) {
            LOGGER.error(NO_CATEGORY_BY_CODE_MESSAGE, categoryCode, catalogCode);
            return null;
        }
        return getProductsByCategoryUuid(category.getUuid(), currentPage, pageSize);
    }

    /**
     * Get products
     * @param categoryCode Category code
     * @param catalog      Catalog
     * @return List of products
     */
    @Override
    public List<ProductEntity> getProductsByCategory(String categoryCode, CatalogEntity catalog) {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalog(categoryCode, catalog, CategoryEntity.class);
        if (category == null) {
            LOGGER.error(NO_CATEGORY_BY_CODE_MESSAGE, categoryCode, catalog.getCode());
            return null;
        }
        return getProductsByCategoryUuid(category.getUuid());
    }

    /**
     * Get products
     * @param categoryCode Category code
     * @param catalog      Catalog
     * @param currentPage  Current page
     * @param pageSize     Page size
     * @return Pageable products container
     */
    @Override
    public PageableResult<ProductEntity> getProductsByCategory(String categoryCode, CatalogEntity catalog, Integer currentPage, Integer pageSize) {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalog(categoryCode, catalog, CategoryEntity.class);
        if (category == null) {
            LOGGER.error(NO_CATEGORY_BY_CODE_MESSAGE, categoryCode, catalog.getCode());
            return null;
        }
        return getProductsByCategoryUuid(category.getUuid(), currentPage, pageSize);
    }

    /**
     * Get products
     * @param categoryUUid Category uuid
     * @return List of products
     */
    @Override
    public List<ProductEntity> getProductsByCategoryUuid(String categoryUUid) {
        CategoryEntity categoryEntity = entityService.findByUuid(categoryUUid, CategoryEntity.class);
        if (categoryEntity == null) {
            LOGGER.error(NO_CATEGORY_BY_UUID_MESSAGE, categoryUUid);
            return null;
        }
        return entityService.getListResultByQuery("SELECT DISTINCT product FROM " + ProductEntity.ENTITY_NAME + " as product " +
                "WHERE :category MEMBER OF product." + ProductEntity.CATEGORIES + " " +
                "ORDER BY product." + ProductEntity.NAME,
                ProductEntity.class, new QueryParameter("category", categoryEntity)
        );
    }

    /**
     * Get products
     *
     * @param categoryUUid Category uuid
     * @param currentPage  Current page
     * @param pageSize     Page size
     * @return Pageable products container
     */
    @Override
    public PageableResult<ProductEntity> getProductsByCategoryUuid(String categoryUUid, Integer currentPage, Integer pageSize) {
        CategoryEntity categoryEntity = entityService.findByUuid(categoryUUid, CategoryEntity.class);
        if (categoryEntity == null) {
            LOGGER.error(NO_CATEGORY_BY_UUID_MESSAGE, categoryUUid);
            return null;
        }
        return entityService.getPageableResultByQueries("SELECT DISTINCT product FROM " + ProductEntity.ENTITY_NAME + " as product " +
                "WHERE :category MEMBER OF product." + ProductEntity.CATEGORIES + " " +
                "ORDER BY product." + ProductEntity.NAME,
                "SELECT count(product) FROM " + ProductEntity.ENTITY_NAME + " as product " +
                "WHERE :category MEMBER OF product." + ProductEntity.CATEGORIES,
                currentPage, pageSize, ProductEntity.class, new QueryParameter("category", categoryEntity)
                );
    }

    /**
     * Get all products (include low-level categories' products) (use default catalog)
     * @param categoryCode Category code
     * @param currentPage  Current page
     * @param pageSize     Page size
     * @return Pageable products container
     */
    @Override
    public PageableResult<ProductEntity> getAllProductsByCategory(String categoryCode, Integer currentPage, Integer pageSize) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCode(categoryCode, CategoryEntity.class);
        if (category == null) {
            LOGGER.error(NO_CATEGORY_BY_CODE_MESSAGE, categoryCode, catalog.getCode());
            return null;
        }
        return getAllProductsByCategoryUuid(category.getUuid(), currentPage, pageSize);
    }

    /**
     * Get all products (include low-level categories' products)
     * @param categoryCode Category code
     * @param catalogCode  Catalog code
     * @param currentPage  Current page
     * @param pageSize     Page size
     * @return Pageable products container
     */
    @Override
    public PageableResult<ProductEntity> getAllProductsByCategory(String categoryCode, String catalogCode, Integer currentPage, Integer pageSize) {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(categoryCode, catalogCode, CategoryEntity.class);
        if (category == null) {
            LOGGER.error(NO_CATEGORY_BY_CODE_MESSAGE, categoryCode, catalogCode);
            return null;
        }
        return getAllProductsByCategoryUuid(category.getUuid(), currentPage, pageSize);
    }

    /**
     * Get all products (include low-level categories' products)
     * @param categoryCode Category code
     * @param catalog      Catalog
     * @param currentPage  Current page
     * @param pageSize     Page size
     * @return Pageable products container
     */
    @Override
    public PageableResult<ProductEntity> getAllProductsByCategory(String categoryCode, CatalogEntity catalog, Integer currentPage, Integer pageSize) {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalog(categoryCode, catalog, CategoryEntity.class);
        if (category == null) {
            LOGGER.error(NO_CATEGORY_BY_CODE_MESSAGE, categoryCode, catalog.getCode());
            return null;
        }
        return getAllProductsByCategoryUuid(category.getUuid(), currentPage, pageSize);
    }

    /**
     * Get all products (include low-level categories' products)
     * @param categoryUUid Category uuid
     * @param currentPage Current page
     * @param pageSize Page size
     * @return Pageable products container
     */
    @Override
    public PageableResult<ProductEntity> getAllProductsByCategoryUuid(String categoryUUid, Integer currentPage, Integer pageSize) {
        /** Load categories */
        CategoryEntity categoryEntity = entityService.findByUuid(categoryUUid, CategoryEntity.class);
        if (categoryEntity == null) {
            LOGGER.error(NO_CATEGORY_BY_UUID_MESSAGE, categoryUUid);
            return null;
        }
        List<CategoryEntity> categories = categoryService.getAllSubCategoriesByCategoryUuid(categoryUUid);
        if (categories == null) {
            return PageableResult.emptyPageableResult();
        }
        categories.add(categoryEntity);
        /** Load products */
        return entityService.getPageableResultByQueries("SELECT DISTINCT product FROM " + ProductEntity.ENTITY_NAME + " as product " +
                "LEFT JOIN product." + ProductEntity.CATEGORIES + " as category " +
                "WHERE category IN (:productCategories) " +
                "ORDER BY product." + ProductEntity.NAME,
                "SELECT count(product) FROM " + ProductEntity.ENTITY_NAME + " as product " +
                "LEFT JOIN product." + ProductEntity.CATEGORIES + " as category " +
                "WHERE category IN (:productCategories)",
                currentPage, pageSize,
                ProductEntity.class, new QueryParameter("productCategories", categories));
    }
}
