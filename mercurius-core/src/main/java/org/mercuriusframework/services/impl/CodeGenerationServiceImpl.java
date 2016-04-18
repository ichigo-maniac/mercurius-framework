package org.mercuriusframework.services.impl;

import org.mercuriusframework.dto.QueryParameter;
import org.mercuriusframework.entities.Catalog;
import org.mercuriusframework.entities.CatalogUniqueCodeEntity;
import org.mercuriusframework.entities.UniqueCodeEntity;
import org.mercuriusframework.services.CodeGenerationService;
import org.mercuriusframework.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Code generation service
 */
@Service("codeGenerationService")
public class CodeGenerationServiceImpl implements CodeGenerationService {

    /**
     * Entity service
     */
    @Autowired
    private EntityService entityService;

    /**
     * Generate unique code
     * @param type Entity type class
     * @param <T> Entity type class
     * @return Unique code
     */
    @Override
    public <T extends UniqueCodeEntity> String generateUniqueCode(Class<T> type) {
        Class classValue = getSuperUniqueCodeClass(type);
        String tempCode = classValue.getSimpleName().toLowerCase() + new Date().getTime();
        boolean existCode = existUniqueCode(classValue, tempCode);
        while (existCode) {
            tempCode = classValue.getSimpleName().toLowerCase() + new Date().getTime();
            existCode = existUniqueCode(classValue, tempCode);
        }
        return tempCode;
    }

    /**
     * Check unique code existence
     * @param type Entity type class
     * @param codeValue Code value
     * @param <T> Entity type class
     * @return Check result
     */
    public <T extends UniqueCodeEntity> boolean existUniqueCode(Class<T> type, String codeValue) {
        Class classValue = getSuperUniqueCodeClass(type);
        Long entitiesCount = entityService.getSingleResultByQuery("SELECT count(entity) FROM " + classValue.getSimpleName() + " as entity " +
                "WHERE entity.code = :entityCode",  Long.class, new QueryParameter("entityCode", codeValue));
        return !entitiesCount.equals(0l);
    }

    /**
     * Check unique code existence except one entity
     * @param type Entity type class
     * @param codeValue Code value
     * @param entityId Entity id
     * @param <T> Entity type class
     * @return Check result
     */
    @Override
    public <T extends UniqueCodeEntity> boolean existUniqueCodeExceptOne(Class<T> type, String codeValue, Long entityId) {
        Class classValue = getSuperUniqueCodeClass(type);
        Long entitiesCount = entityService.getSingleResultByQuery("SELECT count(entity) FROM " + classValue.getSimpleName() + " as entity " +
                "WHERE entity.code = :entityCode AND entity.id <> :entityId",  Long.class,
                new QueryParameter("entityCode", codeValue),
                new QueryParameter("entityId", entityId));
        return !entitiesCount.equals(0l);
    }

    /**
     * Get UniqueCodeEntity super class
     * @param currentClass Current entity class
     * @param <T> Current entity class
     * @return Super class
     */
    public <T extends UniqueCodeEntity> Class getSuperUniqueCodeClass(Class<T> currentClass) {
        Class currentClassValue = currentClass;
        Class superClass = currentClass.getSuperclass();
        while (!superClass.equals(UniqueCodeEntity.class)) {
            currentClassValue = superClass;
            superClass = superClass.getSuperclass();
        }
        return currentClassValue;
    }

    /**
     * Generate catalog unique code
     *
     * @param type    Entity type class
     * @param catalog Catalog
     * @return Unique code
     */
    @Override
    public <T extends CatalogUniqueCodeEntity> String generateCatalogUniqueCode(Class<T> type, Catalog catalog) {
        Class classValue = getSuperCatalogUniqueCodeClass(type);
        String tempCode = classValue.getSimpleName().toLowerCase() + new Date().getTime();
        boolean existCode = existCatalogUniqueCode(classValue, tempCode, catalog);
        while (existCode) {
            tempCode = classValue.getSimpleName().toLowerCase() + new Date().getTime();
            existCode = existCatalogUniqueCode(classValue, tempCode, catalog);
        }
        return tempCode;
    }

