package org.mercuriusframework.services.impl;

import org.mercuriusframework.dto.QueryParameter;
import org.mercuriusframework.entities.UniqueCodeEntity;
import org.mercuriusframework.services.CodeGenerationService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Catalog unique code entity service
 */
@Service("uniqueCodeEntityService")
public class UniqueCodeEntityServiceImpl implements UniqueCodeEntityService {
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
     * Get unique code entity by code
     * @param code  Entity code
     * @param clazz Entity class
     * @return Unique code entity
     */
    @Override
    public <T extends UniqueCodeEntity> T getEntityByCode(String code, Class<T> clazz) {
        Class classValue = codeGenerationService.getSuperUniqueCodeClass(clazz);
        return entityService.getSingleResultByQuery("SELECT entity FROM " + classValue.getSimpleName() + " as entity " +
                "WHERE entity.code = :entityCode", clazz, new QueryParameter("entityCode", code));
    }
}
