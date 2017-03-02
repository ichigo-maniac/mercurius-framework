package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.WarehouseEntityDto;
import org.mercuriusframework.entities.WarehouseEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.stereotype.Service;


/**
 * Warehouse entity filler
 */
@Service("warehouseEntityFiller")
public class WarehouseEntityFiller extends UniqueCodeEntityFiller<WarehouseEntity, WarehouseEntityDto> {

    /**
     * Fill a result object from a source object
     * @param source Source object
     * @param result Result object
     * @param options Load options
     */
    @Override
    public void fillIn(WarehouseEntity source, WarehouseEntityDto result, LoadOptions... options) {
        super.fillIn(source, result, options);
        result.setDisabled(source.getDisabled());
    }
}
