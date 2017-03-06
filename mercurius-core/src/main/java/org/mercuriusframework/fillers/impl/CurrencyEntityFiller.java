package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.CurrencyEntityDto;
import org.mercuriusframework.entities.CurrencyEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.stereotype.Service;

/**
 * Currency entity filler
 */
@Service("currencyEntityFiller")
public class CurrencyEntityFiller extends UniqueCodeEntityFiller<CurrencyEntity, CurrencyEntityDto> {

    /**
     * Fill a result object from a source object
     * @param currencyEntity    Source object
     * @param currencyEntityDto Result object
     * @param options           Load options
     */
    @Override
    public void fillIn(CurrencyEntity currencyEntity, CurrencyEntityDto currencyEntityDto, LoadOptions... options) {
        super.fillIn(currencyEntity, currencyEntityDto, options);
        currencyEntityDto.setSymbol(currencyEntity.getSymbol());
    }
}
