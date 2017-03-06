package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.CurrencyEntityConverter;
import org.mercuriusframework.dto.CurrencyEntityDto;
import org.mercuriusframework.entities.CurrencyEntity;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Currency entity converter
 */
public class CurrencyEntityConverterTest extends AbstractTest {

    /**
     * Currency entity converter
     */
    @Autowired
    private CurrencyEntityConverter currencyEntityConverter;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - currencyEntityConverter.convert
     */
    @Test
    public void convertTest() {
        CurrencyEntity currency = uniqueCodeEntityService.getEntityByCode("usd", CurrencyEntity.class);
        CurrencyEntityDto dto = currencyEntityConverter.convert(currency);
        assertEquals(dto.getUuid().equals("4a9b636e-2222-11e6-7584-0000def2fa9b") && dto.getName().equals("Dollar USA") &&
                dto.getCode().equals("usd") && dto.getSymbol().equals("$"), true);
    }
}
