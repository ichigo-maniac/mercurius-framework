package org.mercuriusframework.dto;

/*
 * Role and entity name container
 */
public class RoleEntityNameContainer {

    /**
     * Role
     */
    private RoleEntityDto role;

    /**
     * Entity name
     */
    private String entityName;

    /**
     * Constructor
     * @param role Role
     * @param entityName Entity name
     */
    public RoleEntityNameContainer(RoleEntityDto role, String entityName) {
        this.role = role;
        this.entityName = entityName;
    }

    /**
     * Get role
     * @return Role
     */
    public RoleEntityDto getRole() {
        return role;
    }

    /**
     * Set role
     * @param role Role
     */
    public void setRole(RoleEntityDto role) {
        this.role = role;
    }

    /**
     * Get entity name
     * @return Entity name
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Set entity name
     * @param entityName Entity name
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * Get hash code
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return (role.getCode() + entityName).hashCode();
    }

    /**
     * Equals
     * @param object Object
     * @return Compare result
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        final RoleEntityNameContainer other = (RoleEntityNameContainer) object;
        return other.entityName.equals(entityName) && other.getRole().equals(role);
    }
}
