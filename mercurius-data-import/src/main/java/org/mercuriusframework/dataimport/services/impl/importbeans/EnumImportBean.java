package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.enums.FacetType;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Enum import bean
 */
@Service("enumImportBean")
public class EnumImportBean implements ValueImportBean {

    private static final String VALUE_GET_METHOD = "valueFromString";

    /**
     * Get value by string
     * @param value        String value
     * @param field        Field
     * @param sourceObject Source object
     * @return Found object
     */
    @Override
    public Object getValueByString(String value, Field field, Object sourceObject) {
        Class clazz = field.getType();
        try {
            Method method = clazz.getDeclaredMethod(VALUE_GET_METHOD, String.class);
            return method.invoke(null, value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
