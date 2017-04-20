package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.DictionaryTypeEntityDto;
import org.mercuriusframework.entities.DictionaryTypeEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.DictionaryTypeEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Dictionary type entity converter
 */
@Service("dictionaryTypeEntityConverter")
public class DictionaryTypeEntityConverter implements Converter<DictionaryTypeEntity, DictionaryTypeEntityDto> {

    /**
     * Dictionary type entity filler
     */
    @Autowired
    @Qualifier("dictionaryTypeEntityFiller")
    protected DictionaryTypeEntityFiller dictionaryTypeEntityFiller;

    /**
     * Convert a source object to a result object
     * @param dictionaryTypeEntity Source object
     * @param options              Load options
     * @return Result object
     */
    @Override
    public DictionaryTypeEntityDto convert(DictionaryTypeEntity dictionaryTypeEntity, LoadOptions... options) {
        DictionaryTypeEntityDto entityDto = new DictionaryTypeEntityDto();
        dictionaryTypeEntityFiller.fillIn(dictionaryTypeEntity, entityDto, options);
        return entityDto;
    }
}
