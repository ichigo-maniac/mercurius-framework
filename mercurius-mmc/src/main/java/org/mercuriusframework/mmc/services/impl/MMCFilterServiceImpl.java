package org.mercuriusframework.mmc.services.impl;

import org.mercuriusframework.enums.CriteriaValueType;
import org.mercuriusframework.mmc.dto.FilterContainer;
import org.mercuriusframework.mmc.enums.FieldType;
import org.mercuriusframework.mmc.services.MMCFilterService;
import org.mercuriusframework.mmc.widgets.listview.Filter;
import org.mercuriusframework.providers.EntityMessageSourceProvider;
import org.mercuriusframework.services.EntityReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * MMC filter service
 */
@Service("mmcFilterService")
public class MMCFilterServiceImpl implements MMCFilterService {

    /**
     * Criteria types constants
     */
    private static final CriteriaValueType[] NUMBER_TYPES = {
            CriteriaValueType.EQUAL, CriteriaValueType.NOT_EQUAL
    };

    private static final CriteriaValueType[] STRING_TYPES = {
            CriteriaValueType.EQUAL, CriteriaValueType.NOT_EQUAL, CriteriaValueType.START_WITH, CriteriaValueType.END_WITH
    };

    private static final CriteriaValueType[] BOOLEAN_TYPES = {
            CriteriaValueType.EQUAL
    };

    /**
     * Entity reflection service
     */
    @Autowired
    @Qualifier("entityReflectionService")
    private EntityReflectionService annotationService;

    /**
     * Build filters
     * @param entityName Entity name
     * @param filters    List of filters
     * @return List of filters containers
     */
    @Override
    public List<FilterContainer> buildFilters(String entityName, List<Filter> filters) {
        Class entityClass = annotationService.getEntityClassByEntityName(entityName);
        List<FilterContainer> result = new ArrayList<>(filters.size());
        for (Filter filter : filters) {
            try {
                Field field = annotationService.getField(entityClass, filter.getProperty());
                FilterContainer filterContainer = new FilterContainer(filter.getProperty(), filter.getIncludeOnStart());
                filterContainer.setLabel(EntityMessageSourceProvider.getMessage(entityName, filter.getProperty()));
                setCriteriaTypes(filterContainer, field.getType());
                result.add(filterContainer);

            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * Set criteria types
     * @param filterContainer Filter container
     * @param type Property class
     */
    private void setCriteriaTypes(FilterContainer filterContainer, Class type) {
        if (Number.class.isAssignableFrom(type)) {
            filterContainer.setCriteriaTypes(NUMBER_TYPES);
            filterContainer.setFieldType(FieldType.NUMBER);
        }
        if (String.class.isAssignableFrom(type)) {
            filterContainer.setCriteriaTypes(STRING_TYPES);
            filterContainer.setFieldType(FieldType.STRING);
        }
        if (Boolean.class.isAssignableFrom(type)) {
            filterContainer.setCriteriaTypes(BOOLEAN_TYPES);
            filterContainer.setFieldType(FieldType.BOOLEAN);
        }
    }
}
