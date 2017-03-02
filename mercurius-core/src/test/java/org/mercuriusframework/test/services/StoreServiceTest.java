package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.entities.StoreEntity;
import org.mercuriusframework.services.StoreService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Store service test
 */
public class StoreServiceTest extends AbstractTest {
    /**
     * Constants
     */
    private static final List<String> ALL_STORES_UUIDS_LIST = Arrays.asList(
            "12345e10-fa94-11e6-b6ff-bf2400ed1234", "12345555-fa94-11e6-b6ff-bf2400ed5555",
            "11115555-fa94-11e6-b6ff-bf2400ed1234"
    );
    private static final List<String> DISABLED_STORES_UUIDS_LIST = Arrays.asList(
            "12345555-fa94-11e6-b6ff-bf2400ed5555", "11115555-fa94-11e6-b6ff-bf2400ed1234"
    );

    /**
     * Store service
     */
    @Autowired
    private StoreService storeService;

    /**
     * Method test - storeService.getStores
     */
    @Test
    public void getStoresTest() {
        List<StoreEntity> stores = storeService.getStores();
        assertUuidListsEquals(ALL_STORES_UUIDS_LIST, getUuids(stores));

    }

    /**
     * Method test (second) - storeService.getStores
     */
    @Test
    public void getStoresTest2() {
        List<StoreEntity> stores = storeService.getStores(true);
        assertUuidListsEquals(DISABLED_STORES_UUIDS_LIST, getUuids(stores));
    }
}
