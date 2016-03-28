package org.mercuriusframework.services;

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
}
