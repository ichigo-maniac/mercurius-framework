package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.UniqueCodeEntityDto;
import org.mercuriusframework.entities.UniqueCodeEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.stereotype.Service;

/**
 * Unique code entity filler
 */
@Service("uniqueCodeEntityFiller")
public class UniqueCodeEntityFiller<SOURCE extends UniqueCodeEntity, RESULT extends UniqueCodeEntityDto>
        extends AbstractEntityFiller<SOURCE, RESULT> {
    /**
     * Fill a result object from a source object
     * @param source Source object
     * @param result Result object
     * @param options Load options
     */
    @Override
    public void fillIn(SOURCE source, RESULT result, LoadOptions... options) {
        super.fillIn(source, result, options);
        result.setCode(source.getCode());
        result.setName(source.getName());
    }
}
