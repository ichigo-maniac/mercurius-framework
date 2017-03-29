package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.PriceEntityConverter;
import org.mercuriusframework.dto.PriceEntityDto;
import org.mercuriusframework.entities.PriceEntity;
import org.mercuriusframework.enums.PriceLoadOptions;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Price entity converter test
 */
public class PriceEntityConverterTest extends AbstractTest {

    /**
     * Price entity converter
     */
    @Autowired
    private PriceEntityConverter priceEntityConverter;

    /**
     * Catalog unique code entity service
     */
    @Autowired
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Method test - priceEntityConverter.convert
     */
    @Test
    public void convertTest() {
        PriceEntity price = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_01_price_pieces", "master_catalog", PriceEntity.class);
        PriceEntityDto dto = priceEntityConverter.convert(price);
        assertEquals(dto.getUuid().equals("a7478e10-fa94-11e6-b6ff-bf2400ed613a") && dto.getName().equals("Sword Art Online vol. 01 - price") &&
        dto.getCode().equals("product_sao_01_price_pieces") && dto.getPriceValue().equals(500.0), true);
        assertEquals(dto.getCurrency().getUuid().equals("4a9b636e-f065-11e6-1123-0000def2f3a6") && dto.getCurrency().getName().equals("Russia ruble") &&
                dto.getCurrency().getCode().equals("rub") && dto.getCurrency().getSymbol().equals("R"), true);
    }

    /**
     * Method test - priceEntityConverter.convert
     */
    @Test
    public void convertTest2() {
        PriceEntity price = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("product_sao_01_price_pieces", "master_catalog",
                PriceEntity.class, PriceEntity.PRODUCT);
        PriceEntityDto dto = priceEntityConverter.convert(price, PriceLoadOptions.PRODUCT);
        assertEquals(dto.getUuid().equals("a7478e10-fa94-11e6-b6ff-bf2400ed613a") && dto.getName().equals("Sword Art Online vol. 01 - price") &&
                dto.getCode().equals("product_sao_01_price_pieces") && dto.getPriceValue().equals(500.0), true);
        assertEquals(dto.getCurrency().getUuid().equals("4a9b636e-f065-11e6-1123-0000def2f3a6") && dto.getCurrency().getName().equals("Russia ruble") &&
                dto.getCurrency().getCode().equals("rub") && dto.getCurrency().getSymbol().equals("R"), true);

        assertEquals(dto.getProduct().getCode().equals("product_sao_01") && dto.getProduct().getName().equals("Sword Art Online vol. 01") &&
                        dto.getProduct().getShortName() == null &&
                        dto.getProduct().getUuid().equals("a1f05e10-fa94-11e6-b6ff-bf2400ed613a")
                , true);
    }

    /**
     * Method test - priceEntityConverter.convert
     */
    @Test
    public void convertTest3() {
        PriceEntity price = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCodeWithFetch("product_sao_01_price_pieces", "master_catalog",
                PriceEntity.class, PriceEntity.UNIT);
        PriceEntityDto dto = priceEntityConverter.convert(price, PriceLoadOptions.UNIT);
        assertEquals(dto.getUuid().equals("a7478e10-fa94-11e6-b6ff-bf2400ed613a") && dto.getName().equals("Sword Art Online vol. 01 - price") &&
                dto.getCode().equals("product_sao_01_price_pieces") && dto.getPriceValue().equals(500.0), true);
        assertEquals(dto.getCurrency().getUuid().equals("4a9b636e-f065-11e6-1123-0000def2f3a6") && dto.getCurrency().getName().equals("Russia ruble") &&
                dto.getCurrency().getCode().equals("rub") && dto.getCurrency().getSymbol().equals("R"), true);
        assertEquals(dto.getUnit().getUuid().equals("a1e2ae50-fa94-11e6-b6f6-67b357732118") && dto.getUnit().getName().equals("Pieces") &&
                dto.getUnit().getCode().equals("pieces"), true);
    }
}
