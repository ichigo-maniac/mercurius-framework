package org.mercuriusframework.widgetss;

import org.mercuriusframework.constants.MercuriusMMCWidgetsConstants;
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
        this.title = xmlElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.TreeNodesView.TreeNode.TITLE).getNodeValue();
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
