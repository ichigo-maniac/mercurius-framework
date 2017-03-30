package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.entities.converters.FeatureValueConverter;
import org.springframework.stereotype.Service;
import java.lang.reflect.Method;

/**
 * Feature value import bean
 */
@Service("featureValueImportBean")
public class FeatureValueImportBean implements ValueImportBean {

    /**
     * Find value by string
     * @param value     String value
     * @param setMethod Set method
     * @return Found object
     */
    @Override
    public Object findValueByString(String value, Method setMethod) {
        return new FeatureValueConverter().convertToEntityAttribute(value);
    }
}
