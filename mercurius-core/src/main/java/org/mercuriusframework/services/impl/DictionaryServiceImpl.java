package org.mercuriusframework.services.impl;

import org.mercuriusframework.entities.DictionaryItemEntity;
import org.mercuriusframework.entities.DictionaryTypeEntity;
import org.mercuriusframework.services.DictionaryService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Dictionary service
 */
@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Get dictionary items by dictionary type code
     * @param dictionaryTypeCode Dictionary type code
     * @return Dictionary items
     */
    @Override
    public List<DictionaryItemEntity> getDictionaryItems(String dictionaryTypeCode) {
        return entityService.getListResultByQuery("SELECT item FROM " + DictionaryItemEntity.ENTITY_NAME + " as item " +
                "WHERE item." + DictionaryItemEntity.DICTIONARY_TYPE + "." + DictionaryTypeEntity.CODE + " = :dictionaryTypeCode",
                DictionaryItemEntity.class, new QueryParameter("dictionaryTypeCode", dictionaryTypeCode));
    }

    /**
     * Get dictionary items by dictionary type
     * @param dictionaryType Dictionary type
     * @return Dictionary items
     */
    @Override
    public List<DictionaryItemEntity> getDictionaryItems(DictionaryTypeEntity dictionaryType) {
        return entityService.getListResultByQuery("SELECT item FROM " + DictionaryItemEntity.ENTITY_NAME + " as item " +
                        "WHERE item." + DictionaryItemEntity.DICTIONARY_TYPE + " = :dictionaryType",
                DictionaryItemEntity.class, new QueryParameter("dictionaryType", dictionaryType));
    }
}
