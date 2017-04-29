package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.converters.impl.DictionaryItemEntityConverter;
import org.mercuriusframework.dto.FacetEntityDto;
import org.mercuriusframework.entities.DictionaryItemEntity;
import org.mercuriusframework.entities.DictionaryTypeEntity;
import org.mercuriusframework.entities.FacetEntity;
import org.mercuriusframework.enums.FacetType;
import org.mercuriusframework.enums.FeatureType;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Facet entity filler
 */
@Service("facetEntityFiller")
public class FacetEntityFiller extends UniqueCodeEntityFiller<FacetEntity, FacetEntityDto> {

    /**
     * Dictionary item entity converter
     */
    @Autowired
    @Qualifier("dictionaryItemEntityConverter")
    protected DictionaryItemEntityConverter dictionaryItemEntityConverter;

    /**
     * Dictionary service
     */
    @Autowired
    @Qualifier("dictionaryService")
    protected DictionaryService dictionaryService;

    /**
     * Fill a result object from a source object
     * @param facetEntity    Source object
     * @param facetEntityDto Result object
     * @param options        Load options
     */
    @Override
    public void fillIn(FacetEntity facetEntity, FacetEntityDto facetEntityDto, LoadOptions... options) {
        super.fillIn(facetEntity, facetEntityDto, options);
        facetEntityDto.setFacetType(facetEntity.getFacetType());
        /** Set available dictionary items */
        facetEntityDto.setAvailableValues(new ArrayList<>());
        if (facetEntity.getFeature() != null && facetEntity.getFacetType() == FacetType.DICTIONARY
                && facetEntity.getFeature().getDictionaryType() != null
                && facetEntity.getFeature().getFeatureType() == FeatureType.DICTIONARY_TYPE) {
            DictionaryTypeEntity dictionaryType = facetEntity.getFeature().getDictionaryType();
            List<DictionaryItemEntity> dictionaryItems = dictionaryService.getDictionaryItems(dictionaryType.getCode());
            facetEntityDto.setAvailableValues(dictionaryItemEntityConverter.convertAll(dictionaryItems));
        }
    }
}
