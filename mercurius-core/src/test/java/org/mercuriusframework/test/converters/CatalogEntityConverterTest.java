package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.CatalogEntityConverter;
import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Catalog entity converter test
 */
public class CatalogEntityConverterTest extends AbstractTest {
    /**
     * Catalog entity converter
     */
    @Autowired
    private CatalogEntityConverter catalogEntityConverter;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - catalogEntityConverter.convert
     */
    @Test
    public void convertTest() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        CatalogEntityDto catalogDto = catalogEntityConverter.convert(catalog);
        assertEquals(catalogDto.getName().equals("Master catalog") && catalogDto.getCode().equals("master_catalog") &&
                catalogDto.getUuid().equals("4a9b636e-f065-11e6-9dac-836adef2f3a6") ,true);
    }
}