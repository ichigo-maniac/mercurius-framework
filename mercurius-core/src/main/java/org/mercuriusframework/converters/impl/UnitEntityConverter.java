package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.UnitEntityDto;
import org.mercuriusframework.entities.UnitEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.UnitEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Unit entity converter
 */
@Service("unitEntityConverter")
public class UnitEntityConverter implements Converter<UnitEntity, UnitEntityDto> {

    /**
     * Unit entity converter
     */
    @Autowired
    @Qualifier("unitEntityFiller")
    protected UnitEntityFiller unitEntityFiller;

    /**
     * Convert a source object to a result object
     * @param source Source object
     * @param options    Load options
     * @return Result object
     */
    @Override
    public UnitEntityDto convert(UnitEntity source, LoadOptions... options) {
        UnitEntityDto result = new UnitEntityDto();
        unitEntityFiller.fillIn(source, result);
        return result;
    }
}
