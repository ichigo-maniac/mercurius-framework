package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.DictionaryItemEntityConverter;
import org.mercuriusframework.converters.impl.DictionaryTypeEntityConverter;
import org.mercuriusframework.dto.DictionaryItemEntityDto;
import org.mercuriusframework.entities.DictionaryItemEntity;
import org.mercuriusframework.entities.DictionaryTypeEntity;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Dictionary item entity converter test
 */
public class DictionaryItemEntityConverterTest extends AbstractTest {

    /**
     * Dictionary item entity converter test
     */
    @Autowired
    private DictionaryItemEntityConverter dictionaryItemEntityConverter;

    /**
     * Dictionary type entity converter
     */
    @Autowired
    private DictionaryTypeEntityConverter dictionaryTypeEntityConverter;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - dictionaryItemEntityConverter.convert
     */
    @Test
    public void convertTest() {
        DictionaryTypeEntity dictionaryType = uniqueCodeEntityService.getEntityByCode("dictionary_country", DictionaryTypeEntity.class);
        DictionaryItemEntity entity = uniqueCodeEntityService.getEntityByCode("country_japan", DictionaryItemEntity.class);
        DictionaryItemEntityDto entityDto = dictionaryItemEntityConverter.convert(entity);
        assertEquals(entityDto.getUuid().equals("2222636e-f065-11e6-4444-836adef2f3a6")
                && entityDto.getCode().equals("country_japan")
                && entityDto.getName().equals("Japan")
                && entityDto.getDictionaryType().equals(dictionaryTypeEntityConverter.convert(dictionaryType)), true);
    }
}
