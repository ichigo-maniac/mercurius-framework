package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.FeatureEntityDto;
import org.mercuriusframework.entities.FeatureEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.FeatureEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Feature entity converter
 */
@Service("featureEntityConverter")
public class FeatureEntityConverter implements Converter<FeatureEntity, FeatureEntityDto> {

    /**
     * Feature entity filler
     */
    @Autowired
    @Qualifier("featureEntityFiller")
    protected FeatureEntityFiller featureEntityFiller;

    /**
     * Convert a source object to a result object
     * @param featureEntity Source object
     * @param options       Load options
     * @return Result object
     */
    @Override
    public FeatureEntityDto convert(FeatureEntity featureEntity, LoadOptions... options) {
        FeatureEntityDto featureEntityDto = new FeatureEntityDto();
        featureEntityFiller.fillIn(featureEntity, featureEntityDto, options);
        return featureEntityDto;
    }
}
