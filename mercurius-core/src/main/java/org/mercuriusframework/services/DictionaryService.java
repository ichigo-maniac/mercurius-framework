package org.mercuriusframework.services;

import org.mercuriusframework.entities.DictionaryItemEntity;
import org.mercuriusframework.entities.DictionaryTypeEntity;
import java.util.List;

/**
 * Dictionary service interface
 */
public interface DictionaryService {

    /**
     * Get dictionary items by dictionary type code
     * @param dictionaryTypeCode Dictionary type code
     * @return Dictionary items
     */
    List<DictionaryItemEntity> getDictionaryItems(String dictionaryTypeCode);

    /**
     * Get dictionary items by dictionary type
     * @param dictionaryType Dictionary type
     * @return Dictionary items
     */
    List<DictionaryItemEntity> getDictionaryItems(DictionaryTypeEntity dictionaryType);
}
