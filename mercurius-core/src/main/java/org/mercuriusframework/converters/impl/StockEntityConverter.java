package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.StockEntityDto;
import org.mercuriusframework.entities.StockEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.StockEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Stock entity converter
 */
@Service("stockEntityConverter")
public class StockEntityConverter implements Converter<StockEntity, StockEntityDto> {

    /**
     * Stock entity filler
     */
    @Autowired
    @Qualifier("stockEntityFiller")
    protected StockEntityFiller stockEntityFiller;

    /**
     * Convert a source object to a result object
     * @param stockEntity Source object
     * @param options     Load options
     * @return Result object
     */
    @Override
    public StockEntityDto convert(StockEntity stockEntity, LoadOptions... options) {
        StockEntityDto result = new StockEntityDto();
        stockEntityFiller.fillIn(stockEntity, result, options);
        return result;
    }
}
