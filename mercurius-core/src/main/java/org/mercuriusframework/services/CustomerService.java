package org.mercuriusframework.services;

import org.mercuriusframework.entities.CustomerEntity;
import org.mercuriusframework.enums.SocialNetworkType;

/**
 * Customer service interface
 */
public interface CustomerService {

    /**
     * Get customer by social network id and type
     * @param socialNetworkType Social network Type
     * @param socialNetworkId Social network id
     * @return Customer
     */
    CustomerEntity getCustomerBySocialNetworkIdAndType(SocialNetworkType socialNetworkType, String socialNetworkId);
}
