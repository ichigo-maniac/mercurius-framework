package org.mercuriusframework.fillers.impl;

import org.apache.commons.lang.ArrayUtils;
import org.mercuriusframework.converters.impl.RoleEntityConverter;
import org.mercuriusframework.dto.EmployeeEntityDto;
import org.mercuriusframework.dto.RoleEntityDto;
import org.mercuriusframework.entities.EmployeeEntity;
import org.mercuriusframework.entities.RoleEntity;
import org.mercuriusframework.enums.EmployeeLoadOptions;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Employee entity filler
 */
@Service("employeeEntityFiller")
public class EmployeeEntityFiller extends UserEntityFiller<EmployeeEntity, EmployeeEntityDto> {

    /**
     * Role entity converter
     */
    @Autowired
    @Qualifier("roleEntityConverter")
    protected RoleEntityConverter roleEntityConverter;

    /**
     * Fill a result object from a source object
     * @param employeeEntity    Source object
     * @param employeeEntityDto Result object
     * @param options           Load options
     */
    @Override
    public void fillIn(EmployeeEntity employeeEntity, EmployeeEntityDto employeeEntityDto, LoadOptions... options) {
        super.fillIn(employeeEntity, employeeEntityDto, options);
        /** Roles */
        if (ArrayUtils.contains(options, EmployeeLoadOptions.ROLES)) {
            employeeEntityDto.setRoles(roleEntityConverter.convertAll(new ArrayList<>(employeeEntity.getRoles())));
        }
    }
}
