package org.mercuriusframework.dto;

/**
 * Role entity data transfer object
 */
public class RoleEntityDto extends UniqueCodeEntityDto {

    private static final long serialVersionUID = 5666510654331457625L;

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
