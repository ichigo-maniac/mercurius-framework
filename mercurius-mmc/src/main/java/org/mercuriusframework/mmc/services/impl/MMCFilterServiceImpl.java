package org.mercuriusframework.mmc.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
     * Object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Criteria types constants
     */
    private static final CriteriaValueType[] NUMBER_TYPES = {
            CriteriaValueType.EQUALS, CriteriaValueType.NOT_EQUALS, CriteriaValueType.MORE, CriteriaValueType.MORE_OR_EQUALS,
            CriteriaValueType.LESS, CriteriaValueType.LESS_OR_EQUALS
    };

    private static final CriteriaValueType[] STRING_TYPES = {
            CriteriaValueType.EQUALS, CriteriaValueType.NOT_EQUALS, CriteriaValueType.START_WITH, CriteriaValueType.END_WITH, CriteriaValueType.CONTAINS
    };

    private static final CriteriaValueType[] BOOLEAN_TYPES = {
            CriteriaValueType.EQUALS
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
        /** Create json for criteria types */
        if (filterContainer.getCriteriaTypes() != null) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (CriteriaValueType criteriaValueType : filterContainer.getCriteriaTypes()) {
                arrayNode.add(criteriaValueType.getValue());
            }
            filterContainer.setJsonCriteriaTypes(arrayNode.toString());
        }
    }
}
