package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.converters.impl.FeatureEntityConverter;
import org.mercuriusframework.dto.FeatureValueEntityDto;
import org.mercuriusframework.entities.FeatureValueEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Feature value entity filler
 */
@Service("featureValueEntityFiller")
public class FeatureValueEntityFiller extends CatalogUniqueCodeEntityFiller<FeatureValueEntity, FeatureValueEntityDto> {

    /**
     * Feature entity converter
     */
    @Autowired
    @Qualifier("featureEntityConverter")
    protected FeatureEntityConverter featureEntityConverter;

    /**
     * Fill a result object from a source object
     * @param featureValueEntity    Source object
     * @param featureValueEntityDto Result object
     * @param options               Load options
     */
    @Override
    public void fillIn(FeatureValueEntity featureValueEntity, FeatureValueEntityDto featureValueEntityDto, LoadOptions... options) {
        super.fillIn(featureValueEntity, featureValueEntityDto, options);
        featureValueEntityDto.setFeature(featureEntityConverter.convert(featureValueEntity.getFeature()));
        featureValueEntityDto.setValue(featureValueEntity.getValue().getValue());
        featureValueEntityDto.setGroupName(featureValueEntity.getGroupName());
    }
}
