package org.mercuriusframework.mmc.widgets.treenodesview;

import org.apache.commons.lang.StringUtils;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.mmc.constants.MercuriusMMCWidgetsConstants;
import org.mercuriusframework.helpers.MessageSourceProvider;
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
        /** Entity name */
        this.entityName = xmlElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.TreeNodesView.TreeNodeEntityElement.ENTITY_NAME).getNodeValue();
        /** Title */
        String titleCode = xmlElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.TreeNodesView.TreeNodeEntityElement.TITLE) != null ?
                xmlElement.getAttributes().getNamedItem(
                        MercuriusMMCWidgetsConstants.TreeNodesView.TreeNodeEntityElement.TITLE).getNodeValue() : "";
        if (StringUtils.isEmpty(titleCode)) {
            titleCode = MercuriusConstants.LOCALIZATION.ENTITY_PREFIX + entityName + MercuriusConstants.LOCALIZATION.ENTITY_SUFFIX;
        }
        this.title = MessageSourceProvider.getMessage(titleCode);
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
