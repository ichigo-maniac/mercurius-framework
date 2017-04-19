package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Role entity class
 */
@Entity(name = RoleEntity.ENTITY_NAME)
@Table(name = "ROLES")
public class RoleEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = 3815233614324792736L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Role";

    /**
     * Employees
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "EMPLOYEE_ROLE_LINKS",
            joinColumns = {
                    @JoinColumn(name = "ROLE_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "EMPLOYEE_UUID",
                            nullable = false, updatable = false)})
    private Set<EmployeeEntity> employees;
    public static final String EMPLOYEES = "employees";

    /**
     * Get employees
     * @return Employees
     */
    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    /**
     * Set employees
     * @param employees Employees
     */
    public void setEmployees(Set<EmployeeEntity> employees) {
        this.employees = employees;
    }
}
