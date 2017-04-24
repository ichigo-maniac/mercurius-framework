package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.enums.FacetType;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Facet type import bean
 */
@Service("facetTypeImportBean")
public class FacetTypeImportBean implements ValueImportBean {

    /**
     * Get value by string
     * @param value        String value
     * @param field        Field
     * @param sourceObject Source object
     * @return Found object
     */
    @Override
    public Object getValueByString(String value, Field field, Object sourceObject) {
        return FacetType.valueFromString(value);
    }
}
