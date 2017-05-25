package org.mercuriusframework.services;

/**
 * Annotation service interface
 */
public interface AnnotationService {
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
}
