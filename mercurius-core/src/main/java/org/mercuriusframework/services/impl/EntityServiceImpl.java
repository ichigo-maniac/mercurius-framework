package org.mercuriusframework.services.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.enums.CriteriaValueType;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.services.query.*;
import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionOperations;
import org.springframework.util.CollectionUtils;
import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Collections;
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
    protected TransactionOperations transactionOperations;

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
     * Find entities by uuid
     * @param entityUuids Entity uuids
     * @param entityClass Entity class
     * @param fetchFields Fetch fields
     * @return Entities
     */
    @Override
    public <T extends AbstractEntity> List<T> findByUuids(List<String> entityUuids, Class<T> entityClass, String... fetchFields) {
        if (CollectionUtils.isEmpty(entityUuids)) {
            return Collections.emptyList();
        }
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
            Root<T> root = criteriaQuery.from(entityClass);
            for (String fetchField : fetchFields) {
                root.fetch(fetchField, JoinType.LEFT);
            }
            criteriaQuery = criteriaQuery.select(root).where(root.get(AbstractEntity.UUID).in(entityUuids));
            TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getResultList();
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
     * Remove entity by uuid
     * @param entityUuid  Entity uuid
     * @param entityClass Entity class
     */
    @Override
    public <T extends AbstractEntity> void remove(String entityUuid, Class<T> entityClass) {
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
     * Remove entity
     * @param entityObject Entity object
     */
    @Override
    public <T extends AbstractEntity> void remove(T entityObject) {
        if (entityObject.getUuid() != null) {
            remove(entityObject.getUuid(), entityObject.getClass());
        }
    }

    /**
     * Remove all entities
     * @param entities Collection of entities
     */
    @Override
    public <T extends AbstractEntity> void removeAll(Collection<T> entities) {
        if (entities == null) {
            return;
        }
        transactionOperations.execute(new TransactionCallback<T>() {
            public T doInTransaction(TransactionStatus transactionStatus) {
                for (AbstractEntity entityObject : entities) {
                    entityManager.remove(entityManager.getReference(entityObject.getClass(), entityObject.getUuid()));
                }
                return null;
            }
        });
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
    public <T, RESULT> PageableResult<RESULT> getPageableResultByQueries(Converter<T, RESULT> converter, LoadOptions[] loadOptions,
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

    /**
     * Get list of entities by criteria
     * @param classType          Class type
     * @param fetchFields        Fetch fields
     * @param criteriaParameters Criteria parameters
     * @return List of entities
     */
    @Override
    public <T> List<T> getListResultByCriteria(Class<T> classType, String[] fetchFields, CriteriaParameter... criteriaParameters) {
        /** Create criteria query */
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(classType);
        Root<T> root = criteriaQuery.from(classType);
        criteriaQuery = criteriaQuery.select(root);
        for (String fetchField : fetchFields) {
            root.fetch(fetchField, JoinType.LEFT);
        }
        /** Build restrictions */
        if (criteriaParameters.length > 0) {
            Expression restrictionExpression = buildRestrictionExpression(root, criteriaParameters);
            if (restrictionExpression != null) {
                criteriaQuery = criteriaQuery.where(restrictionExpression);
            }
        }
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFlushMode(FlushModeType.COMMIT);
        /** Create result */
        return typedQuery.getResultList();
    }

    /**
     * Get pageable result by criteria
     * @param currentPage Current page (count from 0)
     * @param pageSize    Page size (entries on the page)
     * @param fetchFields Fetch fields
     * @param classType   Class type
     * @param criteriaParameters Criteria parameters
     * @return Pageable result
     */
    @Override
    public <T> PageableResult<T> getPageableResultByCriteria(Integer currentPage, Integer pageSize, String[] fetchFields,
                                                             Class<T> classType, CriteriaParameter... criteriaParameters) {
        Long totalCount = getCountByCriteria(classType, criteriaParameters);
        Integer currentPageResult = calculateCurrentPage(pageSize, currentPage, totalCount);
        /** Create criteria query */
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(classType);
        Root<T> root = criteriaQuery.from(classType);
        criteriaQuery = criteriaQuery.select(root);
        for (String fetchField : fetchFields) {
            root.fetch(fetchField, JoinType.LEFT);
        }
        /** Build restrictions */
        if (criteriaParameters.length > 0) {
            Expression restrictionExpression = buildRestrictionExpression(root, criteriaParameters);
            if (restrictionExpression != null) {
                criteriaQuery = criteriaQuery.where(restrictionExpression);
            }
        }
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(currentPageResult * pageSize);
        typedQuery.setFlushMode(FlushModeType.COMMIT);
        typedQuery.setMaxResults(pageSize);
        /** Create result */
        return new DefaultPageableResult<T>(totalCount.intValue(), currentPageResult,
                pageSize, getPagesCount(pageSize, totalCount), typedQuery.getResultList());
    }

    /**
     * Get entities count by criteria
     * @param classType Class type
     * @param criteriaParameters Criteria parameters
     * @return Entities count
     */
    @Override
    public <T> Long getCountByCriteria(Class<T> classType, CriteriaParameter... criteriaParameters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(classType);
        criteriaQuery = criteriaQuery.select(builder.count(root));
        /** Build restrictions */
        if (criteriaParameters.length > 0) {
            Expression restrictionExpression = buildRestrictionExpression(root, criteriaParameters);
            if (restrictionExpression != null) {
                criteriaQuery = criteriaQuery.where(restrictionExpression);
            }
        }
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    /**
     * Get pageable result by criteria
     * @param converter
     * @param loadOptions
     * @param currentPage Current page (count from 0)
     * @param pageSize    Page size (entries on the page)
     * @param fetchFields Fetch fields
     * @param classType   Class type
     * @param criteriaParameters Criteria parameters
     * @return Pageable result
     */
    @Override
    public <T, RESULT> PageableResult<RESULT> getPageableResultByCriteria(Converter<T, RESULT> converter, LoadOptions[] loadOptions,
                                                                     Integer currentPage, Integer pageSize, String[] fetchFields,
                                                                     Class<T> classType, CriteriaParameter... criteriaParameters) {
        Long totalCount = getCountByCriteria(classType, criteriaParameters);
        Integer currentPageResult = calculateCurrentPage(pageSize, currentPage, totalCount);
        /** Create criteria query */
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(classType);
        Root<T> root = criteriaQuery.from(classType);
        criteriaQuery = criteriaQuery.select(root);
        for (String fetchField : fetchFields) {
            root.fetch(fetchField, JoinType.LEFT);
        }
        /** Build restrictions */
        if (criteriaParameters.length > 0) {
            Expression restrictionExpression = buildRestrictionExpression(root, criteriaParameters);
            if (restrictionExpression != null) {
                criteriaQuery = criteriaQuery.where(restrictionExpression);
            }
        }
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(currentPageResult * pageSize);
        typedQuery.setFlushMode(FlushModeType.COMMIT);
        typedQuery.setMaxResults(pageSize);
        /** Create result */
        return new ConvertiblePageableResult<T, RESULT>(totalCount.intValue(), currentPageResult,
                pageSize, getPagesCount(pageSize, totalCount), typedQuery.getResultList(), converter, loadOptions);
    }

    /**
     * Build restriction expression
     * @param root Root
     * @param criteriaParameters Criteria parameters
     * @return Restriction expression
     */
    private Expression buildRestrictionExpression(Root root, CriteriaParameter[] criteriaParameters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        Expression expression = null;
        for (CriteriaParameter criteriaParameter : criteriaParameters) {
            Expression parameterExpression = null;
            if (criteriaParameter.getValues().length > 0) {
                for (CriteriaValue criteriaValue : criteriaParameter.getValues()) {
                    /** Create current value expression */
                    Expression currentValueExpression = null;
                    if (criteriaValue.getType() == CriteriaValueType.EQUALS) {
                        currentValueExpression = builder.equal(root.get(criteriaParameter.getProperty()), criteriaValue.getValue());
                    }
                    if (criteriaValue.getType() == CriteriaValueType.NOT_EQUALS) {
                        currentValueExpression = builder.notEqual(root.get(criteriaParameter.getProperty()), criteriaValue.getValue());
                    }
                    if (criteriaValue.getType() == CriteriaValueType.IN) {
                        if (criteriaValue.getValue() instanceof Collection && !((Collection)criteriaValue.getValue()).isEmpty()) {
                            currentValueExpression = root.get(criteriaParameter.getProperty()).in(criteriaValue.getValue());
                        }
                    }
                    /** Add current value expression */
                    if (parameterExpression != null) {
                        parameterExpression = builder.or(parameterExpression, currentValueExpression);
                    } else {
                        parameterExpression = currentValueExpression;
                    }
                }
            }
            /** Add parameter expression */
            if (parameterExpression != null) {
                if (expression != null) {
                    expression = builder.and(expression, parameterExpression);
                } else {
                    expression = parameterExpression;
                }
            }
        }
        return expression;
    }
}
