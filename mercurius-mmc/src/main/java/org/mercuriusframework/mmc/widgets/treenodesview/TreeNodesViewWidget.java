package org.mercuriusframework.mmc.widgets.treenodesview;

import org.mercuriusframework.mmc.constants.MercuriusMMCWidgetsConstants;
import org.mercuriusframework.mmc.widgets.Widget;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree nodes widget
 */
public class TreeNodesViewWidget extends Widget {

    /**
     * Get nodes
     */
    protected List<TreeNode> nodes;

    /**
     * Constructor
     * @param treeNode Tree node xml element
     * @param priority Priority
     */
    public TreeNodesViewWidget(Node treeNode, Integer priority) {
        this.setPriority(priority);
        this.nodes = new ArrayList<>();
        /** Parse tree node */
        for (int i = 0; i < treeNode.getChildNodes().getLength(); i++) {
            Node nodeElement = treeNode.getChildNodes().item(i);
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
}
