package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.WarehouseEntityConverter;
import org.mercuriusframework.dto.WarehouseEntityDto;
import org.mercuriusframework.entities.WarehouseEntity;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Warehouse entity converter test
 */
public class WarehouseEntityConverterTest extends AbstractTest {

    /**
     * Warehouse entity converter
     */
    @Autowired
    private WarehouseEntityConverter warehouseEntityConverter;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - warehouseEntityConverter.convert
     */
    @Test
    public void convertTest() {
        WarehouseEntity warehouse = uniqueCodeEntityService.getEntityByCode("warehouse_1", WarehouseEntity.class);
        WarehouseEntityDto dto = warehouseEntityConverter.convert(warehouse);
        assertEquals(dto.getUuid().equals("12345e10-fa94-22e6-b6ff-abd400ed1234") && dto.getCode().equals("warehouse_1") &&
        dto.getEnabled() && dto.getName().equals("Warehouse 1"), true);
    }
}
