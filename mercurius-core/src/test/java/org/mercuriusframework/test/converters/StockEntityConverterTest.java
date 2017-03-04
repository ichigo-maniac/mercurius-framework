package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.StockEntityConverter;
import org.mercuriusframework.dto.StockEntityDto;
import org.mercuriusframework.entities.StockEntity;
import org.mercuriusframework.enums.StockLoadOptions;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Stock entity converter test
 */
public class StockEntityConverterTest extends AbstractTest {

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Stock entity converter
     */
    @Autowired
    private StockEntityConverter stockEntityConverter;

    /**
     * Method test - stockEntityConverter.convert
     */
    @Test
    public void convertTest() {
        StockEntity stock = uniqueCodeEntityService.getEntityByCode("stock_prince_of_tennis_01_pieces", StockEntity.class);
        StockEntityDto dto = stockEntityConverter.convert(stock);
        assertEquals(dto.getCount().equals(15l) && dto.getUuid().equals("12345e10-1a94-22e6-b6ff-abd422223333") &&
        dto.getCode().equals("stock_prince_of_tennis_01_pieces") && dto.getName().equals("Prince of tennis 01 - pieces") &&
        dto.getEnabled().equals(true), true);
    }

    /**
     * Method test - stockEntityConverter.convert
     */
    @Test
    public void convertTest2() {
        StockEntity stock = uniqueCodeEntityService.getEntityByCode("stock_prince_of_tennis_01_pieces", StockEntity.class, StockEntity.UNIT);
        StockEntityDto dto = stockEntityConverter.convert(stock, StockLoadOptions.UNIT);
        assertEquals(dto.getCount().equals(15l) && dto.getUuid().equals("12345e10-1a94-22e6-b6ff-abd422223333") &&
                dto.getCode().equals("stock_prince_of_tennis_01_pieces") && dto.getName().equals("Prince of tennis 01 - pieces") &&
                dto.getEnabled().equals(true), true);
        assertEquals(dto.getUnit().getUuid().equals("a1e2ae50-fa94-11e6-b6f6-67b357732118") && dto.getUnit().getName().equals("Pieces") &&
                dto.getUnit().getCode().equals("pieces"), true);
    }
}
