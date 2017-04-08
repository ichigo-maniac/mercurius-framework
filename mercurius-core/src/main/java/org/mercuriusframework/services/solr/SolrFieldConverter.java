package org.mercuriusframework.services.solr;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.entities.SolrIndexFieldEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Solr field converter interface
 */
public interface SolrFieldConverter {

    /**
     * Logger
     */
    Logger LOGGER = LogManager.getRootLogger();

    /**
     * Constants
     */
     String GET_METHOD_PREFIX = "get";


    /**
     * Convert field
     * @param solrIndexField Solr index field
     * @param indexObject Index entity object
     * @return Converted value
     */
    Object convertField(SolrIndexFieldEntity solrIndexField, AbstractEntity indexObject);

    /**
     * Get field value
     * @param entityObject Entity object
     * @param fieldName Field name
     * @return Field value
     */
    default Object getFieldValue(AbstractEntity entityObject, String fieldName) {
        try {
            Method getterMethod = entityObject.getClass().getMethod(GET_METHOD_PREFIX + StringUtils.capitalize(fieldName));
            return getterMethod.invoke(entityObject);
        } catch (NoSuchMethodException exception) {
            exception.printStackTrace();
            LOGGER.error(exception);
            return null;
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
            LOGGER.error(exception);
            return null;
        } catch (InvocationTargetException exception) {
            exception.printStackTrace();
            LOGGER.error(exception);
            return null;
        }
    }
}
