package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.EmployeeEntityConverter;
import org.mercuriusframework.dto.EmployeeEntityDto;
import org.mercuriusframework.entities.EmployeeEntity;
import org.mercuriusframework.enums.EmployeeLoadOptions;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Employee entity converter test
 */
public class EmployeeEntityConverterTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> ROLES_UUIDS_LIST = Arrays.asList(
            "12345e10-3367-11e6-bba1-bf2400ed1234", "12345e10-fa94-11e6-b6ff-bf2aaaed1234"
    );

    /**
     * Employee entity converter
     */
    @Autowired
    private EmployeeEntityConverter employeeEntityConverter;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - employeeEntityConverter.convert
     */
    @Test
    public void convertTest() {
        EmployeeEntity employee = uniqueCodeEntityService.getEntityByCode("admin", EmployeeEntity.class);
        EmployeeEntityDto dto = employeeEntityConverter.convert(employee);
        assertEquals(dto.getUuid().equals("12001000-1154-11e6-b6ff-bf2400ed1234") && dto.getCode().equals("admin") &&
                dto.getName().equals("Admin") && dto.getEmail().equals("admin@mercurius.org"), true);
    }

    /**
     * Method test - employeeEntityConverter.convert
     */
    @Test
    public void convertTest2() {
        EmployeeEntity employee = uniqueCodeEntityService.getEntityByCode("admin", EmployeeEntity.class);
        EmployeeEntityDto dto = employeeEntityConverter.convert(employee, EmployeeLoadOptions.ROLES);
        assertEquals(dto.getUuid().equals("12001000-1154-11e6-b6ff-bf2400ed1234") && dto.getCode().equals("admin") &&
                dto.getName().equals("Admin") && dto.getEmail().equals("admin@mercurius.org"), true);
        assertUuidListsEquals(ROLES_UUIDS_LIST, getUuidsFromDtos(dto.getRoles()));
    }
}
