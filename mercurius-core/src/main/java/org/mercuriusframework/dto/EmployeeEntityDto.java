package org.mercuriusframework.dto;

import java.util.List;

/**
 * Employee entity data transfer object
 */
public class EmployeeEntityDto extends UserEntityDto {

    private static final long serialVersionUID = 6722815934295555114L;

    /**
     * Roles
     */
    private List<RoleEntityDto> roles;

    /**
     * Get roles
     * @return Roles
     */
    public List<RoleEntityDto> getRoles() {
        return roles;
    }

    /**
     * Set roles
     * @param roles Roles
     */
    public void setRoles(List<RoleEntityDto> roles) {
        this.roles = roles;
    }
}
