package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.exceptions.UnsupportedImportCollectionException;
import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.entities.UniqueCodeEntity;
import org.mercuriusframework.exceptions.UniqueCodeEntityAbsenceException;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Multi values code entity import bean
 */
@Service("multiUniqueCodeImportBean")
public class MultiUniqueCodeImportBean implements ValueImportBean {

    /**
     * Constants
     */
    private static final String CODES_SPLITTER = ";";

    /**
     * Unique code entity service
     */
    @Autowired
    @Qualifier("uniqueCodeEntityService")
    private UniqueCodeEntityService uniqueCodeEntityService;


    /**
     * Find value by string
     * @param value     String value
     * @param setMethod Set method
     * @return Found object
     */
    @Override
    public Object findValueByString(String value, Method setMethod) {
        Class classType = setMethod.getParameterTypes()[0];
        /** Set */
        if (classType.isAssignableFrom(Set.class)) {
            Set<UniqueCodeEntity> result = new HashSet<>();
            Class entityClass = getEntityClass(setMethod);
            setDataToCollection(result, value, entityClass);
            return result;
        }
        /** List */
        if (classType.isAssignableFrom(List.class)) {
            List<UniqueCodeEntity> result = new ArrayList<>();
            Class entityClass = getEntityClass(setMethod);
            setDataToCollection(result, value, entityClass);
            return result;
        }
        throw new UnsupportedImportCollectionException(classType.getName());
    }

    /**
     * Set data to collection
     * @param collection Collection
     * @param rawValue Raw value
     * @param entityClass Entity class
     */
    private void setDataToCollection(Collection<UniqueCodeEntity> collection, String rawValue, Class entityClass) {
        String entityCodes[] = rawValue.split(CODES_SPLITTER);
        for (String entityCode : entityCodes) {
            UniqueCodeEntity uniqueCodeEntity = uniqueCodeEntityService.getEntityByCode(entityCode.trim(), entityClass);
            if (uniqueCodeEntity != null) {
                collection.add(uniqueCodeEntity);
            } else {
                throw new UniqueCodeEntityAbsenceException(entityClass.getName(), entityCode.trim());
            }
        }
    }

    /**
     * Get entity class
     * @param setMethod Set method
     * @return Entity class
     */
    private Class getEntityClass(Method setMethod) {
        String signature = setMethod.toGenericString();
        String className = signature.substring(signature.indexOf("<") + 1, signature.lastIndexOf(">"));
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }
}
