package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.CategoryEntityDto;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Category entity filler
 */
@Service("categoryEntityFiller")
public class CategoryEntityFiller extends CatalogUniqueCodeEntityFiller<CategoryEntity, CategoryEntityDto> {

    /**
     * Category service
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Fill a result object from a source object
     * @param categoryEntity    Source object
     * @param categoryEntityDto Result object
     */
    @Override
    public void fillIn(CategoryEntity categoryEntity, CategoryEntityDto categoryEntityDto) {
        super.fillIn(categoryEntity, categoryEntityDto);
        /** Build url */
        List<CategoryEntity> breadcrumbs = categoryService.getBreadCrumbs(categoryEntity.getUuid());
        StringBuilder buildUrl = new StringBuilder();
        for (CategoryEntity temp : breadcrumbs) {
            buildUrl.append("/" + temp.getCode());
        }
        categoryEntityDto.setBuiltUrl(buildUrl.toString());
    }
}
