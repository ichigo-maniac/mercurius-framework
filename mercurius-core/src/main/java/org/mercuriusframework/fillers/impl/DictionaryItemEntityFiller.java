package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.converters.impl.DictionaryTypeEntityConverter;
import org.mercuriusframework.dto.DictionaryItemEntityDto;
import org.mercuriusframework.entities.DictionaryItemEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Dictionary item entity filler
 */
@Service("dictionaryItemEntityFiller")
public class DictionaryItemEntityFiller extends UniqueCodeEntityFiller<DictionaryItemEntity, DictionaryItemEntityDto> {

    /**
     * Dictionary type entity converter
     */
    @Autowired
    @Qualifier("dictionaryTypeEntityConverter")
    protected DictionaryTypeEntityConverter dictionaryTypeEntityConverter;

    /**
     * Fill a result object from a source object
     * @param dictionaryItemEntity    Source object
     * @param dictionaryItemEntityDto Result object
     * @param options                 Load options
     */
    @Override
    public void fillIn(DictionaryItemEntity dictionaryItemEntity, DictionaryItemEntityDto dictionaryItemEntityDto, LoadOptions... options) {
        super.fillIn(dictionaryItemEntity, dictionaryItemEntityDto, options);
        dictionaryItemEntityDto.setDictionaryType(dictionaryTypeEntityConverter.convert(dictionaryItemEntity.getDictionaryType()));
    }
}
