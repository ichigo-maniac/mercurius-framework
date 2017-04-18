package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Primitive value list import bean
 */
@Service("primitiveListImportBean")
public class PrimitiveListImportBean implements ValueImportBean {

    /**
     * Constants
     */
    private static final String VALUES_SPLITTER = ";";

    /**
     * Get value by string
     * @param value        String value
     * @param field        Field
     * @param sourceObject Source object
     * @return Found object
     */
    @Override
    public Object getValueByString(String value, Field field, Object sourceObject) {
        String values[] = value.split(VALUES_SPLITTER);
        Class valueClass = field.getType();
        List result = new ArrayList();
        for (String rawValue : values) {
            if (valueClass.equals(String.class)) {
                result.add(rawValue);
            }
            /** Long value */
            if (valueClass.equals(Long.class)) {
                result.add(Long.valueOf(rawValue));
            }
            /** Integer value */
            if (valueClass.equals(Integer.class)) {
                result.add(Integer.valueOf(rawValue));
                continue;
            }
            /** Short value */
            if (valueClass.equals(Short.class)) {
                result.add(Short.valueOf(rawValue));
                continue;
            }
            /** Float value */
            if (valueClass.equals(Float.class)) {
                result.add(Float.valueOf(rawValue));
                continue;
            }
            /** Double value */
            if (valueClass.equals(Double.class)) {
                result.add(Double.valueOf(rawValue));
                continue;
            }
            /** Boolean value */
            if (valueClass.equals(Boolean.class)) {
                result.add(Boolean.valueOf(rawValue));
                continue;
            }
            /** Big integer value */
            if (valueClass.equals(BigInteger.class)) {
                result.add(new BigInteger(rawValue));
                continue;
            }
            /** Big decimal value */
            if (valueClass.equals(BigDecimal.class)) {
                result.add(new BigDecimal(rawValue));
                continue;
            }
            throw new IllegalArgumentException("Class " + valueClass.getCanonicalName() + " is not supported " +
                    "as a primitive type value");
        }
        return result;
    }

}
