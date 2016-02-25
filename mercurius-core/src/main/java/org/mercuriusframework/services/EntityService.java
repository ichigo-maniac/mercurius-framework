package org.mercuriusframework.services;

import org.mercuriusframework.dto.QueryParameter;
import org.mercuriusframework.entities.AbstractEntity;

import java.util.List;

/**
 * Entity service interface
 */
public interface EntityService {
    /**
     * Save entity
     *
     * @param entity Entity
     * @param <T>    Entity class
     * @return Saved entity
     */
    <T extends AbstractEntity> T save(T entity);

    /**
     * Get list result by query
     *
     * @param queryString Query string
     * @param parameters  Query parameters
     * @return Single result (null if there is no result)
     */
    <T extends AbstractEntity> List getListResultByQuery(String queryString, Class<T> classType, QueryParameter... parameters);

    /**
     * Get single result by query
     *
     * @param queryString Query string
     * @param parameters  Query parameters
     * @return Single result (null if there is no result)
     */
    <T extends AbstractEntity> T getSingleResultByQuery(String queryString, Class<T> classType, QueryParameter... parameters);
}
