package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.FeatureEntityConverter;
import org.mercuriusframework.dto.FeatureEntityDto;
import org.mercuriusframework.entities.FeatureEntity;
import org.mercuriusframework.enums.FeatureType;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Feature entity converter test
 */
public class FeatureEntityConverterTest extends AbstractTest {

    /**
     * Catalog unique code entity service
     */
    @Autowired
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Feature entity converter
     */
    @Autowired
    private FeatureEntityConverter featureEntityConverter;

    /**
     * Method test - featureEntityConverter.convert
     */
    @Test
    public void convertTest() {
        FeatureEntity feature = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("book_pages_feature", "master_catalog", FeatureEntity.class);
        FeatureEntityDto dto = featureEntityConverter.convert(feature);
        assertEquals(dto.getUuid().equals("b1f05e10-5522-11e6-4221-bf2400ed613a") && dto.getName().equals("Pages count")
                && dto.getIncludeInIndex()
                && dto.getCode().equals("book_pages_feature")
                && dto.getFeatureType() == FeatureType.NUMERIC_TYPE, true);
    }
}
