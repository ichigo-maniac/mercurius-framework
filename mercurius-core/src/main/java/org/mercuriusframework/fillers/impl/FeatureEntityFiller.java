package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.FeatureEntityDto;
import org.mercuriusframework.entities.FeatureEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.stereotype.Service;

/**
 * Feature entity filler
 */
@Service("featureEntityFiller")
public class FeatureEntityFiller extends CatalogUniqueCodeEntityFiller<FeatureEntity, FeatureEntityDto> {

    /**
     * Fill a result object from a source object
     * @param featureEntity    Source object
     * @param featureEntityDto Result object
     * @param options          Load options
     */
    @Override
    public void fillIn(FeatureEntity featureEntity, FeatureEntityDto featureEntityDto, LoadOptions... options) {
        super.fillIn(featureEntity, featureEntityDto, options);
        featureEntityDto.setFeatureType(featureEntity.getFeatureType());
    }
}
