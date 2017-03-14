package org.mercuriusframework.dto;

/**
 * Role entity data transfer object
 */
public class RoleEntityDto extends UniqueCodeEntityDto {

    /**
     * Get empty role
     * @return Empty role
     */
    public static RoleEntityDto getEmptyRole() {
        RoleEntityDto result = new RoleEntityDto();
        result.setCode("null");
        result.setUuid("null");
        result.setName("null");
        return result;
    }
}
