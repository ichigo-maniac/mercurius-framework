package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.FacetEntityDto;
import org.mercuriusframework.entities.FacetEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.FacetEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Facet entity converter
 */
@Service("facetEntityConverter")
public class FacetEntityConverter implements Converter<FacetEntity, FacetEntityDto> {

    /**
     * Facet entity filler
     */
    @Autowired
    @Qualifier("facetEntityFiller")
    protected FacetEntityFiller facetEntityFiller;


    /**
     * Convert a source object to a result object
     * @param facetEntity Source object
     * @param options     Load options
     * @return Result object
     */
    @Override
    public FacetEntityDto convert(FacetEntity facetEntity, LoadOptions... options) {
        FacetEntityDto result = new FacetEntityDto();
        facetEntityFiller.fillIn(facetEntity, result, options);
        return result;
    }
}
