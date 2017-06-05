package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.CustomerEntityDto;
import org.mercuriusframework.entities.CustomerEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.CustomerEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Customer entity converter
 */
@Service("customerEntityConverter")
public class CustomerEntityConverter implements Converter<CustomerEntity, CustomerEntityDto> {

    /**
     * Customer entity filler
     */
    @Autowired
    @Qualifier("customerEntityFiller")
    protected CustomerEntityFiller customerEntityFiller;

    /**
     * Convert a source object to a result object
     * @param customerEntity Source object
     * @param options        Load options
     * @return Result object
     */
    @Override
    public CustomerEntityDto convert(CustomerEntity customerEntity, LoadOptions... options) {
        CustomerEntityDto result = new CustomerEntityDto();
        customerEntityFiller.fillIn(customerEntity, result, options);
        return result;
    }
}
