package org.mercuriusframework.services;

import org.mercuriusframework.entities.Catalog;
import org.mercuriusframework.entities.CatalogUniqueCodeEntity;
import org.mercuriusframework.entities.UniqueCodeEntity;

/**
 * Code generation service
 */
public interface CodeGenerationService {
    /**
     * Generate unique code
     * @param type Entity type class
     * @param <T> Entity type class
     * @return Unique code
     */
    <T extends UniqueCodeEntity> String generateUniqueCode(Class<T> type);

    /**
     * Check unique code existence
     * @param type Entity type class
     * @param codeValue Code value
     * @param <T> Entity type class
     * @return Check result
     */
    <T extends UniqueCodeEntity> boolean existUniqueCode(Class<T> type, String codeValue);
    /**
     * Check unique code existence except one entity
     * @param type Entity type class
     * @param codeValue Code value
     * @param entityId Entity id
     * @param <T> Entity type class
     * @return Check result
     */
    <T extends UniqueCodeEntity> boolean existUniqueCodeExceptOne(Class<T> type, String codeValue, Long entityId);

    /**
     * Get UniqueCodeEntity super class
     *
     * @param currentClass Current entity class
     * @param <T>          Current entity class
     * @return Super class
     */
    <T extends UniqueCodeEntity> Class getSuperUniqueCodeClass(Class<T> currentClass);
    /**
     * Generate catalog unique code
     * @param type Entity type class
     * @param <T> Entity type class
     * @param catalog Catalog
     * @return Unique code
     */
    <T extends CatalogUniqueCodeEntity> String generateCatalogUniqueCode(Class<T> type, Catalog catalog);
    /**
     * Generate catalog unique code
     * @param type Entity type class
     * @param <T> Entity type class
     * @param catalogCode Catalog code
     * @return Unique code
     */
    <T extends CatalogUniqueCodeEntity> String generateCatalogUniqueCode(Class<T> type, String catalogCode);
    /**
     * Check catalog unique code existence
     * @param type Entity type class
     * @param codeValue Code value
     * @param <T> Entity type class
     * @param catalog Catalog
     * @return Check result
     */
    <T extends CatalogUniqueCodeEntity> boolean existCatalogUniqueCode(Class<T> type, String codeValue, Catalog catalog);
    /**
     * Check catalog unique code existence
     * @param type Entity type class
     * @param codeValue Code value
     * @param <T> Entity type class
     * @param catalogCode Catalog code
     * @return Check result
     */
    <T extends CatalogUniqueCodeEntity> boolean existCatalogUniqueCode(Class<T> type, String codeValue, String catalogCode);
    /**
     * Check catalog unique code existence except one entity
     * @param type Entity type class
     * @param codeValue Code value
     * @param catalog Catalog
     * @param entityId Entity id
     * @param <T> Entity type class
     * @return Check result
     */
    <T extends CatalogUniqueCodeEntity> boolean existCatalogUniqueCodeExceptOne(Class<T> type, String codeValue, Catalog catalog, Long entityId);
    /**
     * Check catalog unique code existence except one entity
     * @param type Entity type class
     * @param codeValue Code value
     * @param catalogCode Catalog code
     * @param entityId Entity id
     * @param <T> Entity type class
     * @return Check result
     */
    <T extends CatalogUniqueCodeEntity> boolean existCatalogUniqueCodeExceptOne(Class<T> type, String codeValue, String catalogCode, Long entityId);
    /**
     * Get CatalogUniqueCodeEntity super class
     *
     * @param currentClass Current entity class
     * @param <T>          Current entity class
     * @return Super class
     */
    <T extends CatalogUniqueCodeEntity> Class getSuperCatalogUniqueCodeClass(Class<T> currentClass);


}
