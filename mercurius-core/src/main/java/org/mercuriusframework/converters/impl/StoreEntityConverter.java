package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.StoreEntityDto;
import org.mercuriusframework.entities.StoreEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.StoreEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Store entity converter
 */
@Service("storeEntityConverter")
public class StoreEntityConverter implements Converter<StoreEntity, StoreEntityDto> {

    /**
     * Store entity filler
     */
    @Autowired
    @Qualifier("storeEntityFiller")
    private StoreEntityFiller storeEntityFiller;

    /**
     * Convert a source object to a result object
     * @param source Source object
     * @param options Load options
     * @return Result object
     */
    @Override
    public StoreEntityDto convert(StoreEntity source, LoadOptions... options) {
        StoreEntityDto result = new StoreEntityDto();
        storeEntityFiller.fillIn(source, result, options);
        return result;
    }
}
