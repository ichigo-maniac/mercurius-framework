package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.StoreEntityConverter;
import org.mercuriusframework.dto.StoreEntityDto;
import org.mercuriusframework.entities.StoreEntity;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Store entity converter test
 */
public class StoreEntityConverterTest extends AbstractTest {

    /**
     * Store entity converter
     */
    @Autowired
    private StoreEntityConverter storeEntityConverter;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;


    /**
     * Method test - storeEntityConverter.convert
     */
    @Test
    public void convertTest() {
        StoreEntity store = uniqueCodeEntityService.getEntityByCode("default_store", StoreEntity.class);
        StoreEntityDto dto = storeEntityConverter.convert(store);
        assertEquals(dto.getUuid().equals("12345e10-fa94-11e6-b6ff-bf2400ed1234") && dto.getCode().equals("default_store") &&
        !dto.getDisabled() && dto.getName().equals("Default store"), true);
    }
}
