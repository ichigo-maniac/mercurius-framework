package org.mercuriusframework.services.impl;

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
     * Entity service
     */
    @Autowired
    private EntityService entityService;

    /**
     * Category service
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Catalog unique code entity service
     */
    @Autowired
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Catalog facade
     */
    @Autowired
    private CatalogFacade catalogFacade;

    /**
     * Get all products (include low-level categories' products) (use default catalog)
     * @param categoryCode Category code
     * @param currentPage  Current page
     * @param pageSize     Page size
     * @return Pageable products container
     */
    @Override
    public PageableResult<ProductEntity> getAllProductByCategory(String categoryCode, Integer currentPage, Integer pageSize) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCode(categoryCode, CategoryEntity.class);
        if (category == null) {
            return null;
        }
        return getAllProductByCategoryUuid(category.getUuid(), currentPage, pageSize);
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
    public PageableResult<ProductEntity> getAllProductByCategory(String categoryCode, String catalogCode, Integer currentPage, Integer pageSize) {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(categoryCode, catalogCode, CategoryEntity.class);
        if (category == null) {
            return null;
        }
        return getAllProductByCategoryUuid(category.getUuid(), currentPage, pageSize);
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
    public PageableResult<ProductEntity> getAllProductByCategory(String categoryCode, CatalogEntity catalog, Integer currentPage, Integer pageSize) {
        CategoryEntity category = catalogUniqueCodeEntityService.getEntityByCodeAndCatalog(categoryCode, catalog, CategoryEntity.class);
        if (category == null) {
            return null;
        }
        return getAllProductByCategoryUuid(category.getUuid(), currentPage, pageSize);
    }

    /**
     * Get all products (include low-level categories' products)
     * @param categoryUUid Category uuid
     * @param currentPage Current page
     * @param pageSize Page size
     * @return Pageable products container
     */
    @Override
    public PageableResult<ProductEntity> getAllProductByCategoryUuid(String categoryUUid, Integer currentPage, Integer pageSize) {
        /** Load categories */
        CategoryEntity categoryEntity = entityService.findByUuid(categoryUUid, CategoryEntity.class);
        if (categoryEntity == null) {
            return null;
        }
        List<CategoryEntity> categories = categoryService.getAllSubCategoriesByCategoryUuid(categoryUUid);
        if (categories == null) {
            return null;
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
