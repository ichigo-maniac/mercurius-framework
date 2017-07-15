package org.mercuriusframework.providers;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.services.EntityReflectionService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Entity message source provider
 */
@Service("entityMessageSourceProvider")
public class EntityMessageSourceProvider implements ApplicationContextAware {

    /**
     * Message source
     */
    private static MessageSource messageSource;
    /**
     * Annotation service
     */
    private static EntityReflectionService entityReflectionService;

    /**
     * Get entity message
     * @param entityName Entity name
     * @param entityProperty Entity property
     * @return Message
     */
    public static String getMessage(String entityName, String entityProperty) {
        try {
            String entityMessage = getEntityMessage(entityName, entityProperty);
            if (entityMessage != null) {
                return entityMessage;
            } else {
                return "[" + MercuriusConstants.LOCALIZATION.ENTITY_PREFIX + entityName + MercuriusConstants.LOCALIZATION.ENTITY_PROPERTY_SUFFIX + entityProperty + "]";
            }
        } catch (NoSuchMessageException e) {
            return "[" + MercuriusConstants.LOCALIZATION.ENTITY_PREFIX + entityName + MercuriusConstants.LOCALIZATION.ENTITY_PROPERTY_SUFFIX  + entityProperty + "]";
        }
    }


    /**
     * Get entity message
     * @param entityName Entity name
     * @param entityProperty Entity property
     * @param locale Locale
     * @return Message
     */
    public static String getMessage(String entityName, String entityProperty,  Locale locale) {
        try {
            String entityMessage = getEntityMessage(entityName, entityProperty, locale);
            if (entityMessage != null) {
                return entityMessage;
            } else {
                return "[" + MercuriusConstants.LOCALIZATION.ENTITY_PREFIX + entityName + MercuriusConstants.LOCALIZATION.ENTITY_PROPERTY_SUFFIX  + entityProperty + "]";
            }
        } catch (NoSuchMessageException e) {
            return "[" + MercuriusConstants.LOCALIZATION.ENTITY_PREFIX + entityName + MercuriusConstants.LOCALIZATION.ENTITY_PROPERTY_SUFFIX  + entityProperty + "]";
        }
    }

    /**
     * Get entity message
     * @param entityName Entity name
     * @param entityProperty Entity property
     * @return Message or null
     */
    private static String getEntityMessage(String entityName, String entityProperty) {
        Class entityClass = getEntityClass(entityName);
        if (entityClass == null) {
            return null;
        }
        /** Check subclasses */
        Class subClass = entityClass;
        while (!entityClass.equals(Object.class)) {
            try {
                String currentEntityName = entityReflectionService.getEntityNameByClass(subClass);
                currentEntityName = currentEntityName != null ? currentEntityName : subClass.getSimpleName();
                /** Load message */
                String message = messageSource.getMessage(MercuriusConstants.LOCALIZATION.ENTITY_PREFIX +
                                currentEntityName + MercuriusConstants.LOCALIZATION.ENTITY_PROPERTY_SUFFIX +  entityProperty,
                        new Object[]{}, LocaleContextHolder.getLocale());
                if (message != null) {
                    return message;
                } else {
                    subClass = subClass.getSuperclass();
                }
            } catch (NoSuchMessageException e) {
                subClass = subClass.getSuperclass();
            }
        }
        return null;
    }

    /**
     * Get entity message
     * @param entityName Entity name
     * @param entityProperty Entity property
     * @param locale Locale
     * @return Message or null
     */
    private static String getEntityMessage(String entityName, String entityProperty, Locale locale) {
        Class entityClass = getEntityClass(entityName);
        if (entityClass == null) {
            return null;
        }
        /** Check subclasses */
        Class subClass = entityClass;
        while (!entityClass.equals(Object.class)) {
            try {
                String currentEntityName = entityReflectionService.getEntityNameByClass(subClass);
                currentEntityName = currentEntityName != null ? currentEntityName : subClass.getSimpleName();
                /** Load message */
                String message = messageSource.getMessage(MercuriusConstants.LOCALIZATION.ENTITY_PREFIX + currentEntityName +
                                MercuriusConstants.LOCALIZATION.ENTITY_PROPERTY_SUFFIX + entityProperty,
                        new Object[]{}, locale);
                if (message != null) {
                    return message;
                } else {
                    subClass = subClass.getSuperclass();
                }
            } catch (NoSuchMessageException e) {
                subClass = subClass.getSuperclass();
            }
        }
        return null;
    }

    /**
     * Get entity class
     * @param entityName Entity name
     * @return Entity class
     */
    private static Class getEntityClass(String entityName) {
        if (entityName == null) {
            return null;
        }
        return entityReflectionService.getEntityClassByEntityName(entityName);
    }

    /**
     * Set application context
     * @param applicationContext Application context
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        messageSource = (MessageSource) applicationContext.getBean(MessageSource.class);
        entityReflectionService = (EntityReflectionService) applicationContext.getBean(EntityReflectionService.class);
    }
}
