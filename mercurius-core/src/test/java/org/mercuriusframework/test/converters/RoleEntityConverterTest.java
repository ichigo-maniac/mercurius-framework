package org.mercuriusframework.test.converters;

import org.junit.Test;
import org.mercuriusframework.converters.impl.RoleEntityConverter;
import org.mercuriusframework.dto.RoleEntityDto;
import org.mercuriusframework.entities.RoleEntity;
import org.mercuriusframework.test.AbstractTest;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Role entity converter test
 */
public class RoleEntityConverterTest extends AbstractTest {

    /**
     * Role entity converter
     */
    @Autowired
    private RoleEntityConverter roleEntityConverter;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - roleEntityConverter.convert
     */
    @Test
    public void convertTest() {
        RoleEntity role = uniqueCodeEntityService.getEntityByCode("ROLE_ADMIN", RoleEntity.class);
        RoleEntityDto dto = roleEntityConverter.convert(role);
        assertEquals(dto.getUuid().equals("12345e10-3367-11e6-bba1-bf2400ed1234") && dto.getCode().equals("ROLE_ADMIN") &&
                dto.getName().equals("Admin role"), true);
    }
}
