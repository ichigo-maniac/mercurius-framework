package org.mercuriusframework.dataimport.components;

/**
 * Abstract import component
 */
public abstract class AbstractImportComponent {

    /**
     * Entity name
     */
    protected String entityName;

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
}
