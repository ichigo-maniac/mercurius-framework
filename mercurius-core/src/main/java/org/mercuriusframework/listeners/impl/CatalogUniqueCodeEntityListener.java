package org.mercuriusframework.listeners.impl;

import org.mercuriusframework.entities.CatalogUniqueCodeEntity;
import org.mercuriusframework.exceptions.MandatoryParameterNullException;
import org.mercuriusframework.exceptions.UniqueCodeConstraintViolationException;
import org.mercuriusframework.listeners.PrePersistEntityListener;
import org.mercuriusframework.listeners.PreUpdateEntityListener;
import org.mercuriusframework.services.CodeGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Catalog unique code entity listener
 */
@Service("catalogUniqueCodeEntityListener")
public class CatalogUniqueCodeEntityListener implements PrePersistEntityListener<CatalogUniqueCodeEntity>, PreUpdateEntityListener<CatalogUniqueCodeEntity> {
    /**
     * Code generation service
     */
    @Autowired
    private CodeGenerationService codeGenerationService;

    /**
     * "Pre-persist" handler
     *
     * @param entityObject Entity object
     */
    @Override
    public void prePersist(CatalogUniqueCodeEntity entityObject) {
        if (entityObject.getName() == null) {
            throw new MandatoryParameterNullException(entityObject.getClass(), "name");
        }
        if (entityObject.getCatalog() == null) {
            throw new MandatoryParameterNullException(entityObject.getClass(), "catalog");
        }
        if (entityObject.getCode() == null) {
            entityObject.setCode(codeGenerationService.generateCatalogUniqueCode(entityObject.getClass(), entityObject.getCatalog()));
        } else {
            boolean checkResult = codeGenerationService.existCatalogUniqueCode(entityObject.getClass(), entityObject.getCode(), entityObject.getCatalog());
            if (checkResult) {
                throw new UniqueCodeConstraintViolationException(
                        codeGenerationService.getSuperCatalogUniqueCodeClass(entityObject.getClass()), entityObject.getCode(), entityObject.getCatalog().getCode());
            }
        }
    }

    /**
     * "Pre-update" handler
     *
     * @param entityObject Entity object
     */
    @Override
    public void preUpdate(CatalogUniqueCodeEntity entityObject) {
        if (entityObject.getName() == null) {
            throw new MandatoryParameterNullException(entityObject.getClass(), "name");
        }
        if (entityObject.getCatalog() == null) {
            throw new MandatoryParameterNullException(entityObject.getClass(), "catalog");
        }
        if (entityObject.getCode() == null) {
            throw new MandatoryParameterNullException(entityObject.getClass(), "code");
        } else {
            boolean checkResult = codeGenerationService.existCatalogUniqueCodeExceptOne(entityObject.getClass(), entityObject.getCode(), entityObject.getCatalog(), entityObject.getUuid());
            if (checkResult) {
                throw new UniqueCodeConstraintViolationException(
                        codeGenerationService.getSuperCatalogUniqueCodeClass(entityObject.getClass()), entityObject.getCode(), entityObject.getCatalog().getCode());
            }
        }
    }

    /**
     * Get entity type
     *
     * @return Entity type
     */
    @Override
    public Class<CatalogUniqueCodeEntity> getEntityType() {
        return CatalogUniqueCodeEntity.class;
    }
}
