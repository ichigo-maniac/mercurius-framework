package org.mercuriusframework.dataimport.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

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

    /**
     * Get collection type class
     * @param field Field
     * @return
     */
    default Class getCollectionTypeClass(Field field) {
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
