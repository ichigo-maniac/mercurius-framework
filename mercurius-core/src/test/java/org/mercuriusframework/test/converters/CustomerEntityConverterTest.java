package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.CustomerEntityConverter;
import org.mercuriusframework.dto.CustomerEntityDto;
import org.mercuriusframework.entities.CustomerEntity;
import org.mercuriusframework.enums.SocialNetworkType;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Customer entity converter test
 */
public class CustomerEntityConverterTest extends AbstractTest {

    /**
     * Customer entity converter
     */
    @Autowired
    private CustomerEntityConverter customerEntityConverter;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - customerEntityConverter,convert
     */
    @Test
    public void convertTest() {
        CustomerEntity customerEntity = uniqueCodeEntityService.getEntityByCode("test_customer", CustomerEntity.class);
        CustomerEntityDto entityDto = customerEntityConverter.convert(customerEntity);
        assertEquals(entityDto.getCode().equals("test_customer") && entityDto.getUuid().equals("12001000-1154-11e6-4444-bf2400ed1234")
            && entityDto.getSocialNetworkType() == SocialNetworkType.FACEBOOK
            && entityDto.getFirstName().equals("Test") && entityDto.getLastName().equals("Test")
            && entityDto.getSocialNetworkId().equals("test-social-network-id"), true);
    }
}
