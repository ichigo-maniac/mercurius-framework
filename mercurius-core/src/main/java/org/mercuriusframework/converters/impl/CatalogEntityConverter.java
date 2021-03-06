package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.CatalogEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Catalog entity converter
 */
@Service("catalogEntityConverter")
public class CatalogEntityConverter implements Converter<CatalogEntity, CatalogEntityDto> {

    /**
     * Catalog entity filler
     */
    @Autowired
    @Qualifier("catalogEntityFiller")
    protected CatalogEntityFiller catalogEntityFiller;

    /**
     * Convert a source object to a result object
     * @param source Source object
     * @param options Load options
     * @return Result object
     */
    @Override
    public CatalogEntityDto convert(CatalogEntity source, LoadOptions... options) {
        CatalogEntityDto result = new CatalogEntityDto();
        catalogEntityFiller.fillIn(source, result, options);
        return result;
    }
}
