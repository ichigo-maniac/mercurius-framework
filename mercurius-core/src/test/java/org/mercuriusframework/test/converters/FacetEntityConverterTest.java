package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.FacetEntityConverter;
import org.mercuriusframework.dto.FacetEntityDto;
import org.mercuriusframework.entities.FacetEntity;
import org.mercuriusframework.enums.FacetType;
import org.mercuriusframework.enums.SolrCriteriaValueType;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Facet entity converter test
 */
public class FacetEntityConverterTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> DICTIONARY_ITEMS_UUIDS_LIST = Arrays.asList(
            "2222636e-f065-11e6-4444-836adef2f3a6", "4a9b636e-f065-11e6-4444-836111f2f3a6"
    );

    /**
     * Facet entity converter
     */
    @Autowired
    private FacetEntityConverter facetEntityConverter;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - facetEntityConverter.convert
     */
    @Test
    public void convertTest() {
        FacetEntity facet = uniqueCodeEntityService.getEntityByCode("facet_country", FacetEntity.class);
        FacetEntityDto entityDto = facetEntityConverter.convert(facet);
        assertEquals(entityDto.getUuid().equals("1111b636e-f065-11e6-5322-836adef2f3a") && entityDto.getFacetType() == FacetType.DICTIONARY
                && entityDto.getSolrCriteriaValueType() == SolrCriteriaValueType.IN
                && entityDto.getCode().equals("facet_country") && entityDto.getName().equals("Country")
                && entityDto.getSolrDocumentFieldName().equals("country"), true);
        assertUuidListsEquals(getUuidsFromDtos(entityDto.getAvailableValues()), DICTIONARY_ITEMS_UUIDS_LIST);
    }
}
