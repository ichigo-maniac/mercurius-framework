package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.CategoryEntityDto;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.CategoryEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Category entity converter
 */
@Service("categoryEntityConverter")
public class CategoryEntityConverter implements Converter<CategoryEntity, CategoryEntityDto> {

    /**
     * Category entity filler
     */
    @Autowired
    @Qualifier("categoryEntityFiller")
    protected CategoryEntityFiller categoryEntityFiller;

    /**
     * Convert a source object to a result object
     * @param categoryEntity Source object
     * @param options Load options
     * @return Result object
     */
    @Override
    public CategoryEntityDto convert(CategoryEntity categoryEntity, LoadOptions... options) {
        CategoryEntityDto result = new CategoryEntityDto();
        categoryEntityFiller.fillIn(categoryEntity, result, options);
        return result;
    }
}
