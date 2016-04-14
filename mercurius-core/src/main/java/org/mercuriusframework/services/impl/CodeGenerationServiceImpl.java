package org.mercuriusframework.services.impl;

import org.mercuriusframework.dto.QueryParameter;
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
        while (!existCode) {
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
}
