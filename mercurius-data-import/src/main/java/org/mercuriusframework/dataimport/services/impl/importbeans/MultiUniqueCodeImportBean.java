package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.exceptions.UnsupportedImportCollectionException;
import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.entities.UniqueCodeEntity;
import org.mercuriusframework.exceptions.UniqueCodeEntityAbsenceException;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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
     * Get value by string
     * @param value     String value
     * @param field        Field
     * @param sourceObject Source object
     * @return Found object
     */
    @Override
    public Object getValueByString(String value, Field field, Object sourceObject) {
        Class classType = field.getType();
        /** Set */
        if (classType.isAssignableFrom(Set.class)) {
            Set<UniqueCodeEntity> result = new HashSet<>();
            Class entityClass = getCollectionTypeClass(field);
            setDataToCollection(result, value, entityClass);
            return result;
        }
        /** List */
        if (classType.isAssignableFrom(List.class)) {
            List<UniqueCodeEntity> result = new ArrayList<>();
            Class entityClass = getCollectionTypeClass(field);
            setDataToCollection(result, value, entityClass);
            return result;
        }
        if (!classType.isAssignableFrom(Collection.class)) {
            List<UniqueCodeEntity> result = new ArrayList<>();
            Class entityClass = field.getType();
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

}
