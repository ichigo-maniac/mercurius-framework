package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.CurrencyEntityDto;
import org.mercuriusframework.entities.CurrencyEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.CurrencyEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Currency entity converter
 */
@Service("currencyEntityConverter")
public class CurrencyEntityConverter implements Converter<CurrencyEntity, CurrencyEntityDto> {

    /**
     * Currency entity filler
     */
    @Autowired
    @Qualifier("currencyEntityFiller")
    protected CurrencyEntityFiller currencyEntityFiller;

    /**
     * Convert a source object to a result object
     * @param currencyEntity Source object
     * @param options        Load options
     * @return Result object
     */
    @Override
    public CurrencyEntityDto convert(CurrencyEntity currencyEntity, LoadOptions... options) {
        CurrencyEntityDto result = new CurrencyEntityDto();
        currencyEntityFiller.fillIn(currencyEntity, result, options);
        return result;
    }
}
