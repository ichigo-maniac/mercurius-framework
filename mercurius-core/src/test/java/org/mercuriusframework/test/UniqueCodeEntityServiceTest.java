package org.mercuriusframework.test;

import org.junit.Test;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Unique code entity service test
 */
public class UniqueCodeEntityServiceTest extends AbstractTest {

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - uniqueCodeEntityService.getEntityByCode()
     */
    @Test
    public void getEntityByCodeTest() {
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode("master_catalog", CatalogEntity.class);
        assertEquals(catalog.getUuid(), "4a9b636e-f065-11e6-9dac-836adef2f3a6");
    }
}
