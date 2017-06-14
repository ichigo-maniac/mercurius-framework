package org.mercuriusframework.services.impl;

import org.mercuriusframework.entities.CustomerEntity;
import org.mercuriusframework.enums.SocialNetworkType;
import org.mercuriusframework.services.CustomerService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Customer service
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Get customer by social network id and type
     * @param socialNetworkType Social network Type
     * @param socialNetworkId   Social network id
     * @return Customer
     */
    @Override
    public CustomerEntity getCustomerBySocialNetworkIdAndType(SocialNetworkType socialNetworkType, String socialNetworkId) {
        return entityService.getSingleResultByQuery("SELECT customer FROM " + CustomerEntity.ENTITY_NAME + " as customer " +
                "WHERE customer." + CustomerEntity.SOCIAL_NETWORK_ID + " = :socialNetworkId " +
                "AND customer." + CustomerEntity.SOCIAL_NETWORK_TYPE + " = :socialNetworkType ",
                CustomerEntity.class,
                new QueryParameter("socialNetworkId", socialNetworkId),
                new QueryParameter("socialNetworkType", socialNetworkType));
    }
}
