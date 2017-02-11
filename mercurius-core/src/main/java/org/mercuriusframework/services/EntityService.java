package org.mercuriusframework.services;

import org.mercuriusframework.dto.QueryParameter;
import org.mercuriusframework.entities.AbstractEntity;

import java.util.List;

/**
 * Entity service interface
 */
public interface EntityService {
    /**
     * Find entity by uuid
     * @param entityUUid Entity uuid
     * @param entityClass Entity class
     * @param fetchFields Fetch fields
     * @param <T> Entity Class
     * @return Entity
     */
    <T extends AbstractEntity> T findByUuid(String entityUUid, Class<T> entityClass, String... fetchFields);
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
    <T> List getListResultByQuery(String queryString, Class<T> classType, QueryParameter... parameters);

    /**
     * Get single result by query
     *
     * @param queryString Query string
     * @param parameters  Query parameters
     * @return Single result (null if there is no result)
     */
    <T> T getSingleResultByQuery(String queryString, Class<T> classType, QueryParameter... parameters);
}
