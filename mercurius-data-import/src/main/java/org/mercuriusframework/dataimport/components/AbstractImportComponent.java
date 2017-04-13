package org.mercuriusframework.dataimport.components;

import org.mercuriusframework.dataimport.constants.MercuriusDataImportComponentConstants;
import org.w3c.dom.Node;

/**
 * Abstract import component
 */
public abstract class AbstractImportComponent {

    /**
     * Entity name
     */
    protected String entityName;

    /**
     * Constructor
     * @param xmlElement Xml element
     */
    public AbstractImportComponent(Node xmlElement) {
        this.entityName = xmlElement.getAttributes().getNamedItem(
                MercuriusDataImportComponentConstants.ImportComponent.ENTITY_NAME).getNodeValue();
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
}
