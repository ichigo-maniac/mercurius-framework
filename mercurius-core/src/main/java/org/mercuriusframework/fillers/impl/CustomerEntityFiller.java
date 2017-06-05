package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.CustomerEntityDto;
import org.mercuriusframework.entities.CustomerEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.stereotype.Service;

/**
 * Customer entity filler
 */
@Service("customerEntityFiller")
public class CustomerEntityFiller extends UserEntityFiller<CustomerEntity, CustomerEntityDto> {

    /**
     * Fill a result object from a source object
     * @param customerEntity    Source object
     * @param customerEntityDto Result object
     * @param options           Load options
     */
    @Override
    public void fillIn(CustomerEntity customerEntity, CustomerEntityDto customerEntityDto, LoadOptions... options) {
        super.fillIn(customerEntity, customerEntityDto, options);
        customerEntityDto.setSocialNetworkType(customerEntity.getSocialNetworkType());
        customerEntityDto.setSocialNetworkId(customerEntity.getSocialNetworkId());
    }
}
