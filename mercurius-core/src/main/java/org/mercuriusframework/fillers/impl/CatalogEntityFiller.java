package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.stereotype.Service;

/**
 * Catalog entity filler
 */
@Service("catalogEntityFiller")
public class CatalogEntityFiller extends UniqueCodeEntityFiller<CatalogEntity, CatalogEntityDto> {
    /**
     * Fill a result object from a source object
     * @param catalogEntity    Source object
     * @param catalogEntityDto Result object
     * @param options Load options
     */
    @Override
    public void fillIn(CatalogEntity catalogEntity, CatalogEntityDto catalogEntityDto, LoadOptions... options) {
        super.fillIn(catalogEntity, catalogEntityDto, options);
    }
}
