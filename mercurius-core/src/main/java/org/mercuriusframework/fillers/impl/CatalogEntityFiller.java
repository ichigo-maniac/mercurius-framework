package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.entities.CatalogEntity;
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
     */
    @Override
    public void fillIn(CatalogEntity catalogEntity, CatalogEntityDto catalogEntityDto) {
        super.fillIn(catalogEntity, catalogEntityDto);
    }
}
