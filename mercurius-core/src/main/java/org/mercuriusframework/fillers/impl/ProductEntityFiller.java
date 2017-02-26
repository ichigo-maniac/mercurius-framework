package org.mercuriusframework.fillers.impl;

import org.apache.commons.lang.ArrayUtils;
import org.mercuriusframework.converters.impl.CategoryEntityConverter;
import org.mercuriusframework.dto.ProductEntityDto;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.enums.ProductLoadOptions;
import org.mercuriusframework.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Product entity filler
 */
@Service("productEntityFiller")
public class ProductEntityFiller extends CatalogUniqueCodeEntityFiller<ProductEntity, ProductEntityDto> {

    /**
     * Category service
     */
    @Autowired
    protected CategoryService categoryService;

    /**
     * Category entity converter
     */
    @Autowired
    @Qualifier("categoryEntityConverter")
    protected CategoryEntityConverter categoryEntityConverter;

    /**
     * Fill a result object from a source object
     * @param productEntity    Source object
     * @param productEntityDto Result object
     * @param options          Load options
     */
    @Override
    public void fillIn(ProductEntity productEntity, ProductEntityDto productEntityDto, LoadOptions... options) {
        super.fillIn(productEntity, productEntityDto, options);
        productEntityDto.setShortName(productEntity.getShortName());
        /** Description */
        if (ArrayUtils.contains(options, ProductLoadOptions.DESCRIPTION)) {
            productEntityDto.setDescription(productEntity.getDescription() != null ? productEntity.getDescription().getValue() : "");
        }
        /** Breadcrumbs */
        if (ArrayUtils.contains(options, ProductLoadOptions.BREAD_CRUMBS)) {
            List<CategoryEntity> breadcrumbs = categoryService.getBreadCrumbsByCategoryUuid(productEntity.getMainCategory().getUuid());
            productEntityDto.setBreadCrumbs(categoryEntityConverter.convertAll(breadcrumbs));
        }
    }
}
