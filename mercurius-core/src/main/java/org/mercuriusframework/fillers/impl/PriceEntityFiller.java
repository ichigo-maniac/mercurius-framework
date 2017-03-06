package org.mercuriusframework.fillers.impl;

import org.apache.commons.lang.ArrayUtils;
import org.mercuriusframework.converters.impl.CurrencyEntityConverter;
import org.mercuriusframework.converters.impl.ProductEntityConverter;
import org.mercuriusframework.converters.impl.UnitEntityConverter;
import org.mercuriusframework.dto.PriceEntityDto;
import org.mercuriusframework.entities.PriceEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.enums.PriceLoadOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Price entity filler
 */
@Service("priceEntityFiller")
public class PriceEntityFiller extends CatalogUniqueCodeEntityFiller<PriceEntity, PriceEntityDto> {

    /**
     * Currency entity converter
     */
    @Autowired
    @Qualifier("currencyEntityConverter")
    protected CurrencyEntityConverter currencyEntityConverter;

    /**
     * Product entity converter
     */
    @Autowired
    @Qualifier("productEntityConverter")
    protected ProductEntityConverter productEntityConverter;

    /**
     * Unit entity converter
     */
    @Autowired
    @Qualifier("unitEntityConverter")
    protected UnitEntityConverter unitEntityConverter;

    /**
     * Fill a result object from a source object
     * @param priceEntity    Source object
     * @param priceEntityDto Result object
     * @param options        Load options
     */
    @Override
    public void fillIn(PriceEntity priceEntity, PriceEntityDto priceEntityDto, LoadOptions... options) {
        super.fillIn(priceEntity, priceEntityDto, options);
        priceEntityDto.setCurrency(currencyEntityConverter.convert(priceEntity.getCurrency()));
        priceEntityDto.setPriceValue(priceEntity.getPriceValue());
        /** Unit */
        if (ArrayUtils.contains(options, PriceLoadOptions.UNIT)) {
            priceEntityDto.setUnit(unitEntityConverter.convert(priceEntity.getUnit()));
        }
        /** Product */
        if (ArrayUtils.contains(options, PriceLoadOptions.PRODUCT)) {
            priceEntityDto.setProduct(productEntityConverter.convert(priceEntity.getProduct()));
        }
    }
}
