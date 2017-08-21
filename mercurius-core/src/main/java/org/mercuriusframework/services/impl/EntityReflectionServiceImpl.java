package org.mercuriusframework.services.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.services.EntityReflectionService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Entity reflection service
 */
@Service("entityReflectionService")
public class EntityReflectionServiceImpl implements EntityReflectionService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Is entity class exist
     * @param entityName Entity name
     * @return Check result
     */
    @Override
    public boolean isEntityClassExist(String entityName) {
        return getEntityClassByEntityName(entityName) != null;
    }

    /**
     * Get entity class by entity name
     * @param entityName Entity name
     * @return Entity class
     */
    @Override
    public Class getEntityClassByEntityName(String entityName) {
        if (StringUtils.isEmpty(entityName)) {
            return null;
        }
        /** Base package */
        ClassPathScanningCandidateComponentProvider classProvider = new ClassPathScanningCandidateComponentProvider(false);
        classProvider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        for (BeanDefinition beanDefinition :classProvider.findCandidateComponents(MercuriusConstants.COMMON.MERCURIUS_BASE_PACKAGE)) {
            try {
                Class entityClass = Class.forName(beanDefinition.getBeanClassName());
                Entity entityAnnotation = (Entity) entityClass.getAnnotation(Entity.class);
                if (entityAnnotation.name() != null && entityAnnotation.name().equals(entityName)) {
                    return entityClass;
                }
            } catch (ClassNotFoundException exception) {
                LOGGER.error(exception);
            } catch (Exception exception) {
                LOGGER.error(exception);
            }
        }
        /** User package */
        return null;
    }

    /**
     * Get entity name by class
     * @param entityClass Entity class
     * @return Entity name or null
     */
    @Override
    public String getEntityNameByClass(Class entityClass) {
        if (entityClass == null) {
            return null;
        }
        Entity entityAnnotation = (Entity) entityClass.getAnnotation(Entity.class);
        if (entityAnnotation != null) {
            return entityAnnotation.name();
        } else {
            return null;
        }
    }

    /**
     * Get field
     * @param type      Class
     * @param fieldName Field name
     * @return Field
     */
    @Override
    public Field getField(Class type, String fieldName) throws NoSuchFieldException {
        Class currentClass = type;
        while (currentClass != null && currentClass != Object.class) {
            try {
                Field field = currentClass.getDeclaredField(fieldName);
                if (field != null) {
                    return field;
                }
            } catch (NoSuchFieldException e) {
            } finally {
                currentClass = currentClass.getSuperclass();
            }
        }
        throw new NoSuchFieldException(fieldName);
    }

    /**
     * Get field class
     * @param type      Class
     * @param fieldName Field name
     * @return Class
     */
    @Override
    public Class getFieldClass(Class type, String fieldName) {
        try {
            Field field = getField(type, fieldName);
            return getFieldClass(field);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    /**
     * Get field class
     * @param field Field
     * @return Class
     */
    @Override
    public Class getFieldClass(Field field) {
        if (Collection.class.isAssignableFrom(field.getType())) {
            return getCollectionTypeClass(field);
        } else {
            return field.getType();
        }
    }


    /**
     * Get collection type class
     * @param field Field
     * @return
     */
    private Class getCollectionTypeClass(Field field) {
        Class valueClass = field.getType();
        if (!valueClass.isAssignableFrom(Set.class) && !valueClass.isAssignableFrom(List.class)) {
            return null;
        }
        String signature = field.toGenericString();
        String className = signature.substring(signature.indexOf("<") + 1, signature.lastIndexOf(">"));
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }
}
