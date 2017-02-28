package org.mercuriusframework.services;

import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.services.query.PageableResult
import java.util.List;

/**
 * Product service interface
 */
public interface ProductService {

    /**
     * Get products (use default catalog)
     * @param categoryCode Category code
     * @return List of products
     */
    List<ProductEntity> getProductsByCategory(String categoryCode);

    /**
     * Get products (use default catalog)
     * @param categoryCode Category code
     * @param currentPage Current page
     * @param pageSize Page size
     * @return Pageable products container
     */
    PageableResult<ProductEntity> getProductsByCategory(String categoryCode, Integer currentPage, Integer pageSize);

    /**
     * Get all products (include low-level categories' products)
     * @param categoryCode Category code
     * @param catalogCode Catalog code
     * @return List of products
     */
    List<ProductEntity> getProductsByCategory(String categoryCode, String catalogCode);

    /**
     * Get products
     * @param categoryCode Category code
     * @param catalogCode Catalog code
     * @param currentPage Current page
     * @param pageSize Page size
     * @return Pageable products container
     */
    PageableResult<ProductEntity> getProductsByCategory(String categoryCode, String catalogCode, Integer currentPage, Integer pageSize);

    /**
     * Get products
     * @param categoryCode Category code
     * @param catalog Catalog
     * @return List of products
     */
    List<ProductEntity> getProductsByCategory(String categoryCode, CatalogEntity catalog);

    /**
     * Get products
     * @param categoryCode Category code
     * @param catalog Catalog
     * @param currentPage Current page
     * @param pageSize Page size
     * @return Pageable products container
     */
    PageableResult<ProductEntity> getProductsByCategory(String categoryCode, CatalogEntity catalog, Integer currentPage, Integer pageSize);

    /**
     * Get products
     * @param categoryUUid Category uuid
     * @return List of products
     */
    List<ProductEntity> getProductsByCategoryUuid(String categoryUUid);

    /**
     * Get products
     * @param categoryUUid Category uuid
     * @param currentPage Current page
     * @param pageSize Page size
     * @return Pageable products container
     */
    PageableResult<ProductEntity> getProductsByCategoryUuid(String categoryUUid, Integer currentPage, Integer pageSize);

    /**
     * Get all products (include low-level categories' products) (use default catalog)
     * @param categoryCode Category code
     * @param currentPage Current page
     * @param pageSize Page size
     * @return Pageable products container
     */
    PageableResult<ProductEntity> getAllProductByCategory(String categoryCode, Integer currentPage, Integer pageSize);

    /**
     * Get all products (include low-level categories' products)
     * @param categoryCode Category code
     * @param catalogCode Catalog code
     * @param currentPage Current page
     * @param pageSize Page size
     * @return Pageable products container
     */
    PageableResult<ProductEntity> getAllProductByCategory(String categoryCode, String catalogCode, Integer currentPage, Integer pageSize);

    /**
     * Get all products (include low-level categories' products)
     * @param categoryCode Category code
     * @param catalog Catalog
     * @param currentPage Current page
     * @param pageSize Page size
     * @return Pageable products container
     */
    PageableResult<ProductEntity> getAllProductByCategory(String categoryCode, CatalogEntity catalog, Integer currentPage, Integer pageSize);

    /**
     * Get all products (include low-level categories' products)
     * @param categoryUUid Category uuid
     * @param currentPage Current page
     * @param pageSize Page size
     * @return Pageable products container
     */
    PageableResult<ProductEntity> getAllProductByCategoryUuid(String categoryUUid, Integer currentPage, Integer pageSize);
}
