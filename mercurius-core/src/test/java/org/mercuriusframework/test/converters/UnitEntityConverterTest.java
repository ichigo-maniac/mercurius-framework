package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.UnitEntityConverter;
import org.mercuriusframework.dto.UnitEntityDto;
import org.mercuriusframework.entities.UnitEntity;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Unit entity converter test
 */
public class UnitEntityConverterTest extends AbstractTest {

    /**
     * Unit entity converter
     */
    @Autowired
    private UnitEntityConverter unitEntityConverter;

    /**
     * Catalog unique code entity service
     */
    @Autowired
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Method test - unitEntityConverter.convert
     */
    @Test
    public void convertTest() {
        UnitEntity unit = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("pieces", "master_catalog", UnitEntity.class);
        UnitEntityDto dto = unitEntityConverter.convert(unit);
        assertEquals(dto.getUuid().equals("a1e2ae50-fa94-11e6-b6f6-67b357732118") && dto.getName().equals("Pieces") &&
        dto.getCode().equals("pieces"), true);
    }
}
