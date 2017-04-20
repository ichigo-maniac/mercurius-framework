package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.DictionaryItemEntityDto;
import org.mercuriusframework.entities.DictionaryItemEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.DictionaryItemEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Dictionary item entity converter
 */
@Service("dictionaryItemEntityConverter")
public class DictionaryItemEntityConverter implements Converter<DictionaryItemEntity, DictionaryItemEntityDto> {

    /**
     * Dictionary item entity filler
     */
    @Autowired
    @Qualifier("dictionaryItemEntityFiller")
    protected DictionaryItemEntityFiller dictionaryItemEntityFiller;

    /**
     * Convert a source object to a result object
     * @param dictionaryItemEntity Source object
     * @param options              Load options
     * @return Result object
     */
    @Override
    public DictionaryItemEntityDto convert(DictionaryItemEntity dictionaryItemEntity, LoadOptions... options) {
        DictionaryItemEntityDto entityDto = new DictionaryItemEntityDto();
        dictionaryItemEntityFiller.fillIn(dictionaryItemEntity, entityDto, options);
        return entityDto;
    }
}
