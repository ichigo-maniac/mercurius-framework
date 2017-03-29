package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.enums.FeatureType;
import org.springframework.stereotype.Service;
import java.lang.reflect.Method;

/**
 * Feature type import bean
 */
@Service("featureTypeImportBean")
public class FeatureTypeImportBean implements ValueImportBean {

    /**
     * Find value by string
     * @param value     String value
     * @param setMethod Set method
     * @return Found object
     */
    @Override
    public Object findValueByString(String value, Method setMethod) {
        return FeatureType.valueFromString(value);
    }
}
