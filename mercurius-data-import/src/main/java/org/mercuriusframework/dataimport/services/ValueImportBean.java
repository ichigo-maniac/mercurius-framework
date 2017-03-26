package org.mercuriusframework.dataimport.services;

import java.lang.reflect.Method;

/**
 *  Value import bean service
 */
public interface ValueImportBean {

    /**
     * Find value by string
     * @param value String value
     * @param setMethod Set method
     * @return Found object
     */
    Object findValueByString(String value, Method setMethod);
}
