package org.mercuriusframework.services.impl;

import org.mercuriusframework.dto.QueryParameter;
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
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        for (String fetchField : fetchFields) {
            root.fetch(fetchField, JoinType.LEFT);
        }
        criteriaQuery = criteriaQuery.select(root).where(builder.equal(root.get("uuid"), entityUUid));
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
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

}
