package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.FeatureValueEntityDto;
import org.mercuriusframework.entities.FeatureValueEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.FeatureValueEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Feature value entity converter
 */
@Service("featureValueEntityConverter")
public class FeatureValueEntityConverter implements Converter<FeatureValueEntity, FeatureValueEntityDto> {

    /**
     * Feature value entity filler
     */
    @Autowired
    @Qualifier("featureValueEntityFiller")
    protected FeatureValueEntityFiller featureValueEntityFiller;

    /**
     * Convert a source object to a result object
     * @param featureValueEntity Source object
     * @param options            Load options
     * @return Result object
     */
    @Override
    public FeatureValueEntityDto convert(FeatureValueEntity featureValueEntity, LoadOptions... options) {
        FeatureValueEntityDto result = new FeatureValueEntityDto();
        featureValueEntityFiller.fillIn(featureValueEntity, result, options);
        return result;
    }
}
