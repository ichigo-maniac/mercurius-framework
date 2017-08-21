package org.mercuriusframework.services;

import java.lang.reflect.Field;

/**
 * Entity reflection service interface
 */
public interface EntityReflectionService {
    /**
     * Is entity class exist
     * @param entityName Entity name
     * @return Check result
     */
    boolean isEntityClassExist(String entityName);

    /**
     * Get entity class by entity name
     * @param entityName Entity name
     * @return Entity class
     */
    Class getEntityClassByEntityName(String entityName);

    /**
     * Get entity name by class
     * @param entityClass Entity class
     * @return Entity name or null
     */
    String getEntityNameByClass(Class entityClass);

    /**
     * Get field
     * @param type Class
     * @param fieldName Field name
     * @return Field
     */
    Field getField(Class type, String fieldName) throws NoSuchFieldException;

    /**
     * Get field class
     * @param type Class
     * @param fieldName Field name
     * @return Class
     */
    Class getFieldClass(Class type, String fieldName);

    /**
     * Get field class
     * @param field Field
     * @return Class
     */
    Class getFieldClass(Field field);
}
