package org.mercuriusframework.services.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.services.AnnotationService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.Set;

/**
 * Annotation service
 */
@Service("annotationService")
public class AnnotationServiceImpl implements AnnotationService {

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
}
