package org.mercuriusframework.converters.impl;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.EmployeeEntityDto;
import org.mercuriusframework.entities.EmployeeEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.impl.EmployeeEntityFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Employee entity converter
 */
@Service("employeeEntityConverter")
public class EmployeeEntityConverter implements Converter<EmployeeEntity, EmployeeEntityDto> {

    /**
     * Employee entity filler
     */
    @Autowired
    @Qualifier("employeeEntityFiller")
    protected EmployeeEntityFiller employeeEntityFiller;

    /**
     * Convert a source object to a result object
     * @param employeeEntity Source object
     * @param options        Load options
     * @return Result object
     */
    @Override
    public EmployeeEntityDto convert(EmployeeEntity employeeEntity, LoadOptions... options) {
        EmployeeEntityDto result = new EmployeeEntityDto();
        employeeEntityFiller.fillIn(employeeEntity, result, options);
        return result;
    }
}
