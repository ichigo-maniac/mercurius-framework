package org.mercuriusframework.fillers.impl;

import org.apache.commons.lang.ArrayUtils;
import org.mercuriusframework.converters.impl.ProductEntityConverter;
import org.mercuriusframework.converters.impl.UnitEntityConverter;
import org.mercuriusframework.converters.impl.WarehouseEntityConverter;
import org.mercuriusframework.dto.StockEntityDto;
import org.mercuriusframework.entities.StockEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.enums.StockLoadOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Stock entity filler
 */
@Service("stockEntityFiller")
public class StockEntityFiller extends UniqueCodeEntityFiller<StockEntity, StockEntityDto> {

    /**
     * Unit entity converter
     */
    @Autowired
    @Qualifier("unitEntityConverter")
    private UnitEntityConverter unitEntityConverter;

    /**
     * Warehouse entity converter
     */
    @Autowired
    @Qualifier("warehouseEntityConverter")
    private WarehouseEntityConverter warehouseEntityConverter;

    /**
     * Product entity converter
     */
    @Autowired
    @Qualifier("productEntityConverter")
    private ProductEntityConverter productEntityConverter;


    /**
     * Fill a result object from a source object
     * @param stockEntity    Source object
     * @param stockEntityDto Result object
     * @param options        Load options
     */
    @Override
    public void fillIn(StockEntity stockEntity, StockEntityDto stockEntityDto, LoadOptions... options) {
        super.fillIn(stockEntity, stockEntityDto, options);
        stockEntityDto.setEnabled(stockEntity.getEnabled() == null ? true : stockEntity.getEnabled());
        stockEntityDto.setCount(stockEntity.getCount());
        if (ArrayUtils.contains(options, StockLoadOptions.UNIT)) {
            stockEntityDto.setUnit(unitEntityConverter.convert(stockEntity.getUnit()));
        }
        if (ArrayUtils.contains(options, StockLoadOptions.PRODUCT)) {
            stockEntityDto.setProduct(productEntityConverter.convert(stockEntity.getProduct()));
        }
        if (ArrayUtils.contains(options, StockLoadOptions.WAREHOUSE)) {
            stockEntityDto.setWarehouse(warehouseEntityConverter.convert(stockEntity.getWarehouse()));
        }

    }
}
