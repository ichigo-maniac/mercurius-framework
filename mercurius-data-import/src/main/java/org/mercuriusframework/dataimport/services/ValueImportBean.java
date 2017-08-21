package org.mercuriusframework.dataimport.services;

import java.lang.reflect.Field;

/**
 *  Value import bean service
 */
public interface ValueImportBean {

    /**
     * Get value by string
     * @param value String value
     * @param field Field
     * @param sourceObject Source object
     * @return Found object
     */
    Object getValueByString(String value, Field field, Object sourceObject);
}
