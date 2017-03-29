package org.mercuriusframework.services.impl;

import org.mercuriusframework.entities.UniqueCodeEntity;
import org.mercuriusframework.services.CodeGenerationService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

/**
 * Catalog unique code entity service
 */
@Service("uniqueCodeEntityService")
public class UniqueCodeEntityServiceImpl implements UniqueCodeEntityService {

    /**
     * Entity manager
    */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Code generation service
     */
    @Autowired
    @Qualifier("codeGenerationService")
    protected CodeGenerationService codeGenerationService;

    /**
     * Get unique code entity by code
     * @param code  Entity code
     * @param clazz Entity class
     * @return Unique code entity
     */
    @Override
    public <T extends UniqueCodeEntity> T getEntityByCode(String code, Class<T> clazz) {
        try {
            Class classValue = codeGenerationService.getSuperUniqueCodeClass(clazz);

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(classValue);
            Root<T> root = criteriaQuery.from(classValue);
            criteriaQuery = criteriaQuery.select(root).where(builder.equal(root.get(UniqueCodeEntity.CODE), code));
            TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

    /**
     * Get unique code entity by code
     *
     * @param code        Entity code
     * @param clazz       Entity class
     * @param fetchFields Fetch fields
     * @return Unique code entity
     */
    @Override
    public <T extends UniqueCodeEntity> T getEntityByCodeWithFetch(String code, Class<T> clazz, String... fetchFields) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
            Root<T> root = criteriaQuery.from(clazz);
            for (String fetchField : fetchFields) {
                root.fetch(fetchField, JoinType.LEFT);
            }
            criteriaQuery = criteriaQuery.select(root).where(builder.equal(root.get(UniqueCodeEntity.CODE), code));
            TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }
}
