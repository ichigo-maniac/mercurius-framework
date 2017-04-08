package org.mercuriusframework.mmc.widgets.treenodesview;

import org.mercuriusframework.mmc.constants.MercuriusMMCWidgetsConstants;
import org.mercuriusframework.providers.MessageSourceProvider;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree node element
 */
public class TreeNodeElement extends TreeNode {

    /**
     * Get nodes
     */
    protected List<TreeNode> nodes;

    /**
     * Constructor
     * @param xmlElement XML element
     */
    public TreeNodeElement(Node xmlElement) {
        /** Title */
        String titleCode = xmlElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.TreeNodesView.TreeNode.TITLE).getNodeValue();
        this.title = MessageSourceProvider.getMessage(titleCode);
        /** Nodes */
        this.nodes = new ArrayList<>();
        /** Parse child elements */
        for (int i = 0; i < xmlElement.getChildNodes().getLength(); i++) {
            Node nodeElement = xmlElement.getChildNodes().item(i);
            if (nodeElement.getNodeType() == Node.ELEMENT_NODE) {
                if (nodeElement.getNodeName().equals(MercuriusMMCWidgetsConstants.TreeNodesView.TreeNode.WIDGET_NAME)) {
                    nodes.add(new TreeNodeElement(nodeElement));
                }
                if (nodeElement.getNodeName().equals(MercuriusMMCWidgetsConstants.TreeNodesView.TreeNodeEntityElement.WIDGET_NAME)) {
                    nodes.add(new TreeNodeEntityElement(nodeElement));
                }
            }
        }
    }

    /**
     * Constructor
     * @param title Title
     */
    public TreeNodeElement(String title) {
        this.nodes = new ArrayList<>();
        this.title = title;
    }

    /**
     * Constructor
     * @param title Title
     * @param nodes Nodes
     */
    public TreeNodeElement(String title, List<TreeNode> nodes) {
        this.nodes = nodes;
        this.title = title;
    }

    /**
     * Get nodes
     * @return Nodes
     */
    public List<TreeNode> getNodes() {
        return nodes;
    }

    /**
     * Set nodes
     * @param nodes Nodes
     */
    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }

    /**
     * Get type
     * @return Type
     */
    @Override
    public String getType() {
        return MercuriusMMCWidgetsConstants.TreeNodesView.TreeNode.WIDGET_NAME;
    }
}
