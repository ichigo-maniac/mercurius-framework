package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.entities.CatalogEntity;
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
    private CatalogEntityFiller catalogEntityFiller;

    /**
     * Convert a source object to a result object
     * @param source Source object
     * @return Result object
     */
    @Override
    public CatalogEntityDto convert(CatalogEntity source) {
        CatalogEntityDto result = new CatalogEntityDto();
        catalogEntityFiller.fillIn(source, result);
        return result;
    }
}
