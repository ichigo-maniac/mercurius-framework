package org.mercuriusframework.widgets.treenodesview;

import org.mercuriusframework.constants.MercuriusMMCWidgetsConstants;
import org.w3c.dom.Node;

/**
 * Tree node entity element
 */
public class TreeNodeEntityElement extends TreeNode {

    /**
     * Entity name
     */
    protected String entityName;

    /**
     * Constructor
     * @param xmlElement XML element
     */
    public TreeNodeEntityElement(Node xmlElement) {
        this.title = xmlElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.TreeNodesView.TreeNodeEntityElement.TITLE).getNodeValue();
        this.entityName = xmlElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.TreeNodesView.TreeNodeEntityElement.ENTITY_NAME).getNodeValue();
    }

    /**
     * Constructor
     * @param entityName Entity name
     * @param title Title
     */
    public TreeNodeEntityElement(String entityName, String title) {
        this.entityName = entityName;
        this.title = title;
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
     * Get type
     * @return Type
     */
    @Override
    public String getType() {
        return MercuriusMMCWidgetsConstants.TreeNodesView.TreeNodeEntityElement.WIDGET_NAME;
    }
}
