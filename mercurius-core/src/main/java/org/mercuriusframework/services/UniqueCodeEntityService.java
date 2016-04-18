package org.mercuriusframework.services;

import org.mercuriusframework.entities.UniqueCodeEntity;

/**
 * Unique code entity service interface
 */
public interface UniqueCodeEntityService {
    /**
     * Get unique code entity by code
     * @param code Entity code
     * @param clazz  Entity class
     * @param <T> Entity class
     * @return Unique code entity
     */
    <T extends UniqueCodeEntity> T getEntityByCode(String code, Class<T> clazz);
}
