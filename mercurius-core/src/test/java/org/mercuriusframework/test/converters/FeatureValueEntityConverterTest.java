package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.DictionaryItemEntityConverter;
import org.mercuriusframework.converters.impl.FeatureValueEntityConverter;
import org.mercuriusframework.dto.DictionaryItemEntityDto;
import org.mercuriusframework.dto.FeatureValueEntityDto;
import org.mercuriusframework.entities.DictionaryItemEntity;
import org.mercuriusframework.entities.FeatureValueEntity;
import org.mercuriusframework.entities.UniqueCodeEntity;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Feature value entity converter test
 */
public class FeatureValueEntityConverterTest extends AbstractTest {

    /**
     * Catalog unique code entity service
     */
    @Autowired
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Feature value entity converter
     */
    @Autowired
    private FeatureValueEntityConverter featureValueEntityConverter;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Dictionary item entity converter
     */
    @Autowired
    private DictionaryItemEntityConverter dictionaryItemEntityConverter;

    /**
     * Method test - featureValueEntityConverter.convert
     */
    @Test
    public void convertTest() {
        FeatureValueEntity featureValueEntity = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_01_pages_count", "master_catalog", FeatureValueEntity.class);
        FeatureValueEntityDto dto = featureValueEntityConverter.convert(featureValueEntity);
        assertEquals(dto.getUuid().equals("12345e10-1a94-22e6-0000-abd000223330") && dto.getCode().equals("product_sao_01_pages_count")
                && dto.getName().equals("Product sao 01 - pages_count") && dto.getValue().equals(256)
                && dto.getGroupName().equals("Common"), true);
    }

    /**
     * Method test - featureValueEntityConverter.convert
     */
    @Test
    public void convertTest2() {
        FeatureValueEntity featureValueEntity = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_01_cover_type", "master_catalog", FeatureValueEntity.class);
        FeatureValueEntityDto dto = featureValueEntityConverter.convert(featureValueEntity);
        assertEquals(dto.getUuid().equals("00045e10-1a94-22e6-0000-abd000223111") && dto.getCode().equals("product_sao_01_cover_type")
                && dto.getName().equals("Product sao 01 - covert_type") && dto.getValue().equals("Paperback")
                && dto.getGroupName().equals("Common"), true);
    }

    /**
     * Method test - featureValueEntityConverter.convert
     */
    @Test
    public void convertTest3() {
        FeatureValueEntity featureValueEntity = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_01_weight", "master_catalog", FeatureValueEntity.class);
        FeatureValueEntityDto dto = featureValueEntityConverter.convert(featureValueEntity);
        assertEquals(dto.getUuid().equals("12345e10-1a94-22e6-0000-777000223337") && dto.getCode().equals("product_sao_01_weight")
                && dto.getName().equals("Product sao 01 - weight") && dto.getValue().equals(1.5)
                && dto.getGroupName().equals("Common"), true);
    }

    /**
     * Method test - featureValueEntityConverter.convert
     */
    @Test
    public void convertTest4() {
        FeatureValueEntity featureValueEntity = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_01_for_adult", "master_catalog", FeatureValueEntity.class);
        FeatureValueEntityDto dto = featureValueEntityConverter.convert(featureValueEntity);
        assertEquals(dto.getUuid().equals("11115e0-1a94-22e6-0000-ccbd000223111") && dto.getCode().equals("product_sao_01_for_adult")
                && dto.getName().equals("Product sao 01 - for adult") && dto.getValue().equals(false)
                && dto.getGroupName().equals("Common"), true);
    }

    /**
     * Method test - featureValueEntityConverter.convert
     */
    @Test
    public void convertTest5() {
        DictionaryItemEntity dictionaryItem = uniqueCodeEntityService.getEntityByCode("country_japan", DictionaryItemEntity.class);
        DictionaryItemEntityDto dictionaryItemEntityDto = dictionaryItemEntityConverter.convert(dictionaryItem);
        FeatureValueEntity featureValueEntity = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode("product_sao_01_country", "master_catalog", FeatureValueEntity.class);
        FeatureValueEntityDto dto = featureValueEntityConverter.convert(featureValueEntity);
        assertEquals(dto.getUuid().equals("11115e0-1a94-22e6-53a5-ccbd000223111") && dto.getCode().equals("product_sao_01_country")
                && dto.getName().equals("Product sao 01 - country")
                && dto.getValue().equals(dictionaryItemEntityDto)
                && dto.getGroupName().equals("Common"), true);
    }
}
