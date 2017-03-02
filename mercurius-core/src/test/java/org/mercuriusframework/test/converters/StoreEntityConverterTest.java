package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.StoreEntityConverter;
import org.mercuriusframework.dto.StoreEntityDto;
import org.mercuriusframework.entities.StoreEntity;
import org.mercuriusframework.enums.StoreLoadOptions;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Store entity converter test
 */
public class StoreEntityConverterTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> ALL_WAREHOUSES_UUIDS_LIST = Arrays.asList(
            "12345e10-fa94-22e6-b6ff-abd400ed1234", "12345e10-fa94-22e6-b6ff-abd400ed2222",
            "12345e10-fa94-22e6-b6ff-abd400ed3333"
    );
    private static final List<String> ENABLED_WAREHOUSES_UUIDS_LIST = Arrays.asList(
            "12345e10-fa94-22e6-b6ff-abd400ed1234", "12345e10-fa94-22e6-b6ff-abd400ed2222"
    );

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

    /**
     * Method test (second) - storeEntityConverter.convert
     */
    @Test
    public void convertTest2() {
        StoreEntity store = uniqueCodeEntityService.getEntityByCode("default_store", StoreEntity.class);
        StoreEntityDto dto = storeEntityConverter.convert(store, StoreLoadOptions.WAREHOUSES);
        assertEquals(dto.getUuid().equals("12345e10-fa94-11e6-b6ff-bf2400ed1234") && dto.getCode().equals("default_store") &&
                !dto.getDisabled() && dto.getName().equals("Default store"), true);
        assertUuidListsEquals(ALL_WAREHOUSES_UUIDS_LIST, getUuidsFromDtos(dto.getWarehouses()));
    }

    /**
     * Method test (third) - storeEntityConverter.convert
     */
    @Test
    public void convertTest3() {
        StoreEntity store = uniqueCodeEntityService.getEntityByCode("default_store", StoreEntity.class);
        StoreEntityDto dto = storeEntityConverter.convert(store, StoreLoadOptions.ENABLED_WAREHOUSES);
        assertEquals(dto.getUuid().equals("12345e10-fa94-11e6-b6ff-bf2400ed1234") && dto.getCode().equals("default_store") &&
                !dto.getDisabled() && dto.getName().equals("Default store"), true);
        assertUuidListsEquals(ENABLED_WAREHOUSES_UUIDS_LIST, getUuidsFromDtos(dto.getWarehouses()));
    }
}
