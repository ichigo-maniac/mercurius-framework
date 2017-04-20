package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.DictionaryTypeEntityDto;
import org.mercuriusframework.entities.DictionaryTypeEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Dictionary type entity filler
 */
@Service("dictionaryTypeEntityFiller")
public class DictionaryTypeEntityFiller extends UniqueCodeEntityFiller<DictionaryTypeEntity, DictionaryTypeEntityDto> {

    /**
     * Fill a result object from a source object
     * @param dictionaryTypeEntity    Source object
     * @param dictionaryTypeEntityDto Result object
     * @param options                 Load options
     */
    @Override
    public void fillIn(DictionaryTypeEntity dictionaryTypeEntity, DictionaryTypeEntityDto dictionaryTypeEntityDto, LoadOptions... options) {
        super.fillIn(dictionaryTypeEntity, dictionaryTypeEntityDto, options);
    }
}
