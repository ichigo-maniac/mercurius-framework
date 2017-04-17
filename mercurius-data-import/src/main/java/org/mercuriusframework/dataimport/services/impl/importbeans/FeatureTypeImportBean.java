package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.enums.FeatureType;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Feature type import bean
 */
@Service("featureTypeImportBean")
public class FeatureTypeImportBean implements ValueImportBean {

    /**
     * Get value by string
     * @param value        String value
     * @param field        Field
     * @param sourceObject Source object
     * @return Found object
     */
    @Override
    public Object getValueByString(String value, Field field, Object sourceObject) {
        return FeatureType.valueFromString(value);
    }
}
