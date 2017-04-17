package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.entities.converters.FeatureValueConverter;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Feature value import bean
 */
@Service("featureValueImportBean")
public class FeatureValueImportBean implements ValueImportBean {

    /**
     * Get value by string
     * @param value        String value
     * @param field        Field
     * @param sourceObject Source object
     * @return Found object
     */
    @Override
    public Object getValueByString(String value, Field field, Object sourceObject) {
        return new FeatureValueConverter().convertToEntityAttribute(value);
    }
}
