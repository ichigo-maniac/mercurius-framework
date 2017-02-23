package org.mercuriusframework.services.impl;

import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.CatalogUniqueCodeEntity;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.CodeGenerationService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service("catalogUniqueCodeEntityService")
public class CatalogUniqueCodeEntityServiceImpl implements CatalogUniqueCodeEntityService {
    /**
     * Entity manager
     */
    @PersistenceContext
    protected EntityManager entityManager;
    /**
     * Entity service
     */
    @Autowired
    private EntityService entityService;
    /**
     * Code generation service
     */
    @Autowired
    private CodeGenerationService codeGenerationService;
    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Get catalog unique code entity by code and catalog
     * @param code    Entity code
     * @param catalog Catalog
     * @param clazz   Entity class
     * @param fetchFields Fetch fields
     * @return Catalog unique code entity
     */
    @Override
    public <T extends CatalogUniqueCodeEntity> T getEntityByCodeAndCatalog(String code, CatalogEntity catalog, Class<T> clazz, String... fetchFields) {
        try {
            Class classValue = codeGenerationService.getSuperCatalogUniqueCodeClass(clazz);

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(classValue);
            Root<T> root = criteriaQuery.from(classValue);
            for (String fetchField : fetchFields) {
                root.fetch(fetchField, JoinType.LEFT);
            }
            criteriaQuery = criteriaQuery.select(root).where(builder.equal(root.get(CatalogUniqueCodeEntity.CODE), code),
                    builder.and(builder.equal(root.get(CatalogUniqueCodeEntity.CATALOG), catalog)));
            TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

    /**
     * Get catalog unique code entity by code 1and catalog code
     * @param code        Entity code
     * @param catalogCode Catalog code
     * @param clazz       Entity class
     * @param fetchFields Fetch fields
     * @return Catalog unique code entity
     */
    @Override
    public <T extends CatalogUniqueCodeEntity> T getEntityByCodeAndCatalogCode(String code, String catalogCode, Class<T> clazz, String... fetchFields) {
        try {
            Class classValue = codeGenerationService.getSuperCatalogUniqueCodeClass(clazz);

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(classValue);
            Root<T> root = criteriaQuery.from(classValue);
            for (String fetchField : fetchFields) {
                root.fetch(fetchField, JoinType.LEFT);
            }
            criteriaQuery = criteriaQuery.select(root).where(builder.equal(root.get(CatalogUniqueCodeEntity.CODE), code),
                    builder.and(builder.equal(root.get(CatalogUniqueCodeEntity.CATALOG),
                            uniqueCodeEntityService.getEntityByCode(catalogCode, CatalogEntity.class))));
            TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }
}
