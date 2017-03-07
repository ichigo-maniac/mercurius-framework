package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.PriceEntityDto;
import org.mercuriusframework.entities.PriceEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.PriceEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Price entity converter
 */
@Service("priceEntityConverter")
public class PriceEntityConverter implements Converter <PriceEntity, PriceEntityDto> {

    /**
     * Price entity filler
     */
    @Autowired
    @Qualifier("priceEntityFiller")
    protected PriceEntityFiller priceEntityFiller;

    /**
     * Convert a source object to a result object
     *
     * @param priceEntity Source object
     * @param options     Load options
     * @return Result object
     */
    @Override
    public PriceEntityDto convert(PriceEntity priceEntity, LoadOptions... options) {
        PriceEntityDto result = new PriceEntityDto();
        priceEntityFiller.fillIn(priceEntity, result, options);
        return result;
    }
}
