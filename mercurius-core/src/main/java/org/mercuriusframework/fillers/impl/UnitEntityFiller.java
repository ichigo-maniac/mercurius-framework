package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.UnitEntityDto;
import org.mercuriusframework.entities.UnitEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.stereotype.Service;

/**
 * Unit entity filler
 */
@Service("unitEntityFiller")
public class UnitEntityFiller extends CatalogUniqueCodeEntityFiller<UnitEntity, UnitEntityDto> {

    /**
     * Fill a result object from a source object
     * @param source    Source object
     * @param result Result object
     * @param options       Load options
     */
    @Override
    public void fillIn(UnitEntity source, UnitEntityDto result, LoadOptions... options) {
        super.fillIn(source, result, options);
    }
}
