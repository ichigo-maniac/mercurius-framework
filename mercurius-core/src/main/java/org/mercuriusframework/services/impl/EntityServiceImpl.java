package org.mercuriusframework.services.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.services.query.ConvertiblePageableResult;
import org.mercuriusframework.services.query.DefaultPageableResult;
import org.mercuriusframework.services.query.PageableResult;
import org.mercuriusframework.services.query.QueryParameter;
import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionOperations;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Entity service (save, update and delete entities)
 */
@Service("entityService")
public class EntityServiceImpl implements EntityService {

    /**
     * Entity manager
     */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Transaction operations
     */
    @Autowired
    private TransactionOperations transactionOperations;

    /**
     * Find entity by uuid
     * @param entityUUid  Entity uuid
     * @param entityClass Entity class
     * @param fetchFields  Fetch fields
     * @return Entity
     */
    public <T extends AbstractEntity> T findByUuid(String entityUUid, Class<T> entityClass, String... fetchFields) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
            Root<T> root = criteriaQuery.from(entityClass);
            for (String fetchField : fetchFields) {
                root.fetch(fetchField, JoinType.LEFT);
            }
            criteriaQuery = criteriaQuery.select(root).where(builder.equal(root.get(AbstractEntity.UUID), entityUUid));
            TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

    /**
     * Save entity
     *
     * @param entity Entity
     * @param <T>    Entity class
     * @return Saved entity
     */
    public <T extends AbstractEntity> T save(final T entity) {
        return transactionOperations.execute(new TransactionCallback<T>() {
            public T doInTransaction(TransactionStatus transactionStatus) {
                if (entity.getUuid() == null) {
                    entityManager.persist(entity);
                    return entity;
                } else {
                    return entityManager.merge(entity);
                }
            }
        });
    }

    /**
     * Delete entity by uuid
     * @param entityUuid  Entity uuid
     * @param entityClass Entity class
     */
    @Override
    public <T extends AbstractEntity> void delete(String entityUuid, Class<T> entityClass) {
        if (entityUuid == null) {
            return;
        }
        T entityObject = entityManager.getReference(entityClass, entityUuid);
        transactionOperations.execute(new TransactionCallback<T>() {
            public T doInTransaction(TransactionStatus transactionStatus) {
                entityManager.remove(entityObject);
                return null;
            }
        });
    }

    /**
     * Delete entity
     * @param entityObject Entity object
     */
    @Override
    public <T extends AbstractEntity> void delete(T entityObject) {
        if (entityObject.getUuid() != null) {
            delete(entityObject.getUuid(), entityObject.getClass());
        }
    }

    /**
     * Get list result by query
     *
     * @param queryString Query string
     * @param classType
     * @param parameters  Query parameters  @return Single result (null if there is no result)
     */
    public <T> List getListResultByQuery(String queryString, Class<T> classType, QueryParameter... parameters) {
        TypedQuery<T> query = entityManager.createQuery(queryString, classType);
        if (parameters != null) {
            for (QueryParameter parameter : parameters) {
                query.setParameter(parameter.getKey(), parameter.getValue());
            }
        }
        query.setFlushMode(FlushModeType.COMMIT);
        return query.getResultList();
    }

    /**
     * Get single result by query
     *
     * @param queryString Query string
     * @param parameters  Query parameters
     * @return Single result (null if there is no result)
     */
    public <T> T getSingleResultByQuery(String queryString, Class<T> classType, QueryParameter... parameters) {
        try {
            TypedQuery<T> query = entityManager.createQuery(queryString, classType);
            if (parameters != null) {
                for (QueryParameter parameter : parameters) {
                    query.setParameter(parameter.getKey(), parameter.getValue());
                }
            }
            query.setFlushMode(FlushModeType.COMMIT);
            return (T) query.getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

    /**
     * Get pageable result by query
     *
     * @param queryString           Query string
     * @param totalCountQueryString Total count query string
     * @param currentPage           Current page (count from 0)
     * @param pageSize              Page size (entries on the page)
     * @param classType             Class type
     * @param parameters            Query parameters
     * @return Pageable result
     */
    @Override
    public <T> PageableResult<T> getPageableResultByQueries(String queryString, String totalCountQueryString, Integer currentPage, Integer pageSize, Class<T> classType, QueryParameter... parameters) {
        Long totalCount = getSingleResultByQuery(totalCountQueryString, Long.class, parameters);
        Integer currentPageResult = calculateCurrentPage(pageSize, currentPage, totalCount);
        TypedQuery<T> query = entityManager.createQuery(queryString, classType);
        if (parameters != null) {
            for (QueryParameter parameter : parameters) {
                query.setParameter(parameter.getKey(), parameter.getValue());
            }
        }
        query.setFirstResult(currentPageResult * pageSize);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setMaxResults(pageSize);
        /** Create result */
        return new DefaultPageableResult<T>(totalCount.intValue(), currentPageResult,
                pageSize, getPagesCount(pageSize, totalCount), query.getResultList());
    }

    /**
     * Get pageable result by query
     *
     * @param converter             Converter
     * @param loadOptions           Load options
     * @param queryString           Query string
     * @param totalCountQueryString Total count query string
     * @param currentPage           Current page (count from 0)
     * @param pageSize              Page size (entries on the page)
     * @param classType             Class type
     * @param parameters            Query parameters
     * @return Pageable result
     */
    @Override
    public <T, RESULT> PageableResult<T> getPageableResultByQueries(Converter<T, RESULT> converter, LoadOptions[] loadOptions,
                                                                    String queryString, String totalCountQueryString, Integer currentPage, Integer pageSize,
                                                                    Class<T> classType, QueryParameter... parameters) {
        Long totalCount = getSingleResultByQuery(totalCountQueryString, Long.class, parameters);
        Integer currentPageResult = calculateCurrentPage(pageSize, currentPage, totalCount);
        TypedQuery<T> query = entityManager.createQuery(queryString, classType);
        if (parameters != null) {
            for (QueryParameter parameter : parameters) {
                query.setParameter(parameter.getKey(), parameter.getValue());
            }
        }
        query.setFirstResult(currentPageResult * pageSize);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setMaxResults(pageSize);
        /** Create result */
        return new ConvertiblePageableResult<T, RESULT>(totalCount.intValue(), currentPageResult,
                pageSize, getPagesCount(pageSize, totalCount), query.getResultList(), converter, loadOptions);
    }

    /**
     * Calculate current page
     * @param pageSize Page size
     * @param currentPage Current page
     * @param totalCount Total entries count
     * @return Current Page
     */
    private Integer calculateCurrentPage(Integer pageSize, Integer currentPage, Long totalCount) {
        if (currentPage == null || currentPage <= 0) {
            return 0;
        }
        Long lastPage = ((totalCount- 1) / pageSize);
        if (currentPage > lastPage) {
            return lastPage.intValue();
        } else {
            return currentPage;
        }
    }
    /**
     * Get pages count
     * @param pageSize Page size
     * @param totalCount Total entries count
     * @return Pages count
     */
    private Integer getPagesCount(Integer pageSize, Long totalCount) {
        if (totalCount == 0) {
            return 0;
        } else {
            return (int) ((totalCount - 1) / pageSize) + 1;
        }
    }

}
