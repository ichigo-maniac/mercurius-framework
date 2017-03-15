package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Employee entity class
 */

@Entity(name = EmployeeEntity.ENTITY_NAME)
@DiscriminatorValue(EmployeeEntity.ENTITY_NAME)
public class EmployeeEntity extends AbstractUserEntity {

    private static final long serialVersionUID = 8229895947708518865L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Employee";

    /**
     * Roles
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "EMPLOYEE_ROLE_LINK",
            joinColumns = {
                    @JoinColumn(name = "EMPLOYEE_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_UUID",
                            nullable = false, updatable = false)})
    private Set<RoleEntity> roles;
    public static final String ROLES = "roles";

    /**
     * Get roles
     * @return Roles
     */
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    /**
     * Set roles
     * @param roles Roles
     */
    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
