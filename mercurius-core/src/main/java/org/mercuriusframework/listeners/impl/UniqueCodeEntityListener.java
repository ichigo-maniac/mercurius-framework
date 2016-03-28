package org.mercuriusframework.listeners.impl;

import org.mercuriusframework.entities.Catalog;
import org.mercuriusframework.exceptions.NullUniqueCodeException;
import org.mercuriusframework.exceptions.UniqueCodeConstraintViolationException;
import org.mercuriusframework.listeners.PrePersistEntityListener;
import org.mercuriusframework.listeners.PreUpdateEntityListener;
import org.mercuriusframework.services.CodeGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Unique code entity listener
 */
@Service("uniqueCodeEntityListener")
public class UniqueCodeEntityListener implements PrePersistEntityListener<Catalog>, PreUpdateEntityListener<Catalog> {
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
    public void prePersist(Catalog entityObject) {
        if (entityObject.getCode() == null) {
            entityObject.setCode(codeGenerationService.generateUniqueCode(entityObject.getClass()));
        } else {
            boolean checkResult = codeGenerationService.existUniqueCode(entityObject.getClass(), entityObject.getCode());
            if (checkResult) {
                throw new UniqueCodeConstraintViolationException(codeGenerationService.getSuperUniqueCodeClass(entityObject.getClass()), entityObject.getCode());
            }
        }
    }

    /**
     * "Pre-update" handler
     *
     * @param entityObject Entity object
     */
    @Override
    public void preUpdate(Catalog entityObject) {
        if (entityObject.getCode() == null) {
            throw new NullUniqueCodeException(entityObject.getClass());
        } else {
            boolean checkResult = codeGenerationService.existUniqueCodeExceptOne(entityObject.getClass(), entityObject.getCode(), entityObject.getId());
            if (checkResult) {
                throw new UniqueCodeConstraintViolationException(codeGenerationService.getSuperUniqueCodeClass(entityObject.getClass()), entityObject.getCode());
            }
        }
    }

    /**
     * Get entity type
     *
     * @return Entity type
     */
    @Override
    public Class<Catalog> getEntityType() {
        return Catalog.class;
    }
}
