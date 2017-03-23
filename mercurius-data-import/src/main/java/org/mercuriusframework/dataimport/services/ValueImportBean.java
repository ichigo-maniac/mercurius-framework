package org.mercuriusframework.dataimport.services;

import org.mercuriusframework.entities.AbstractEntity;

/**
 *  Value import bean service
 */
public interface ValueImportBean<T extends AbstractEntity> {

    /**
     * Find value by string
     * @param value String value
     * @param classType Class type
     * @return Found object
     */
    Object findValueByString(String value, Class<T> classType);
}
