package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.StoreEntityDto;
import org.mercuriusframework.entities.StoreEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.stereotype.Service;

/**
 * Store entity filler
 */
@Service("storeEntityFiller")
public class StoreEntityFiller extends UniqueCodeEntityFiller<StoreEntity, StoreEntityDto> {

    /**
     * Fill a result object from a source object
     * @param source Source object
     * @param result Result object
     * @param options Load options
     */
    @Override
    public void fillIn(StoreEntity source, StoreEntityDto result, LoadOptions... options) {
        super.fillIn(source, result, options);
        result.setDisabled(source.getDisabled());
    }
}
