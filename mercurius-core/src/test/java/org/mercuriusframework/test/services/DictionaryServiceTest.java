package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.entities.DictionaryItemEntity;
import org.mercuriusframework.entities.DictionaryTypeEntity;
import org.mercuriusframework.services.DictionaryService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Dictionary service test
 */
public class DictionaryServiceTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> DICTIONARY_ITEMS_UUIDS_LIST = Arrays.asList(
            "2222636e-f065-11e6-4444-836adef2f3a6", "4a9b636e-f065-11e6-4444-836111f2f3a6"
    );

    /**
     * Dictionary service
     */
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - dictionaryService.getDictionaryItems
     */
    @Test
    public void getDictionaryItemsTest() {
        List<DictionaryItemEntity> dictionaryItems = dictionaryService.getDictionaryItems("dictionary_country");
        assertUuidListsEquals(getUuids(dictionaryItems), DICTIONARY_ITEMS_UUIDS_LIST);
    }

    /**
     * Method test - dictionaryService.getDictionaryItems
     */
    @Test
    public void getDictionaryItemsTest2() {
        DictionaryTypeEntity dictionaryType = uniqueCodeEntityService.getEntityByCode("dictionary_country", DictionaryTypeEntity.class);
        List<DictionaryItemEntity> dictionaryItems = dictionaryService.getDictionaryItems(dictionaryType);
        assertUuidListsEquals(getUuids(dictionaryItems), DICTIONARY_ITEMS_UUIDS_LIST);
    }

}
