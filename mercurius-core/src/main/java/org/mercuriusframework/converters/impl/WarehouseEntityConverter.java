package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.WarehouseEntityDto;
import org.mercuriusframework.entities.WarehouseEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.WarehouseEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Warehouse entity converter
 */
@Service("warehouseEntityConverter")
public class WarehouseEntityConverter implements Converter<WarehouseEntity, WarehouseEntityDto> {

    /**
     * Warehouse entity filler
     */
    @Autowired
    @Qualifier("warehouseEntityFiller")
    protected WarehouseEntityFiller warehouseEntityFiller;

    /**
     * Convert a source object to a result object
     * @param source Source object
     * @param options         Load options
     * @return Result object
     */
    @Override
    public WarehouseEntityDto convert(WarehouseEntity source, LoadOptions... options) {
        WarehouseEntityDto result = new WarehouseEntityDto();
        warehouseEntityFiller.fillIn(source, result, options);
        return result;
    }
}
