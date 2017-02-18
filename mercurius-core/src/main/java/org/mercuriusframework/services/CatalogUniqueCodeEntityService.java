package org.mercuriusframework.services;

import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CatalogUniqueCodeEntity;

/**
 * Catalog unique code entity service interface
 */
public interface CatalogUniqueCodeEntityService {
    /**
     * Get catalog unique code entity by code and catalog
     * @param code Entity code
     * @param catalog Catalog
     * @param clazz  Entity class
     * @param fetchFields Fetch fields
     * @param <T> Entity class
     * @return Catalog unique code entity
     */
    <T extends CatalogUniqueCodeEntity> T getEntityByCodeAndCatalog(String code, CatalogEntity catalog, Class<T> clazz, String... fetchFields);
    /**
     * Get catalog unique code entity by code and catalog code
     * @param code Entity code
     * @param catalogCode Catalog code
     * @param clazz  Entity class
     * @param fetchFields Fetch fields
     * @param <T> Entity class
     * @return Catalog unique code entity
     */
    <T extends CatalogUniqueCodeEntity> T getEntityByCodeAndCatalogCode(String code, String catalogCode, Class<T> clazz, String... fetchFields);
}