    /**
     * Generate catalog unique code
     *
     * @param type        Entity type class
     * @param catalogCode Catalog code
     * @return Unique code
     */
    @Override
    public <T extends CatalogUniqueCodeEntity> String generateCatalogUniqueCode(Class<T> type, String catalogCode) {
        Class classValue = getSuperCatalogUniqueCodeClass(type);
        String tempCode = classValue.getSimpleName().toLowerCase() + new Date().getTime();
        boolean existCode = existCatalogUniqueCode(classValue, tempCode, catalogCode);
        while (existCode) {
            tempCode = classValue.getSimpleName().toLowerCase() + new Date().getTime();
            existCode = existCatalogUniqueCode(classValue, tempCode, catalogCode);
        }
        return tempCode;
    }

    /**
     * Check catalog unique code existence
     *
     * @param type      Entity type class
     * @param codeValue Code value
     * @param catalog   Catalog
     * @return Check result
     */
    @Override
    public <T extends CatalogUniqueCodeEntity> boolean existCatalogUniqueCode(Class<T> type, String codeValue, Catalog catalog) {
        Class classValue = getSuperCatalogUniqueCodeClass(type);
        Long entitiesCount = entityService.getSingleResultByQuery("SELECT count(entity) FROM " + classValue.getSimpleName() + " as entity " +
                "WHERE entity.code = :entityCode AND entity.catalog = :catalog",  Long.class,
                new QueryParameter("entityCode", codeValue), new QueryParameter("catalog", catalog));
        return !entitiesCount.equals(0l);
    }

    /**
     * Check catalog unique code existence
     *
     * @param type        Entity type class
     * @param codeValue   Code value
     * @param catalogCode Catalog code
     * @return Check result
     */
    @Override
    public <T extends CatalogUniqueCodeEntity> boolean existCatalogUniqueCode(Class<T> type, String codeValue, String catalogCode) {
        Class classValue = getSuperCatalogUniqueCodeClass(type);
        Long entitiesCount = entityService.getSingleResultByQuery("SELECT count(entity) FROM " + classValue.getSimpleName() + " as entity " +
                "WHERE entity.code = :entityCode AND entity.catalog.code = :catalogCode",
                Long.class, new QueryParameter("entityCode", codeValue), new QueryParameter("catalogCode", catalogCode));
        return !entitiesCount.equals(0l);
    }

    /**
     * Check catalog unique code existence except one entity
     *
     * @param type      Entity type class
     * @param codeValue Code value
     * @param catalog   Catalog
     * @param entityId  Entity id
     * @return Check result
     */
    @Override
    public <T extends CatalogUniqueCodeEntity> boolean existCatalogUniqueCodeExceptOne(Class<T> type, String codeValue, Catalog catalog, Long entityId) {
        Class classValue = getSuperCatalogUniqueCodeClass(type);
        Long entitiesCount = entityService.getSingleResultByQuery("SELECT count(entity) FROM " + classValue.getSimpleName() + " as entity " +
                        "WHERE entity.code = :entityCode AND entity.catalog = :catalog AND entity.id <> :entityId",  Long.class,
                new QueryParameter("entityCode", codeValue), new QueryParameter("catalog", catalog), new QueryParameter("entityId", entityId));
        return !entitiesCount.equals(0l);
    }

    /**
     * Check catalog unique code existence except one entity
     *
     * @param type        Entity type class
     * @param codeValue   Code value
     * @param catalogCode Catalog code
     * @param entityId    Entity id
     * @return Check result
     */
    @Override
    public <T extends CatalogUniqueCodeEntity> boolean existCatalogUniqueCodeExceptOne(Class<T> type, String codeValue, String catalogCode, Long entityId) {
        Class classValue = getSuperCatalogUniqueCodeClass(type);
        Long entitiesCount = entityService.getSingleResultByQuery("SELECT count(entity) FROM " + classValue.getSimpleName() + " as entity " +
                        "WHERE entity.code = :entityCode AND entity.catalog.code = :catalogCode AND entity.id <> :entityId",
                Long.class, new QueryParameter("entityCode", codeValue), new QueryParameter("catalogCode", catalogCode), new QueryParameter("entityId", entityId));
        return !entitiesCount.equals(0l);
    }

    /**
     * Get CatalogUniqueCodeEntity super class
     *
     * @param currentClass Current entity class
     * @return Super class
     */
    @Override
    public <T extends CatalogUniqueCodeEntity> Class getSuperCatalogUniqueCodeClass(Class<T> currentClass) {
        Class currentClassValue = currentClass;
        Class superClass = currentClass.getSuperclass();
        while (!superClass.equals(CatalogUniqueCodeEntity.class)) {
            currentClassValue = superClass;
            superClass = superClass.getSuperclass();
        }
        return currentClassValue;
    }
}
