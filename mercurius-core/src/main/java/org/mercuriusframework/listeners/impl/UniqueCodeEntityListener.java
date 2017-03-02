package org.mercuriusframework.listeners.impl;

import org.mercuriusframework.entities.UniqueCodeEntity;
import org.mercuriusframework.exceptions.MandatoryParameterNullException;
import org.mercuriusframework.exceptions.UniqueCodeConstraintViolationException;
import org.mercuriusframework.listeners.PrePersistEntityListener;
import org.mercuriusframework.listeners.PreUpdateEntityListener;
import org.mercuriusframework.services.CodeGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Unique code entity listener
 */
@Service("uniqueCodeEntityListener")
public class UniqueCodeEntityListener implements PrePersistEntityListener<UniqueCodeEntity>, PreUpdateEntityListener<UniqueCodeEntity> {
    /**
     * Code generation service
     */
    @Autowired
    @Qualifier("codeGenerationService")
    private CodeGenerationService codeGenerationService;

    /**
     * "Pre-persist" handler
     *
     * @param entityObject Entity object
     */
    @Override
    public void prePersist(UniqueCodeEntity entityObject) {
        if (entityObject.getName() == null) {
            throw new MandatoryParameterNullException(entityObject.getClass(), "name");
        }
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
    public void preUpdate(UniqueCodeEntity entityObject) {
        if (entityObject.getName() == null) {
            throw new MandatoryParameterNullException(entityObject.getClass(), "name");
        }
        if (entityObject.getCode() == null) {
            throw new MandatoryParameterNullException(entityObject.getClass(), "code");
        } else {
            boolean checkResult = codeGenerationService.existUniqueCodeExceptOne(entityObject.getClass(), entityObject.getCode(), entityObject.getUuid());
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
    public Class<UniqueCodeEntity> getEntityType() {
        return UniqueCodeEntity.class;
    }
}
