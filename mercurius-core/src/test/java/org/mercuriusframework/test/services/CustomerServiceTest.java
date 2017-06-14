package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.entities.CustomerEntity;
import org.mercuriusframework.enums.SocialNetworkType;
import org.mercuriusframework.services.CustomerService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Customer service test
 */
public class CustomerServiceTest extends AbstractTest {

    /**
     * Customer service
     */
    @Autowired
    private CustomerService customerService;

    /**
     * Method test - customerService.getCustomerBySocialNetworkIdAndType
     */
    @Test
    public void getCustomerBySocialNetworkIdAndTypeTest() {
        CustomerEntity customer = customerService.getCustomerBySocialNetworkIdAndType(SocialNetworkType.FACEBOOK, "test-social-network-id");
        assertEquals(customer.getUuid().equals("12001000-1154-11e6-4444-bf2400ed1234"), true);
    }

}
