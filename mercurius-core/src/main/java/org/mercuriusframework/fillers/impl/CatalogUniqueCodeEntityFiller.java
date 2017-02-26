package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.converters.impl.CatalogEntityConverter;
import org.mercuriusframework.dto.CatalogUniqueCodeEntityDto;
import org.mercuriusframework.entities.CatalogUniqueCodeEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Catalog unique code entity filler
 */
@Service("catalogUniqueCodeEntityFiller")
public class CatalogUniqueCodeEntityFiller<SOURCE extends CatalogUniqueCodeEntity, RESULT extends CatalogUniqueCodeEntityDto>
        extends AbstractEntityFiller<SOURCE, RESULT> {
    /**
     * Catalog entity converter
     */
    @Autowired
    @Qualifier("catalogEntityConverter")
    protected CatalogEntityConverter catalogEntityConverter;

    /**
     * Fill a result object from a source object
     * @param source Source object
     * @param result Result object
     * @param options Load options
     */
    @Override
    public void fillIn(SOURCE source, RESULT result, LoadOptions... options) {
        super.fillIn(source, result, options);
        result.setCode(source.getCode());
        result.setName(source.getName());
        result.setCatalog(catalogEntityConverter.convert(source.getCatalog()));
    }
}
