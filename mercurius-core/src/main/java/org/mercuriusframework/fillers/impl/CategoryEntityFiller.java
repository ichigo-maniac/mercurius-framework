package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.CategoryEntityDto;
import org.mercuriusframework.entities.CategoryEntity;
import org.springframework.stereotype.Service;

/**
 * Category entity filler
 */
@Service("categoryEntityFiller")
public class CategoryEntityFiller extends CatalogUniqueCodeEntityFiller<CategoryEntity, CategoryEntityDto> {
    /**
     * Fill a result object from a source object
     * @param categoryEntity    Source object
     * @param categoryEntityDto Result object
     */
    @Override
    public void fillIn(CategoryEntity categoryEntity, CategoryEntityDto categoryEntityDto) {
        super.fillIn(categoryEntity, categoryEntityDto);
    }
}
