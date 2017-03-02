package org.mercuriusframework.fillers.impl;

import org.apache.commons.lang.ArrayUtils;
import org.mercuriusframework.converters.impl.WarehouseEntityConverter;
import org.mercuriusframework.dto.StoreEntityDto;
import org.mercuriusframework.entities.StoreEntity;
import org.mercuriusframework.entities.WarehouseEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.enums.StoreLoadOptions;
import org.mercuriusframework.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Store entity filler
 */
@Service("storeEntityFiller")
public class StoreEntityFiller extends UniqueCodeEntityFiller<StoreEntity, StoreEntityDto> {

    /**
     * Warehouse service
     */
    @Autowired
    @Qualifier("warehouseService")
    protected WarehouseService warehouseService;

    /**
     * Warehouse entity converter
     */
    @Autowired
    @Qualifier("warehouseEntityConverter")
    protected WarehouseEntityConverter warehouseEntityConverter;

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
        /** Warehouses */
        if (ArrayUtils.contains(options, StoreLoadOptions.WAREHOUSES)) {
            List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStore(source);
            result.setWarehouses(warehouseEntityConverter.convertAll(warehouses));
        } else {
            if (ArrayUtils.contains(options, StoreLoadOptions.ENABLED_WAREHOUSES)) {
                List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStore(source, false);
                result.setWarehouses(warehouseEntityConverter.convertAll(warehouses));
            }
        }
    }
}
