package org.mercuriusframework.services;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.services.query.PageableResult;
import org.mercuriusframework.services.query.QueryParameter;
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

    /**
     * Get pageable result by query
     * @param queryString Query string
     * @param totalCountQueryString Total count query string
     * @param currentPage Current page (count from 0)
     * @param pageSize Page size (entries on the page)
     * @param classType Class type
     * @param parameters Query parameters
     * @param <T> Type
     * @return Pageable result
     */
    <T> PageableResult<T> getPageableResultByQueries(String queryString, String totalCountQueryString, Integer currentPage, Integer pageSize,
                                                     Class<T> classType, QueryParameter... parameters);

    /**
     * Get pageable result by query
     * @param converter Converter
     * @param loadOptions Load options
     * @param queryString Query string
     * @param totalCountQueryString Total count query string
     * @param currentPage Current page (count from 0)
     * @param pageSize Page size (entries on the page)
     * @param classType Class type
     * @param parameters Query parameters
     * @param <T> Type
     * @return Pageable result
     */
    <T, RESULT> PageableResult<T> getPageableResultByQueries(Converter<T, RESULT> converter, LoadOptions[] loadOptions,
            String queryString, String totalCountQueryString, Integer currentPage, Integer pageSize,
            Class<T> classType, QueryParameter... parameters);
}
