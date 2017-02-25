package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.ProductEntityDto;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.stereotype.Service;

/**
 * Product entity filler
 */
@Service("productEntityFiller")
public class ProductEntityFiller extends CatalogUniqueCodeEntityFiller<ProductEntity, ProductEntityDto> {

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
    }
}
