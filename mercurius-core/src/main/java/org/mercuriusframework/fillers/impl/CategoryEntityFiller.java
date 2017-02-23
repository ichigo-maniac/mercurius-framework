package org.mercuriusframework.fillers.impl;

import org.apache.commons.lang.ArrayUtils;
import org.mercuriusframework.converters.impl.CategoryEntityConverter;
import org.mercuriusframework.dto.CategoryEntityDto;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.enums.CategoryLoadOptions;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Category entity filler
 */
@Service("categoryEntityFiller")
public class CategoryEntityFiller extends CatalogUniqueCodeEntityFiller<CategoryEntity, CategoryEntityDto> {

    /**
     * Category entity converter
     */
    @Autowired
    @Qualifier("categoryEntityConverter")
    private CategoryEntityConverter categoryEntityConverter;

    /**
     * Category service
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Fill a result object from a source object
     * @param categoryEntity    Source object
     * @param categoryEntityDto Result object
     * @param options Load options
     */
    @Override
    public void fillIn(CategoryEntity categoryEntity, CategoryEntityDto categoryEntityDto, LoadOptions... options) {
        super.fillIn(categoryEntity, categoryEntityDto, options);
        /** Build url */
        List<CategoryEntity> breadcrumbs = categoryService.getBreadCrumbs(categoryEntity.getUuid());
        StringBuilder buildUrl = new StringBuilder();
        for (CategoryEntity temp : breadcrumbs) {
            buildUrl.append("/" + temp.getCode());
        }
        /** Breadcrumbs */
        if (ArrayUtils.contains(options, CategoryLoadOptions.BREAD_CRUMBS)) {
            categoryEntityDto.setBreadCrumbs(categoryEntityConverter.convertAll(breadcrumbs));
        }
        categoryEntityDto.setBuiltUrl(buildUrl.toString());
    }
}
