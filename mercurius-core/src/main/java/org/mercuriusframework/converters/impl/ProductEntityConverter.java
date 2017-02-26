package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.ProductEntityDto;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.ProductEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Product entity converter
 */
@Service("productEntityConverter")
public class ProductEntityConverter implements Converter<ProductEntity, ProductEntityDto> {

    /**
     * Product entity filler
     */
    @Autowired
    @Qualifier("productEntityFiller")
    protected ProductEntityFiller productEntityFiller;
    /**
     * Convert a source object to a result object
     * @param productEntity Source object
     * @param options       Load options
     * @return Result object
     */
    @Override
    public ProductEntityDto convert(ProductEntity productEntity, LoadOptions... options) {
        ProductEntityDto result = new ProductEntityDto();
        productEntityFiller.fillIn(productEntity, result, options);
        return result;
    }
}
